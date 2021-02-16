#
# SPDX-License-Identifier: MIT
# Copyright (c) 2020 Tano Systems LLC. All rights reserved.
#
PR = "ti0"
require recipes-bsp/u-boot/u-boot-ti.inc
require u-boot-ti-sdk-common.inc

inherit u-boot-spl-multiple

# Look in the generic major.minor directory for files
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:${THISDIR}/u-boot-ti:"

inherit u-boot-defconfig-copy

require u-boot-ti-tano.inc

# Patches
SRC_URI_append_ti33x = "\
	file://0001-am33xx-Fix-dram-initialization.patch \
	file://0002-am335x-Changes-in-DDR-init-procedure-to-Support-Samsung-K4B2G1646EBIH9-memory-chip.patch \
"
