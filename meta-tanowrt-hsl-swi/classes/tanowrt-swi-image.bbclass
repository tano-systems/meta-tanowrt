#
# SPDX-License-Identifier: MIT
# Copyright (c) 2020 Tano Systems LLC. All rights reserved.
#
LEGATO_IMG_NAME = "${PN}-${MACHINE}-leimage"

create_deploy_dir_image() {
    mkdir -p "${DEPLOY_DIR_IMAGE}"
}

ROOTFS_PREPROCESS_COMMAND:append = "create_deploy_dir_image"

IMAGE_ROOTFS_SIZE ?= "8192"

DEPENDS += " \
        virtual/lk \
        mtd-utils-native \
        cryptsetup-native \
        ima-evm-utils-native \
        libarchive-native"

inherit ubi-image
inherit dm-verity-hash

do_image_4k.ubifs[depends] += "mtd-utils-native:do_populate_sysroot"
do_image_2k.ubifs[depends] += "mtd-utils-native:do_populate_sysroot"

BUILD_IMAGE_2K := "${@bb.utils.contains('IMAGE_FSTYPES', '2k.ubifs', '1', '0', d)}"
BUILD_IMAGE_4K := "${@bb.utils.contains('IMAGE_FSTYPES', '4k.ubifs', '1', '0', d)}"
BUILD_IMAGE_SQUASHFS := "${@bb.utils.contains('IMAGE_FSTYPES', 'squashfs', '1', '0', d)}"

create_ubinize_config() {
    local cfg_path=$1
    local rootfs_type=$2

    if [[ "${DM_VERITY_ENCRYPT}" = "on" ]]; then
        local dm_hash_path=$3
        local dm_root_hash_path=$4
    fi

    local rootfs_path="${IMGDEPLOYDIR}/${IMAGE_NAME}.rootfs.${rootfs_type}"

    echo \[sysfs_volume\] > $cfg_path
    echo mode=ubi >> $cfg_path
    echo image="$rootfs_path" >> $cfg_path
    echo vol_id=0 >> $cfg_path

    if [[ "${rootfs_type}" = "squashfs" ]]; then
        echo vol_type=static >> $cfg_path
    else
        echo vol_type=dynamic >> $cfg_path
        echo vol_size="${UBI_ROOTFS_SIZE}" >> $cfg_path
    fi

    echo vol_name=rootfs >> $cfg_path
    echo vol_alignment=1 >> $cfg_path

    if [[ "${DM_VERITY_ENCRYPT}" = "on" ]]; then
        # dm-verity hash tree table followed after the rootfs
        # Init scripts will check this partition during boot up
        if [[ -s ${dm_hash_path} ]]; then
            echo >> $cfg_path
            echo \[hash_volume\] >> $cfg_path
            echo mode=ubi >> $cfg_path
            echo image="$dm_hash_path" >> $cfg_path
            echo vol_id=1 >> $cfg_path
            echo vol_type=static >> $cfg_path
            echo vol_name=rootfs_hs >> $cfg_path
            echo vol_alignment=1 >> $cfg_path
        fi

        #  dm-verity root hash is following the hash
        if [[ -s ${dm_root_hash_path} ]]; then
            echo >> $cfg_path
            echo \[rh_volume\] >> $cfg_path
            echo mode=ubi >> $cfg_path
            echo image="$dm_root_hash_path" >> $cfg_path
            echo vol_id=2 >> $cfg_path
            echo vol_type=static >> $cfg_path
            echo vol_name=rootfs_rhs >> $cfg_path
            echo vol_alignment=1 >> $cfg_path
        fi
    fi
}

create_reset_ubinize_config() {
    local cfg_path=$1
    local rootfs_type=$2

    echo > $cfg_path
    echo \[userapp_vol0-volume\] >> $cfg_path
    # We do not need an image for now, leave UBI partition empty.
    echo mode=ubi >> $cfg_path
    echo vol_id=0 >> $cfg_path
    echo vol_size="${UBI_USERAPP_SIZE}" >> $cfg_path
    echo vol_type=dynamic >> $cfg_path
    echo vol_name=userapp_vol0 >> $cfg_path
    echo vol_flags=autoresize >> $cfg_path
}

