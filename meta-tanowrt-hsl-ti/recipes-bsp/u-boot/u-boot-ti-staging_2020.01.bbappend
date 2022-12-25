#
# SPDX-License-Identifier: MIT
# Copyright (c) 2020-2021 Tano Systems LLC. All rights reserved.
#
PR:append:ti33x = ".tano2"
PR:append:omap-a15 = ".tano3"

SRCREV = "6b5b982e98dde63832b3a68c84f990e63f732584"

# Look in the generic major.minor directory for files
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}_2020.01:${THISDIR}/${PN}:"

inherit u-boot-defconfig-copy
inherit u-boot-spl-multiple

require u-boot-ti-tano.inc

# Patches
SRC_URI:append:ti33x = "\
	file://0001-am33xx-Fix-dram-initialization.patch \
	file://0002-am335x-Changes-in-DDR-init-procedure-to-Support-Samsung-K4B2G1646EBIH9-memory-chip.patch \
	file://0100-ti-am335x-Adjust-SYS_MMC_ENV_DEV-for-SD-eMMC-boot.patch \
	file://0101-ti-am335x-Reduce-default-environment-parameters.patch \
"

SRC_URI:append:omap-a15 = "\
	file://0200-ti-am574x-Adjust-SYS_MMC_ENV_DEV-for-SD-eMMC-boot.patch \
	file://0201-ti-am574x-Reduce-default-environment-parameters.patch \
"
