#
# SPDX-License-Identifier: MIT
# Copyright (c) 2020 Tano Systems LLC. All rights reserved.
#
DESCRIPTION = "Little Kernel bootloader"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/\
${LICENSE};md5=0835ade698e0bcf8506ecda2f7b4f302"
HOMEPAGE = "https://www.codeaurora.org/cgit/quic/la/kernel/lk"
PROVIDES = "virtual/lk"

PR = "tn-0"

SECURITY_FLAGS_PATCH = "file://0001-arm-fix-dprintf-format.patch"

# safe defaults for recipes/systems that do not set up these variables
LK_REPO_NAME ?= "git"

FILESPATH:prepend = "${THISDIR}:"

SRC_URI = "${LK_REPO} ${SECURITY_FLAGS_PATCH}"

S = "${WORKDIR}/${LK_REPO_NAME}"
B = "${WORKDIR}/build"

LK_TARGET ?= "mdm9615"

COMPATIBLE_MACHINE = "(swi-mdm.*)"

# Debug levels you could have. Default is critical.
# 0 - CRITICAL
# 1 - INFO
# 2 - SPEW
LK_DEBUG ?= "0"

EXTRA_OEMAKE = "-j 1 \
                TOOLCHAIN_PREFIX='${TARGET_PREFIX}' \
                TOOLCHAIN_OPTIONS='${TOOLCHAIN_OPTIONS}' \
                ${LK_TARGET} \
                DEBUG=${LK_DEBUG} \
                BOOTLOADER_OUT='${B}' \
                ARCH='${TARGET_ARCH}' \
                CC='${CC}' \
                ${@oe.utils.conditional('ARM_FLOAT_ABI', 'hard', 'ENABLE_HARD_FPU=1', '', d)} \
                ENABLE_IMA='${ENABLE_IMA}' \
                ENABLE_FX30='${ENABLE_FX30}' \
                IMA_KERNEL_CMDLINE_OPTIONS='${IMA_KERNEL_CMDLINE_OPTIONS}'"

do_tag_lk() {
    cd ${S}

    # Use FW_VERSION as lk version if available, otherwise provide a x.x.x_<hash> as version
    if [ -n "${FW_VERSION}" ]; then
        LK_VERSION="${FW_VERSION}"
    else
        # We remove the sierra_lkversion.h to avoid this file to be counted in sha1
        LK_VERSION="${PV}-${PR}_"`for file in $(find -type f -not -regex '.*\(pc\|git\|build-\|patches\).*'); do \
            sha256sum $file; done | \
            sort | \
            grep -v sierra_lkversion.h | \
            awk '{print $1}' | \
            sha256sum | \
            cut -c 1-10 -`""
    fi

    mkdir -p ${B}/build-${LK_TARGET}
    echo "#define LKVERSION  \"${LK_VERSION}\"" > ${B}/build-${LK_TARGET}/sierra_lkversion.h
    echo "${LK_VERSION} $(date +'%Y/%m/%d %H:%M:%S')" > ${B}/build-${LK_TARGET}/lk.version
}

addtask tag_lk before do_compile after do_configure

do_compile[dirs] = "${S}"
do_compile:prepend() {
    export NOECHO=""
    export LIBGCC="$(${CC} ${CFLAGS} -print-libgcc-file-name)"
}

do_install() {
    install -d ${D}/boot
    install ${B}/build-${LK_TARGET}/appsboot.mbn ${D}/boot
    if [ -f "${B}/build-${LK_TARGET}/appsboot.raw" ] ; then
        install ${B}/build-${LK_TARGET}/appsboot.raw ${D}/boot
    fi
    if [ -f "${B}/build-${LK_TARGET}/appsboot_rw.mbn" ] ; then
        install ${B}/build-${LK_TARGET}/appsboot_rw.mbn ${D}/boot
    fi
    if [ -f "${B}/build-${LK_TARGET}/appsboot_rw_ima.mbn" ] ; then
        install ${B}/build-${LK_TARGET}/appsboot_rw_ima.mbn ${D}/boot
    fi
    cp ${B}/build-${LK_TARGET}/lk.version ${D}/boot
}

do_deploy() {
    install -d ${DEPLOY_DIR_IMAGE}
    if [ -f "${D}/boot/appsboot.mbn" ] ; then
        install ${D}/boot/appsboot.mbn ${DEPLOY_DIR_IMAGE}
    fi
    if [ -f "${D}/boot/appsboot.raw" ] ; then
        install ${D}/boot/appsboot.raw ${DEPLOY_DIR_IMAGE}
    fi
    if [ -f "${D}/boot/appsboot_rw.mbn" ] ; then
        install ${D}/boot/appsboot_rw.mbn ${DEPLOY_DIR_IMAGE}
    fi
    if [ -f "${D}/boot/appsboot_rw_ima.mbn" ] ; then
        install ${D}/boot/appsboot_rw_ima.mbn ${DEPLOY_DIR_IMAGE}
    fi
    cp ${D}/boot/lk.version ${DEPLOY_DIR_IMAGE}/lk.version
}

do_deploy[dirs] = "${S}"
addtask deploy before do_package_stage after do_install

PACKAGE_STRIP = "no"

FILES:${PN} = "/boot"
FILES:${PN}-dbg = "/boot/.debug"

LK_HASH_MODE = "no_hash"

DEPENDS += "${@oe.utils.conditional('LK_HASH_MODE', 'dual_system', "openssl-native python-native", '', d)}"

add_hash_dual_system() {
    IMAGE_NAME=$1
    cd ${B}
    if [ -f "${B}/../../$IMAGE_NAME.mbn" ] ; then
        python ${THISDIR}/files/add_hash_segment.py image=${B}/../../$IMAGE_NAME.mbn imageType=APBL of=${B}/build-${LK_TARGET}/unsigned
        install ${B}/build-${LK_TARGET}/unsigned/$IMAGE_NAME.umbn ${B}/../../$IMAGE_NAME.mbn
    fi
}

add_hash_android_signing() {
    IMAGE_NAME=$1
    cd ${B}
    if [ -f "${B}/../../$IMAGE_NAME.mbn" ] ; then
        android_signature_add /aboot ${B}/../../$IMAGE_NAME.mbn ${B}/../../$IMAGE_NAME.mbn.signed verity
        install ${B}/../../$IMAGE_NAME.mbn ${B}/build-${LK_TARGET}/$IMAGE_NAME.mbn
    fi
}

add_hash_segment() {
    case ${LK_HASH_MODE} in
        dual_system)
            add_hash_dual_system $1 ;;
        android_signing)
            add_hash_android_signing $1 ;;
        no_hash)
            echo "no signed images for boot" ;;
        *)
            exit 1 ;;
    esac
}

do_add_hash() {
    add_hash_segment appsboot
    add_hash_segment appsboot_rw
    add_hash_segment appsboot_rw_ima
}

addtask add_hash after do_compile before do_install
