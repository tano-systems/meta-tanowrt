#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2019-2020 Tano Systems LLC. All rights reserved.
# Authors: Anton Kikin <a.kikin@tano-systems.com>
#
# Based on kernel recipes from meta-swi layer
# (https://gitlab.com/legato.io/meta-swi.git)
#

PR_append = ".0"

COMPATIBLE_MACHINE = "(swi-mdm9x28)"

KERNEL_MACHINE_INCLUDE_mangoh-green-wp7607 ?= "recipes-kernel/linux/linux-tano-swi-mangoh-green-wp7607.inc"
KERNEL_MACHINE_INCLUDE ?= "recipes-kernel/linux/linux-tano-swi-${MACHINE}.inc"
include ${KERNEL_MACHINE_INCLUDE}

inherit android-signing
inherit kernel-src-install

do_install_append() {
    kernel_src_install
}

# Allow merging of multiple kernel configs. This must be space
# separated list of files using absolute paths.
KBUILD_DEFCONFIG_SNIPPETS ?= ""

KERNEL_EXTRA_ARGS += "O=${B}"

do_bootimg[depends] += "mkbootimg-native:do_populate_sysroot"
do_deploy[depends] += "dtbtool-native:do_populate_sysroot mkbootimg-native:do_populate_sysroot"

DEPENDS += "ima-support-tools-native gcc"

# Use this to include the IMA kernel key into the trusted keyring
IMA_INCLUDE_KERNEL_KEY ?= "true"

do_kernel_metadata_prepend() {
    rm -f ${B}/.config
    rm -f ${B}/.config.old
    cp ${WORKDIR}/defconfig ${B}/.config
}

KCONFIG_MODE = "alldefconfig"

do_compile_append() {
    oe_runmake dtbs ${KERNEL_EXTRA_ARGS}
}

# Note that @{DATETIME} isn't a BitBake variable expansion;
# see do_bootimg for the crude substitution we do with sed.
# Originally we had the ${DATETIME} variable here.
# What this "fake variable" achieves is a stable base hash across reparses:
# BitBake only ever sees the literal text @{DATETIME},
# so the hash doesn't change. In Yocto 1.7 we didn't see a
# problem, but newer Yocto diagnoses situations when the inputs
# to a task appear to change upon a second parse, changing the
# hash, which occurs if ${DATETIME} is mixed in.
BOOTIMG_NAME_2k ?= "boot-yocto-mdm9x28-@{DATETIME}.2k"
BOOTIMG_NAME_4k ?= "boot-yocto-mdm9x28-@{DATETIME}.4k"

MACHINE_KERNEL_BASE = "0x80000000"
MACHINE_KERNEL_TAGS_OFFSET = "0x81E00000"

gen_master_dtb() {
    master_dtb_name=$1
    page_size=$2

    ${STAGING_BINDIR_NATIVE}/dtbtool \
        ${B}/arch/arm/boot/dts/qcom/ \
        -s $page_size \
        -o ${DEPLOYDIR}/$master_dtb_name \
        -p ${B}/scripts/dtc/ \
        -v

    if ! [ -e "${DEPLOYDIR}/$master_dtb_name" ]; then
        echo "Unable to generate $master_dtb_name"
        exit 1
    fi
}

