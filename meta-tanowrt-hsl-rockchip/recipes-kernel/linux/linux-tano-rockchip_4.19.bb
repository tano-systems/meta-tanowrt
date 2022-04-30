#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2022 Tano Systems LLC. All rights reserved.
# Authors: Anton Kikin <a.kikin@tano-systems.com>
#
# Linux kernel for Rockchip SoC's
#

SECTION = "kernel"
DESCRIPTION = "Linux kernel 4.19 for Rockchip SoC's"
SUMMARY = "Linux kernel 4.19 for Rockchip SoC's"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

KERNEL_SRC_URI ?= "git://github.com/JeffyCN/mirrors.git"
KERNEL_SRC_BRANCH ?= "kernel-4.19-2022_01_18"
KERNEL_SRC_NOBRANCH ?= "1"
KERNEL_SRC_PROTOCOL ?= "https"
KERNEL_SRC_SRCREV ?= "40b753b965cb50f446606e0867c6f13ec7995eb2"

LINUX_VERSION ?= "4.19.219"
LINUX_KERNEL_TYPE ?= "standard"
PV = "${LINUX_VERSION}+git${SRCPV}"

# Append to the MACHINE_KERNEL_PR so that a new SRCREV will cause a rebuild
MACHINE_KERNEL_PR_append = "tano3"
PR = "${MACHINE_KERNEL_PR}"

require recipes-kernel/linux/linux-tano.inc
require recipes-kernel/linux/linux-tano-rockchip.inc

# Look in the generic major.minor directory for files
FILESEXTRAPATHS_prepend := "${THISDIR}/linux-tano-rockchip-4.19/files:"
FILESEXTRAPATHS_prepend := "${THISDIR}/linux-tano-rockchip-4.19/patches:"
FILESEXTRAPATHS_prepend := "${THISDIR}/linux-tano-rockchip-4.19/devicetree:"
FILESEXTRAPATHS_prepend := "${THISDIR}/linux-tano-rockchip-4.19/features:"

SRC_URI_append = "\
	file://config-fixup.scc \
"

KERNEL_FEATURES_append = "\
	config-fixup.scc \
"

SRC_URI += "\
	file://0006-HACK-drm-rockchip-Force-enable-legacy-cursor-update.patch \
	file://0007-HACK-drm-rockchip-Prefer-non-cluster-overlay-planes.patch \
	file://1000-arm64_increasing_DMA_block_memory_allocation_to_2048.patch \
	file://1001-rk3308-Fix-main-dtsi.patch \
	file://1002-Build-overlays.patch \
	file://1003-GPIO-add-named-gpio-exports.patch \
	file://1004-rtc-hym8563-Add-updated-by-Rockchip-driver.patch \
	file://1005-rtkbtusb-Add-modified-Rockchip-driver.patch \
	file://1006-rtl8723du-Add-driver.patch \
"

# Not required for 4.19.219
SRC_URI_remove = "file://550-loop-better-discard-for-block-devices.patch"

# This 4.19 kernel has backported wireguard in-kernel support
KERNEL_FEATURES_append = " features/kernel-5.6+/wireguard/wireguard.scc"
