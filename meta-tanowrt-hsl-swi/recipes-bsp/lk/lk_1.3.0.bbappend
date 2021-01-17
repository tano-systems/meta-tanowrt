#
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

PR_append = ".0"

# Use STAGING_KERNEL_DIR for linux headers
do_configure[depends] += "virtual/kernel:do_shared_workdir"

LK_DEBUG = "0"

LK_REPO = "git://github.com/legatoproject/lk.git;protocol=https;branch=mdm9x28le20-swi"
SRCREV = "caebd0a5af84b33fefe7856e14f5daadf8189b84"

# Patches
SRC_URI += "\
	file://0001-disable-kernel-quiet-mode.patch \
	file://0002-fix-cmdline-appending.patch \
"

INSANE_SKIP_${PN} += "already-stripped"

LK_TARGET = "mdm9607"

inherit android-signing
LK_HASH_MODE = "android_signing"

EXTRA_OEMAKE += "LINUX_KERNEL_DIR='${STAGING_KERNEL_DIR}'"
EXTRA_OEMAKE_append = " SIGNED_KERNEL=1"
CC_append += " -Wno-error=format-security"

do_configure_prepend() {
    if [ -d "${STAGING_KERNEL_DIR}/arch/arm/mach-msm/sierra" ]; then
        rm -f ${S}/app/aboot/sierra
        ln -sf ${STAGING_KERNEL_DIR}/arch/arm/mach-msm/sierra ${S}/app/aboot/sierra
    fi
}

do_install_prepend() {
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
