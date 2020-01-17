#
PR = "ti0"
require recipes-bsp/u-boot/u-boot-ti.inc
require u-boot-ti-sdk-common.inc

# Look in the generic major.minor directory for files
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append = " file://${UBOOT_MACHINE}"

# Patches
SRC_URI_append_ti33x = "\
	file://0001-am33xx-Fix-dram-initialization.patch \
	file://0002-am335x-Changes-in-DDR-init-procedure-to-Support-Samsung-K4B2G1646EBIH9-memory-chip.patch \
"

do_configure_prepend() {
	cp ${WORKDIR}/${UBOOT_MACHINE} ${S}/configs/${UBOOT_MACHINE}
}
