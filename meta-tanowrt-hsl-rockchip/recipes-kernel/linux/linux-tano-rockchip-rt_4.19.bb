#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2022 Tano Systems LLC. All rights reserved.
# Authors: Anton Kikin <a.kikin@tano-systems.com>
#
# RT Linux kernel for Rockchip SoC's
#

SECTION = "kernel"
DESCRIPTION = "Linux kernel 4.19-rt for Rockchip SoC's"
SUMMARY = "Linux kernel 4.19-rt for Rockchip SoC's"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

KERNEL_SRC_URI ?= "git://github.com/rockchip-linux/kernel.git"
KERNEL_SRC_BRANCH ?= "develop-4.19-rt87"
KERNEL_SRC_NOBRANCH ?= "0"
KERNEL_SRC_PROTOCOL ?= "https"
KERNEL_SRC_SRCREV ?= "9b4ca23f63bc3be618410fa5b3b6193d1265d522"

LINUX_VERSION ?= "4.19.209-rt87"
LINUX_KERNEL_TYPE ?= "preempt-rt"
PV = "${LINUX_VERSION}+git${SRCPV}"

# Append to the MACHINE_KERNEL_PR so that a new SRCREV will cause a rebuild
MACHINE_KERNEL_PR:append = "tano2"
PR = "${MACHINE_KERNEL_PR}"

require recipes-kernel/linux/linux-tano.inc
require recipes-kernel/linux/linux-tano-rockchip.inc

# Look in the generic major.minor directory for files
FILESEXTRAPATHS:prepend := "${THISDIR}/linux-tano-rockchip-4.19/files:"
FILESEXTRAPATHS:prepend := "${THISDIR}/linux-tano-rockchip-4.19/patches:"
FILESEXTRAPATHS:prepend := "${THISDIR}/linux-tano-rockchip-4.19/devicetree:"
FILESEXTRAPATHS:prepend := "${THISDIR}/linux-tano-rockchip-4.19/features:"

FILESEXTRAPATHS:prepend := "${THISDIR}/linux-tano-rockchip-rt-4.19/files:"
FILESEXTRAPATHS:prepend := "${THISDIR}/linux-tano-rockchip-rt-4.19/patches:"
FILESEXTRAPATHS:prepend := "${THISDIR}/linux-tano-rockchip-rt-4.19/devicetree:"
FILESEXTRAPATHS:prepend := "${THISDIR}/linux-tano-rockchip-rt-4.19/features:"

SRC_URI:append = "\
	file://config-fixup.scc \
	file://config-rt.scc \
"

KERNEL_FEATURES:append = "\
	config-fixup.scc \
	config-rt.scc \
"

SRC_URI += "\
	file://0006-HACK-drm-rockchip-Force-enable-legacy-cursor-update.patch \
	file://0007-HACK-drm-rockchip-Prefer-non-cluster-overlay-planes.patch \
	file://1000-zram-Fix-compilation-with-CONFIG_PREEMPT_RT_BASE-ena.patch \
	file://1001-sched-completion-Replace-swait_queue-by-wait_queue.patch \
	file://1002-Build-overlays.patch \
	file://1003-GPIO-add-named-gpio-exports.patch \
	file://1004-rtc-hym8563-Add-updated-by-Rockchip-driver.patch \
	file://1005-rtkbtusb-Add-modified-Rockchip-driver.patch \
	file://1006-rtl8723du-Add-driver.patch \
"

# Not required for 4.19.219
SRC_URI:remove = "file://550-loop-better-discard-for-block-devices.patch"

# This 4.19 kernel has backported wireguard in-kernel support
KERNEL_FEATURES:append = " features/kernel-5.6+/wireguard/wireguard.scc"
