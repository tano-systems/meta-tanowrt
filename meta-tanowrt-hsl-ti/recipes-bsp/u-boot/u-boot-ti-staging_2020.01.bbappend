#
PR_append = ".tano0"

# Look in the generic major.minor directory for files
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append = " file://${UBOOT_MACHINE}"

# Patches
SRC_URI_append_ti33x = "\
	file://0001-am33xx-Fix-dram-initialization.patch \
	file://0002-am335x-Changes-in-DDR-init-procedure-to-Support-Samsung-K4B2G1646EBIH9-memory-chip.patch \
"

SRC_URI_append_ti33x = "\
	file://0003-Customize-default-environment.patch \
"

do_configure_prepend() {
	cp ${WORKDIR}/${UBOOT_MACHINE} ${S}/configs/${UBOOT_MACHINE}
}
