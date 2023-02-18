#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2023 Tano Systems LLC. All rights reserved.
# Authors: Anton Kikin <a.kikin@tano-systems.com>
#
# RT Linux kernel for Rockchip SoC's
#

SECTION = "kernel"
DESCRIPTION = "Linux kernel 5.10-rt for Rockchip SoC's"
SUMMARY = "Linux kernel 5.10-rt for Rockchip SoC's"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

KERNEL_SRC_URI ?= "git://github.com/rockchip-linux/kernel.git"
KERNEL_SRC_BRANCH ?= "develop-5.10-rt53"
KERNEL_SRC_NOBRANCH ?= "0"
KERNEL_SRC_PROTOCOL ?= "https"
KERNEL_SRC_SRCREV ?= "cbaedd4be381bca40d03e85829f9d9b6692e41cf"

LINUX_VERSION ?= "5.10.110-rt53"
LINUX_KERNEL_TYPE ?= "preempt-rt"
PV = "${LINUX_VERSION}+git${SRCPV}"

# Append to the MACHINE_KERNEL_PR so that a new SRCREV will cause a rebuild
MACHINE_KERNEL_PR:append = "tano0"
PR = "${MACHINE_KERNEL_PR}"

require recipes-kernel/linux/linux-tano.inc
require recipes-kernel/linux/linux-tano-rockchip.inc

# Look in the generic major.minor directory for files
FILESEXTRAPATHS:prepend := "${THISDIR}/linux-tano-rockchip-5.10/files:"
FILESEXTRAPATHS:prepend := "${THISDIR}/linux-tano-rockchip-5.10/patches:"
FILESEXTRAPATHS:prepend := "${THISDIR}/linux-tano-rockchip-5.10/devicetree:"
FILESEXTRAPATHS:prepend := "${THISDIR}/linux-tano-rockchip-5.10/features:"

FILESEXTRAPATHS:prepend := "${THISDIR}/linux-tano-rockchip-rt-5.10/files:"
FILESEXTRAPATHS:prepend := "${THISDIR}/linux-tano-rockchip-rt-5.10/patches:"
FILESEXTRAPATHS:prepend := "${THISDIR}/linux-tano-rockchip-rt-5.10/devicetree:"
FILESEXTRAPATHS:prepend := "${THISDIR}/linux-tano-rockchip-rt-5.10/features:"

SRC_URI:append = "\
	file://config-fixup.scc \
	file://config-rt.scc \
"

KERNEL_FEATURES:append = "\
	config-fixup.scc \
	config-rt.scc \
"

SRC_URI += "\
	file://0001-net-rkwifi-Fix-include-path-error.patch \
	file://0002-init-do_mounts.c-Retry-all-fs-after-failed-to-mount-.patch \
	file://0006-HACK-drm-rockchip-Force-enable-legacy-cursor-update.patch \
	file://0007-HACK-drm-rockchip-Prefer-non-cluster-overlay-planes.patch \
	file://1002-Build-overlays.patch \
	file://1003-GPIO-add-named-gpio-exports.patch \
	file://1004-rtc-hym8563-Add-updated-by-Rockchip-driver.patch \
"
