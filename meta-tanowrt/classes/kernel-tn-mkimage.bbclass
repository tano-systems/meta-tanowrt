#
# SPDX-License-Identifier: MIT
# SPDX-FileCopyrightText: 2021 Anton Kikin <a.kikin@tano-systems.com>
#
# Copyright (C) 2021 Anton Kikin <a.kikin@tano-systems.com>
#

#
# Usage example. In machine configuration:
#
#   KERNEL_CLASSES += "kernel-tn-mkimage"
#   KERNEL_MKIMAGE_TYPES = "ubi"
#   KERNEL_MKIMAGE_UBI_VOL_NAME = "kernel"
#   KERNEL_MKIMAGE_UBI_UBINIZE_ARGS = "-m 2048 -p 128KiB -s 512 -O 2048"
#   KERNEL_MKIMAGE_INCLUDE_DEVICETREE = "0"
#

inherit kernel-artifact-names

#
# Supported types:
# - ubi
# - ext2
# - ext3
# - ext4
# - vfat
#

KERNEL_MKIMAGE_TYPES ?= "ubi"

# Supported only for ext2/3/4 and vfat
KERNEL_MKIMAGE_INCLUDE_DEVICETREE ?= "0"

do_kernel_mkimage_ubi[depends] += "mtd-utils-native:do_populate_sysroot"
do_kernel_mkimage_ext4[depends] += "e2fsprogs-native:do_populate_sysroot"
do_kernel_mkimage_ext3[depends] += "e2fsprogs-native:do_populate_sysroot"
do_kernel_mkimage_vfat[depends] += "dosfstools-native:do_populate_sysroot mtools-native:do_populate_sysroot"

KERNEL_MKIMAGE_WORKDIR ?= "${WORKDIR}/mkimage"
KERNEL_MKIMAGE_ARTIFACT_NAME ?= "-${PKGV}-${PKGR}-${MACHINE}"
KERNEL_MKIMAGE_ARTIFACT_LINK_NAME ?= "-${MACHINE}"
KERNEL_MKIMAGE_EXTRA_SPACE ?= "1024"
KERNEL_MKIMAGE_OVERHEAD_FACTOR ?= "120"

KERNEL_MKIMAGE_UBI_VOL_NAME ?= "kernel"
KERNEL_MKIMAGE_UBI_VOL_SIZE ?= ""
KERNEL_MKIMAGE_UBI_VOL_TYPE ?= "dynamic"
KERNEL_MKIMAGE_UBI_VOL_ID ?= "0"
KERNEL_MKIMAGE_UBI_VOL_ALIGNMENT ?= "1"

python __anonymous () {
    types = d.getVar('KERNEL_MKIMAGE_TYPES', True).split()

    if "ubi" in types:
        ubinize_args = d.getVar("KERNEL_MKIMAGE_UBI_UBINIZE_ARGS")
        ubi_volume = d.getVar("KERNEL_MKIMAGE_UBI_VOL_NAME")
        if not ubinize_args or not ubi_volume:
            bb.fatal("KERNEL_MKIMAGE_UBI_UBINIZE_ARGS and KERNEL_MKIMAGE_UBI_VOL_NAME have to be set")

    for type in types:
        d.appendVarFlag('do_kernel_mkimage_%s' % type, 'cleandirs', \
            ' %s-%s' % (d.getVar('KERNEL_MKIMAGE_WORKDIR', True), type) )

        task = "do_kernel_mkimage_%s" % type

        initramfs_image = d.getVar('INITRAMFS_IMAGE')
        if initramfs_image:
            kernel_image_types = d.getVar('KERNEL_IMAGETYPES').split()
            if "fitImage" in kernel_image_types:
                d.appendVarFlag(task, 'depends', ' ${PN}:do_assemble_fitimage_initramfs')

        bb.build.addtask(task, "do_deploy", "do_install", d)
}

ubi_create_image() {
	local ubinize_config=$1
	local ubi_path=$2
	local ubi_link=$3

	${STAGING_DIR_NATIVE}/usr/sbin/ubinize \
		${KERNEL_MKIMAGE_UBI_UBINIZE_ARGS} \
		--output="$ubi_path" \
		$ubinize_config

	if [ -n "$ubi_link" ]; then
		rm -f $ubi_link
		ln -snf $(basename $ubi_path) "$ubi_link"
	fi
}

