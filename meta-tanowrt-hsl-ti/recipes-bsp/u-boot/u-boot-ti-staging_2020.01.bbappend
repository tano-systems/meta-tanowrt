#
PR_append_ti33x = ".tano0"
PR_append_omap-a15 = ".tano1"

# Look in the generic major.minor directory for files
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

inherit uboot-defconfig-copy

# Patches
SRC_URI_append_ti33x = "\
	file://0001-am33xx-Fix-dram-initialization.patch \
	file://0002-am335x-Changes-in-DDR-init-procedure-to-Support-Samsung-K4B2G1646EBIH9-memory-chip.patch \
	file://0003-ti-am335x-Customize-default-environment.patch \
"

SRC_URI_append_omap-a15 = "\
	file://0004-ti-am574x-Customize-default-environment.patch \
"