prepare_ubi_ps() {
    local page_size=$1
    local image_types=$2
    local image_type=
    local ubinize_cfg=
    local image_path=
    local dm_hash_path=
    local dm_hash_filename=
    local dm_root_hash_path=
    local ubi_path=
    local ubi_link_path=

    mkdir -p "${IMGDEPLOYDIR}"

    for rootfs_type in ${image_types}; do
        image_type=${rootfs_type}
        if [[ "${rootfs_type}" != "squashfs" ]]; then
            image_type=${page_size}.${rootfs_type}
        fi

        ubinize_cfg="${IMGDEPLOYDIR}/${IMAGE_NAME}${IMAGE_NAME_SUFFIX}.${image_type}.ubinize.cfg"
        image_path="${IMGDEPLOYDIR}/${IMAGE_NAME}${IMAGE_NAME_SUFFIX}.${image_type}"

        # Now Dm-verity only work with squashfs
        if [ "${DM_VERITY_ENCRYPT}" = "on" -a "${rootfs_type}" = "squashfs" ]; then
            dm_hash_path="${image_path}.hash"
            dm_hash_filename="${dm_hash_path}.txt"
            dm_root_hash_path="${image_path}.rhash"

            if [[ ! -e ${dm_hash_filename} ]]; then
                create_dm_verity_hash "${image_path}" "${dm_hash_path}" "${dm_hash_filename}"
                get_dm_root_hash "${dm_root_hash_path}" "${dm_hash_filename}"
            fi
            create_ubinize_config ${ubinize_cfg} ${image_type} ${dm_hash_path} ${dm_root_hash_path}
        else
            create_ubinize_config ${ubinize_cfg} ${image_type}
        fi

        ubi_path="${IMGDEPLOYDIR}/${IMAGE_NAME}.${rootfs_type}.${page_size}.ubi"
        ubi_link_path="${IMGDEPLOYDIR}/${IMAGE_LINK_NAME}.${rootfs_type}.${page_size}.ubi"

        create_ubi_image $page_size $ubinize_cfg $ubi_path $ubi_link_path
    done

    ubi_path="${IMGDEPLOYDIR}/${IMAGE_NAME}.reset.ubifs.${page_size}.ubi"
    create_reset_ubinize_config ${ubinize_cfg}
    create_ubi_image $page_size $ubinize_cfg $ubi_path
}

# Create UBI images
prepare_ubi() {
	if [ "${BUILD_IMAGE_2K}" = "1" ]; then
		prepare_ubi_ps '2k' 'ubifs'
	fi

	if [ "${BUILD_IMAGE_4K}" = "1" ]; then
		prepare_ubi_ps '4k' 'ubifs'
	fi

	if [ "${BUILD_IMAGE_SQUASHFS}" = "1" ]; then
		prepare_ubi_ps '4k' 'squashfs'
	fi

	cd ${IMGDEPLOYDIR}

	# Default image (no bs suffix) to 4k + squashfs
	ubi_link_path_def="${IMAGE_LINK_NAME}.squashfs.4k.ubi"
	ubi_link_path_def_2k="${IMAGE_LINK_NAME}.squashfs.2k.ubi"

	ubi_link_path="${IMAGE_LINK_NAME}.4k.ubi"
	ubi_link_path_2k="${IMAGE_LINK_NAME}.2k.ubi"

	rm -f $ubi_link_path $ubi_link_path_2k
	ln -s $ubi_link_path_def $ubi_link_path

	if [ "${BUILD_IMAGE_2K}" = "1" ]; then
		ln -s $ubi_link_path_def_2k $ubi_link_path_2k
	fi

	ubi_link_path="${IMAGE_LINK_NAME}.ubi"
	rm -f $ubi_link_path
	ln -s $ubi_link_path_def $ubi_link_path
}

do_image_complete[postfuncs] += "prepare_ubi"

default_rootfs_ps() {
	cd ${IMGDEPLOYDIR}

	if [ "${BUILD_IMAGE_2K}" = "1" && "${BUILD_IMAGE_4K}" = "0" ]; then
		# Default rootfs to ubi for 2k
		ln -sf ${IMAGE_LINK_NAME}.2k.ubi  ${IMAGE_LINK_NAME}.2k.default
		
		# Default rootfs to 2k
		ln -sf ${IMAGE_LINK_NAME}.2k.default ${IMAGE_LINK_NAME}.default
	else
		# Default rootfs to ubi for 4k
		ln -sf ${IMAGE_LINK_NAME}.4k.ubi  ${IMAGE_LINK_NAME}.4k.default

		# Default rootfs to 4k
		ln -sf ${IMAGE_LINK_NAME}.4k.default ${IMAGE_LINK_NAME}.default
	fi
}

