#
# SPDX-License-Identifier: MIT
# Copyright (c) 2020-2021 Tano Systems LLC. All rights reserved.
#
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

PR:append = ".1"

# Use STAGING_KERNEL_DIR for linux headers
do_configure[depends] += "virtual/kernel:do_shared_workdir"

LK_DEBUG = "0"

LK_REPO = "git://github.com/legatoproject/lk.git;protocol=https;branch=mdm9x28le20-swi"
SRCREV = "caebd0a5af84b33fefe7856e14f5daadf8189b84"

# Patches
SRC_URI += "\
	file://0001-Disable-kernel-quiet-mode.patch \
	file://0002-Fix-kernel-cmdline-appending.patch \
	file://0003-Fix-linker-errors-when-using-GCC-10.2.patch \
"

INSANE_SKIP:${PN} += "already-stripped"

LK_TARGET = "mdm9607"

inherit android-signing
LK_HASH_MODE = "android_signing"

EXTRA_OEMAKE += "LINUX_KERNEL_DIR='${STAGING_KERNEL_DIR}'"
EXTRA_OEMAKE:append = " SIGNED_KERNEL=1"
CC:append = " -Wno-error=format-security"

do_configure:prepend() {
    if [ -d "${STAGING_KERNEL_DIR}/arch/arm/mach-msm/sierra" ]; then
        rm -f ${S}/app/aboot/sierra
        ln -sf ${STAGING_KERNEL_DIR}/arch/arm/mach-msm/sierra ${S}/app/aboot/sierra
    fi
}

do_install:prepend() {
    if [ -f "${B}/../../appsboot.mbn" ] ; then
        install ${B}/../../appsboot.mbn ${B}/build-${LK_TARGET}/
    fi
    if [ -f "${B}/../../appsboot_rw.mbn" ] ; then
        install ${B}/../../appsboot_rw.mbn ${B}/build-${LK_TARGET}/
    fi
    if [ -f "${B}/../../appsboot_rw_ima.mbn" ] ; then
        install ${B}/../../appsboot_rw_ima.mbn ${B}/build-${LK_TARGET}/
    fi
}