do_deploy_append() {
    kernel_img=${DEPLOYDIR}/${KERNEL_IMAGETYPE}
    if [ "${INITRAMFS_IMAGE_BUNDLE}" -eq 1 ]; then
        kernel_img=${DEPLOYDIR}/${KERNEL_IMAGETYPE}-initramfs-${MACHINE}.bin
    fi
    kernel_img=$(readlink -f $kernel_img)
    ls -al $kernel_img

    set -xe

    ver=$(sed -r 's/#define UTS_RELEASE "(.*)"/\1/' ${B}/include/generated/utsrelease.h)
    dtb_files=$(find ${B}/arch/arm/boot/dts/qcom -iname "*${BASEMACHINE_QCOM}*.dtb" | awk -F/ '{print $NF}' | awk -F[.][d] '{print $1}')

    mkdir -p ${DEPLOYDIR}/dtb/qcom

    # Create separate images with dtb appended to zImage for all targets.
    # Also ship each dtb file individually
    for d in ${dtb_files}; do
        targets=$(echo ${d#${BASEMACHINE_QCOM}-})
        cat $kernel_img ${B}/arch/arm/boot/dts/qcom/${d}.dtb > ${B}/arch/arm/boot/dts/qcom/dtb-zImage-${ver}-${targets}.dtb
        cp ${B}/arch/arm/boot/dts/qcom/${d}.dtb ${DEPLOYDIR}/dtb/qcom/
    done

    gen_master_dtb masterDTB.2k 2048
    gen_master_dtb masterDTB.4k 4096
    cp ${B}/vmlinux ${DEPLOYDIR}/vmlinux
}

gen_bootimg() {
    image_flags=$1
    image_name=$2
    image_link=$3
    master_dtb_name=$4
    page_size=$5
    ret_ok=0
    ret_err=1

    set -xe

    echo "Kernel command line IMA options: [${IMA_KERNEL_CMDLINE_OPTIONS}]"
    echo "Kernel command line: [${KERNEL_BOOT_OPTIONS}]"

    kernel_img=${DEPLOYDIR}/${KERNEL_IMAGETYPE}
    if [ "${INITRAMFS_IMAGE_BUNDLE}" -eq 1 ]; then
        kernel_img=${DEPLOYDIR}/${KERNEL_IMAGETYPE}-initramfs-${MACHINE}.bin
    fi
    kernel_img=$(readlink -f $kernel_img)
    ls -al $kernel_img

    # No initramfs
    ${STAGING_BINDIR_NATIVE}/mkbootimg \
        --dt ${DEPLOYDIR}/$master_dtb_name \
        --kernel $kernel_img \
        --ramdisk "NONE" \
        --cmdline "${KERNEL_BOOT_OPTIONS}" \
        --pagesize $page_size \
        --base ${MACHINE_KERNEL_BASE} \
        --tags-addr ${MACHINE_KERNEL_TAGS_OFFSET} \
        --output ${DEPLOY_DIR_IMAGE}/${image_name}.img

    ln -sf ${image_name}.img ${DEPLOY_DIR_IMAGE}/${image_link}.img
}

do_bootimg() {
    date=$(date +"%Y%m%d%H%M%S")
    date_version=$(date +'%Y/%m/%d %H:%M:%S')

    image_name_2k=$(echo ${BOOTIMG_NAME_2k} | sed -e s/@{DATETIME}/$date/)
    image_name_4k=$(echo ${BOOTIMG_NAME_4k} | sed -e s/@{DATETIME}/$date/)

    gen_bootimg "${MKBOOTIMG_IMAGE_FLAGS_2K}" $image_name_2k boot-yocto-mdm9x28.2k masterDTB.2k 2048
    if [ $? -ne 0 ] ; then exit 1 ; fi
    gen_bootimg "${MKBOOTIMG_IMAGE_FLAGS_4K}" $image_name_4k boot-yocto-mdm9x28.4k masterDTB.4k 4096
    if [ $? -ne 0 ] ; then exit 1 ; fi
    ln -sf $image_name_4k.img ${DEPLOY_DIR_IMAGE}/boot-yocto-mdm9x28.img

    install ${DEPLOY_DIR_IMAGE}/boot-yocto-mdm9x28.2k.img ${DEPLOY_DIR_IMAGE}/boot-yocto-mdm9x28.2k.img.unsigned
    install ${DEPLOY_DIR_IMAGE}/boot-yocto-mdm9x28.4k.img ${DEPLOY_DIR_IMAGE}/boot-yocto-mdm9x28.4k.img.unsigned

    # sign the image:
    android_signature_add /boot ${DEPLOY_DIR_IMAGE}/boot-yocto-mdm9x28.2k.img.unsigned ${DEPLOY_DIR_IMAGE}/boot-yocto-mdm9x28.2k.img.signed verity
    android_signature_add /boot ${DEPLOY_DIR_IMAGE}/boot-yocto-mdm9x28.4k.img.unsigned ${DEPLOY_DIR_IMAGE}/boot-yocto-mdm9x28.4k.img.signed verity

    ln -sf boot-yocto-mdm9x28.4k.img ${DEPLOY_DIR_IMAGE}/boot-yocto-mdm9x28.img.unsigned
    echo "${PV}-${PR} $date_version" > ${DEPLOY_DIR_IMAGE}/kernel.version
}

do_add_mbnhdr_and_hash() {
    # Append "mbn header" and "hash of kernel" to kernel image for data integrity check
    # "mbnhdr_data" is 40bytes mbn header data in hex string format
    mbnhdr_data="06000000030000000000000028000000200000002000000048000000000000004800000000000000"
    # Transfer data from hex string format to binary format "0x06,0x00,0x00,..." and write to a file.
    echo -n $mbnhdr_data | sed 's/\([0-9A-F]\{2\}\)/\\\\\\x\1/gI' | xargs printf > ${DEPLOY_DIR_IMAGE}/boot_mbnhdr
    openssl dgst -sha256 -binary ${DEPLOY_DIR_IMAGE}/boot-yocto-mdm9x28.2k.img > ${DEPLOY_DIR_IMAGE}/boot_hash.2k
    openssl dgst -sha256 -binary ${DEPLOY_DIR_IMAGE}/boot-yocto-mdm9x28.4k.img > ${DEPLOY_DIR_IMAGE}/boot_hash.4k
    cat ${DEPLOY_DIR_IMAGE}/boot_mbnhdr ${DEPLOY_DIR_IMAGE}/boot_hash.2k >> ${DEPLOY_DIR_IMAGE}/boot-yocto-mdm9x28.2k.img
    cat ${DEPLOY_DIR_IMAGE}/boot_mbnhdr ${DEPLOY_DIR_IMAGE}/boot_hash.4k >> ${DEPLOY_DIR_IMAGE}/boot-yocto-mdm9x28.4k.img
}

addtask bootimg after do_deploy before do_build
addtask do_add_mbnhdr_and_hash after do_bootimg before do_build

# The following was removed from the kernel class between Yocto 1.7 and 2.2.
# We need our non-sanitized kernel headers in the sysroot it because our apps
# (Legato) need them.
python sysroot_stage_all () {
    oe.path.copyhardlinktree(d.expand("${D}${KERNEL_SRC_PATH}"), d.expand("${SYSROOT_DESTDIR}${KERNEL_SRC_PATH}"))
}

# Note: keep this empty
LINUX_VERSION_EXTENSION = ""