ubi_create_ubinize_config() {
	local config_path=$1
	local image_path=$2

	# Write ubinize config
	{
		echo "[kernel_volume]"
		echo "mode=ubi"
		echo "image=${image_path}"
		echo "vol_id=${KERNEL_MKIMAGE_UBI_VOL_ID}"
		echo "vol_type=${KERNEL_MKIMAGE_UBI_VOL_TYPE}"
		echo "vol_name=${KERNEL_MKIMAGE_UBI_VOL_NAME}"
		if [ -n "${KERNEL_MKIMAGE_UBI_VOL_SIZE}" ]; then
			echo "vol_size=${KERNEL_MKIMAGE_UBI_VOL_SIZE}"
		fi
		echo "vol_alignment=${KERNEL_MKIMAGE_UBI_VOL_ALIGNMENT}"
	
	} > $config_path
}

ubi_assemble_image() {
	local imageType=$1

	bbdebug 1 "Creating UBI image for ${imageType}..."

	ubi_create_ubinize_config \
		"${KERNEL_MKIMAGE_WORKDIR}-ubi/${imageType}.ubinize.cfg" \
		"${B}/arch/${ARCH}/boot/${imageType}"

	ubi_create_image \
		"${KERNEL_MKIMAGE_WORKDIR}-ubi/${imageType}.ubinize.cfg" \
		"${KERNEL_MKIMAGE_WORKDIR}-ubi/${imageType}${KERNEL_MKIMAGE_ARTIFACT_NAME}.ubi" \
		"${KERNEL_MKIMAGE_WORKDIR}-ubi/${imageType}${KERNEL_MKIMAGE_ARTIFACT_LINK_NAME}.ubi"
}

do_kernel_mkimage_ubi() {
	if [ "${KERNEL_MKIMAGE_INCLUDE_DEVICETREE}" = "1" ]; then
		bbwarn "KERNEL_MKIMAGE_INCLUDE_DEVICETREE is not supported for ubi type"
	fi

	for imageType in ${KERNEL_IMAGETYPES}; do
		ubi_assemble_image "${imageType}"

		if [ "${imageType}" = "fitImage" ]; then
			if [ -n "${INITRAMFS_IMAGE}" ]; then
				ubi_assemble_image "${imageType}-${INITRAMFS_IMAGE}"
			fi
		fi
	done
}