do_image_complete[postfuncs] += "default_rootfs_ps"

DEPENDS += "cwetool-native"

generate_cwe_pid() {
    PID=$1
    PLATFORM=$2
    PAGE_SIZE=$3
    OUTPUT=$4
    BOOT_IMAGE=$5
    KERNEL_IMAGE=$6
    ROOTFS_IMAGE=$7
    LEGATO_IMAGE=$8
    OVERLAY_RESET=$9

    unset OVERLAY_RESET_IMG
    unset OVERLAY_RESET_OPT
    unset OVERLAY_RESET_V_FILE
    unset OVERLAY_RESET_V_OPT

    unset KERNEL_IMG
    unset KERNEL_OPT
    unset KERNEL_V_FILE
    unset KERNEL_V_OPT

    unset ROOTFS_IMG
    unset ROOTFS_OPT
    unset ROOTFS_V_FILE
    unset ROOTFS_V_OPT

    unset LK_IMG
    unset LK_OPT
    unset LK_V_FILE
    unset LK_V_OPT

    unset LEGATO_IMG
    unset LEGATO_OPT
    unset LEGATO_V_OPT
    unset LEGATO_V_FILE

    if [ "$BOOT_IMAGE" = "default" ]; then
        LK_IMG=$(readlink -f ${DEPLOY_DIR_IMAGE}/appsboot.mbn)

        echo "Bootloader: $LK_IMG"
        LK_OPT="-fbt"

    elif [ "$BOOT_IMAGE" = "rw" ]; then
        LK_IMG=$(readlink -f ${DEPLOY_DIR_IMAGE}/appsboot_rw.mbn)

        echo "Bootloader: $LK_IMG"
        LK_OPT="-fbt"

    elif [ "$BOOT_IMAGE" = "rw_ima" ]; then
        LK_IMG=$(readlink -f ${DEPLOY_DIR_IMAGE}/appsboot_rw_ima.mbn)

        echo "Bootloader: $LK_IMG"
        LK_OPT="-fbt"

    fi

    if [ -n "$LK_IMG" ]; then
        LK_V_FILE=$(readlink -f ${DEPLOY_DIR_IMAGE}/lk.version)
        if [ -e "$LK_V_FILE" ]; then
            echo "Bootloader version: $(cat $LK_V_FILE)"
            LK_V_OPT="-vfbt"
        else
            unset LK_V_FILE
        fi
    fi

    if [ "$KERNEL_IMAGE" = "true" ]; then
        KERNEL_IMG="${DEPLOY_DIR_IMAGE}/boot-yocto-mdm9x28.${PAGE_SIZE}.img"

        echo "Kernel: $KERNEL_IMG"
        KERNEL_OPT="-kernel"

        KERNEL_V_FILE=$(readlink -f ${DEPLOY_DIR_IMAGE}/kernel.version)
        if [ -e "$KERNEL_V_FILE" ]; then
            echo "Kernel version: $(cat $KERNEL_V_FILE)"
            KERNEL_V_OPT="-vkernel"
        else
            unset KERNEL_V_FILE
        fi
    fi

    if [ "$ROOTFS_IMAGE" = "true" ]; then
        ROOTFS_IMG="${DEPLOY_DIR_IMAGE}/${PN}-${MACHINE}.${PAGE_SIZE}.default"
        if ! [ -e "$ROOTFS_IMG" ]; then
            ROOTFS_IMG="${DEPLOY_DIR_IMAGE}/${PN}-${MACHINE}.${PAGE_SIZE}.ubi"
        fi

        echo "Rootfs: $ROOTFS_IMG"
        ROOTFS_OPT="-rfs"

        ROOTFS_V_FILE=$(readlink -f ${DEPLOY_DIR_IMAGE}/rootfs.version)
        if [ -e "$ROOTFS_V_FILE" ]; then
            echo "Rootfs version: $(cat $ROOTFS_V_FILE)"
            ROOTFS_V_OPT="-vrfs"
        else
            unset ROOTFS_V_FILE
        fi
    fi

    if [ "$LEGATO_IMAGE" = "true" ]; then
        LEGATO_IMG=$(readlink -f ${DEPLOY_DIR_IMAGE}/${LEGATO_IMG_NAME}.${TARGET}.default)
        if ! [ -e "$LEGATO_IMG" ]; then
            exit 1
        fi

        echo "Legato: $LEGATO_IMG"
        LEGATO_OPT="-ufs"

        LEGATO_V_FILE=$(readlink -f ${DEPLOY_DIR_IMAGE}/${LEGATO_IMG_NAME}.version)
        if [ -e "$LEGATO_V_FILE" ]; then
            echo "Legato version: $(cat $LEGATO_V_FILE)"
            LEGATO_V_OPT="-vufs"
        else
            unset LEGATO_V_FILE
        fi
    fi

    if [ "$OVERLAY_RESET" = "true" ]; then
        OVERLAY_RESET_OPT="-uapp"
        OVERLAY_RESET_IMG="${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.reset.ubifs.${PAGE_SIZE}.ubi"
    fi

    # get production name
    PROD_NAME=${MACHINE}

    yoctocwetool.sh \
        -prod ${PROD_NAME#swi-*-} \
        -pid $PID \
        -platform $PLATFORM \
        -o $OUTPUT \
        $LK_OPT $LK_IMG \
        $LK_V_OPT $LK_V_FILE \
        $KERNEL_OPT $KERNEL_IMG \
        $KERNEL_V_OPT $KERNEL_V_FILE \
        $ROOTFS_OPT $ROOTFS_IMG \
        $ROOTFS_V_OPT $ROOTFS_V_FILE \
        $LEGATO_OPT $LEGATO_IMG \
        $LEGATO_V_OPT $LEGATO_V_FILE \
        $OVERLAY_RESET_OPT $OVERLAY_RESET_IMG \
        $OVERLAY_RESET_V_OPT $OVERLAY_RESET_V_FILE
}

generate_cwe_symlink() {
	local dst=$1
	local src=$2

	if [ -f "${DEPLOY_DIR_IMAGE}/${src}" ]; then
		bbnote "Creating symlink: ${dst} -> ${src}"
		[ -h "${dst}" ] && rm -f "${dst}"
		ln -sf "${src}" "${dst}"
	else
		bbnote "Skipping symlink, source does not exists: ${dst} -> ${src}"
	fi
}

generate_cwe_target() {
    TARGET=$1

    PAGE_SIZE[0]=4k
    if [ "${BUILD_IMAGE_2K}" = "1" ]; then
        PAGE_SIZE[1]=2k
    fi

    PLATFORM='9X15'

    case $TARGET in
        ar7)    PID[0]='A911' ;;
        ar86)   PID[0]='A911' ;;
        wp7)    PID[0]='9X15' ;;
        wp85)   PID[1]='Y912'
                PAGE_SIZE[1]=2k ;;
        ar759x) PID[0]='9X40'
                PLATFORM[0]='9X40' ;;
        ar758x) PID[0]='9X28'
                PLATFORM='9X28' ;;
        wp76xx | wp77xx)
                PID[0]='Y921'
                PLATFORM='9X28' ;;
        *)
            echo "Unknown product '$TARGET'"
            exit 1
        ;;
    esac

    echo "Generating CWE package for $TARGET ($PAGE_SIZE, kernel, lk, rootfs, legato = ${LEGATO_BUILD})"
    generate_cwe_pid $PID $PLATFORM $PAGE_SIZE \
        ${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.${TARGET}.spk \
        default true true ${LEGATO_BUILD} false
    generate_cwe_symlink \
        ${DEPLOY_DIR_IMAGE}/${IMAGE_LINK_NAME}.${TARGET}.spk \
        ${IMAGE_NAME}.${TARGET}.spk

    echo "Generating CWE package for $TARGET ($PAGE_SIZE, kernel, lk, rootfs, legato = ${LEGATO_BUILD}, overlay reset)"
    generate_cwe_pid $PID $PLATFORM $PAGE_SIZE \
        ${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.${TARGET}.clean.spk \
        default true true ${LEGATO_BUILD} true
    generate_cwe_symlink \
        ${DEPLOY_DIR_IMAGE}/${IMAGE_LINK_NAME}.${TARGET}.clean.spk \
        ${IMAGE_NAME}.${TARGET}.clean.spk
}

do_generate_cwe[depends] += "cwetool-native:do_populate_sysroot"
do_generate_cwe[depends] += "${PN}:do_install"
do_generate_cwe[depends] += "${PN}:do_image_complete"
do_generate_cwe[depends] += "virtual/kernel:do_build"

do_generate_cwe() {
    for target in ${LEGATO_ROOTFS_TARGETS}; do
        generate_cwe_target $target
    done
}

addtask generate_cwe before do_build