ext234_assemble_image() {
	local imageType=${1}
	local fstype=${2}
	local targetName=${3}

	local FSDIR="${KERNEL_MKIMAGE_WORKDIR}-${fstype}/fs"
	local OUTPUT_ARTIFACT="${KERNEL_MKIMAGE_WORKDIR}-${fstype}/${imageType}${KERNEL_MKIMAGE_ARTIFACT_NAME}.${fstype}"
	local OUTPUT_LINK="${KERNEL_MKIMAGE_WORKDIR}-${fstype}/${imageType}${KERNEL_MKIMAGE_ARTIFACT_LINK_NAME}.${fstype}"

	bbdebug 1 "Creating ${fstype} image for ${imageType}..."

	rm -rf "${FSDIR}"
	mkdir -p "${FSDIR}"
	cp "${B}/arch/${ARCH}/boot/${imageType}" "${FSDIR}/${targetName}"

	local IMAGE_SIZE
	if [ -z "${targetName}" ]; then
		IMAGE_SIZE=$(stat -L -c %s ${FSDIR}/${imageType})
	else
		IMAGE_SIZE=$(stat -L -c %s ${FSDIR}/${targetName})
	fi

	if [ "${KERNEL_MKIMAGE_INCLUDE_DEVICETREE}" = "1" ]; then
		for dtbf in ${KERNEL_DEVICETREE}; do
			dtb=`normalize_dtb "$dtbf"`
			dtb_ext=${dtb##*.}
			dtb_base_name=`basename $dtb .$dtb_ext`
			dtb_path=`get_real_dtb_path_in_kernel "$dtb"`
			install -m 0644 $dtb_path ${FSDIR}/$dtb_base_name.$dtb_ext

			DTB_SIZE=$(stat -L -c %s ${FSDIR}/$dtb_base_name.$dtb_ext)
			IMAGE_SIZE=$(expr ${IMAGE_SIZE} + ${DTB_SIZE})
		done
	fi

	IMAGE_SIZE=$(expr ${IMAGE_SIZE} \* ${KERNEL_MKIMAGE_OVERHEAD_FACTOR} / 102400 + ${KERNEL_MKIMAGE_EXTRA_SPACE})

	eval local COUNT=\"0\"
	eval local MIN_COUNT=\"60\"
	if [ ${IMAGE_SIZE} -lt ${MIN_COUNT} ]; then
		eval COUNT=\"$MIN_COUNT\"
	fi

	dd if=/dev/zero of=${OUTPUT_ARTIFACT} seek=${IMAGE_SIZE} count=${COUNT} bs=1024

	mkfs.${fstype} -F "${OUTPUT_ARTIFACT}" \
		-d "${KERNEL_MKIMAGE_WORKDIR}-${fstype}/fs"

	# Error codes 0-3 indicate successfull operation of fsck (no errors or errors corrected)
	fsck.${fstype} -pvfD "${OUTPUT_ARTIFACT}" || [ $? -le 3 ]

	if [ -n "${OUTPUT_LINK}" ]; then
		rm -f ${OUTPUT_LINK}
		ln -snf $(basename ${OUTPUT_ARTIFACT}) "${OUTPUT_LINK}"
	fi
}

do_kernel_mkimage_ext234() {
	local fstype=${1}
	for imageType in ${KERNEL_IMAGETYPES}; do
		ext234_assemble_image "${imageType}" "${fstype}"
		if [ "${imageType}" = "fitImage" ]; then
			if [ -n "${INITRAMFS_IMAGE}" ]; then
				ext234_assemble_image "${imageType}-${INITRAMFS_IMAGE}" "${fstype}" "${imageType}"
			fi
		fi
	done
}

do_kernel_mkimage_ext2() {
	do_kernel_mkimage_ext234 "ext2"
}

do_kernel_mkimage_ext3() {
	do_kernel_mkimage_ext234 "ext3"
}

do_kernel_mkimage_ext4() {
	do_kernel_mkimage_ext234 "ext4"
}

#BOOTIMG_VOLUME_ID   ?= "boot"
BOOTIMG_EXTRA_SPACE ?= "${KERNEL_MKIMAGE_EXTRA_SPACE}"

# Stolen from image-live.bbclass (openembedded-core layer)
build_fat_img() {
	FATSOURCEDIR=$1
	FATIMG=$2
	VOLUME_ID=$3

	# Calculate the size required for the final image including the
	# data and filesystem overhead.
	# Sectors: 512 bytes
	#  Blocks: 1024 bytes

	# Determine the sector count just for the data
	SECTORS=$(expr $(du --apparent-size -ks ${FATSOURCEDIR} | cut -f 1) \* 2)

	# Account for the filesystem overhead. This includes directory
	# entries in the clusters as well as the FAT itself.
	# Assumptions:
	#   FAT32 (12 or 16 may be selected by mkdosfs, but the extra
	#   padding will be minimal on those smaller images and not
	#   worth the logic here to caclulate the smaller FAT sizes)
	#   < 16 entries per directory
	#   8.3 filenames only

	# 32 bytes per dir entry
	DIR_BYTES=$(expr $(find ${FATSOURCEDIR} | tail -n +2 | wc -l) \* 32)
	# 32 bytes for every end-of-directory dir entry
	DIR_BYTES=$(expr $DIR_BYTES + $(expr $(find ${FATSOURCEDIR} -type d | tail -n +2 | wc -l) \* 32))
	# 4 bytes per FAT entry per sector of data
	FAT_BYTES=$(expr $SECTORS \* 4)
	# 4 bytes per FAT entry per end-of-cluster list
	FAT_BYTES=$(expr $FAT_BYTES + $(expr $(find ${FATSOURCEDIR} -type d | tail -n +2 | wc -l) \* 4))

	# Use a ceiling function to determine FS overhead in sectors
	DIR_SECTORS=$(expr $(expr $DIR_BYTES + 511) / 512)
	# There are two FATs on the image
	FAT_SECTORS=$(expr $(expr $(expr $FAT_BYTES + 511) / 512) \* 2)
	SECTORS=$(expr $SECTORS + $(expr $DIR_SECTORS + $FAT_SECTORS))

	# Determine the final size in blocks accounting for some padding
	BLOCKS=$(expr $(expr $SECTORS / 2) + ${BOOTIMG_EXTRA_SPACE})

	# mkdosfs will sometimes use FAT16 when it is not appropriate,
	# resulting in a boot failure from SYSLINUX. Use FAT32 for
	# images larger than 512MB, otherwise let mkdosfs decide.
	if [ $(expr $BLOCKS / 1024) -gt 512 ]; then
		FATSIZE="-F 32"
	fi

	# mkdosfs will fail if ${FATIMG} exists. Since we are creating an
	# new image, it is safe to delete any previous image.
	if [ -e ${FATIMG} ]; then
		rm ${FATIMG}
	fi

	mkdosfs ${FATSIZE} -n ${VOLUME_ID} ${MKDOSFS_EXTRAOPTS} -C ${FATIMG} \
		${BLOCKS}

	# Copy FATSOURCEDIR recursively into the image file directly
	mcopy -i ${FATIMG} -s ${FATSOURCEDIR}/* ::/
}

vfat_assemble_image() {
	local imageType=${1}
	local targetName=${2}
	local fstype="vfat"

	local FSDIR="${KERNEL_MKIMAGE_WORKDIR}-${fstype}/fs"
	local OUTPUT_ARTIFACT="${KERNEL_MKIMAGE_WORKDIR}-${fstype}/${imageType}${KERNEL_MKIMAGE_ARTIFACT_NAME}.${fstype}"
	local OUTPUT_LINK="${KERNEL_MKIMAGE_WORKDIR}-${fstype}/${imageType}${KERNEL_MKIMAGE_ARTIFACT_LINK_NAME}.${fstype}"

	bbdebug 1 "Creating ${fstype} image for ${imageType}..."

	rm -rf "${FSDIR}"
	mkdir -p "${FSDIR}"
	cp "${B}/arch/${ARCH}/boot/${imageType}" "${FSDIR}/${targetName}"

	if [ "${KERNEL_MKIMAGE_INCLUDE_DEVICETREE}" = "1" ]; then
		for dtbf in ${KERNEL_DEVICETREE}; do
			dtb=`normalize_dtb "$dtbf"`
			dtb_ext=${dtb##*.}
			dtb_base_name=`basename $dtb .$dtb_ext`
			dtb_path=`get_real_dtb_path_in_kernel "$dtb"`
			install -m 0644 $dtb_path ${FSDIR}/$dtb_base_name.$dtb_ext
		done
	fi

	build_fat_img "${FSDIR}" "${OUTPUT_ARTIFACT}" "kernel"
	if [ -n "${OUTPUT_LINK}" ]; then
		rm -f ${OUTPUT_LINK}
		ln -snf $(basename ${OUTPUT_ARTIFACT}) "${OUTPUT_LINK}"
	fi
}

do_kernel_mkimage_vfat() {
	for imageType in ${KERNEL_IMAGETYPES}; do
		vfat_assemble_image "${imageType}"
		if [ "${imageType}" = "fitImage" ]; then
			if [ -n "${INITRAMFS_IMAGE}" ]; then
				vfat_assemble_image "${imageType}-${INITRAMFS_IMAGE}" "${imageType}"
			fi
		fi
	done
}

kernel_do_deploy:append() {
	for type in ${KERNEL_MKIMAGE_TYPES}; do
		for imageType in ${KERNEL_IMAGETYPES}; do
			for f in ${KERNEL_MKIMAGE_WORKDIR}-${type}/*.${type}; do
				install -m 0644 ${f} "$deployDir/"
			done
		done
	done
}
