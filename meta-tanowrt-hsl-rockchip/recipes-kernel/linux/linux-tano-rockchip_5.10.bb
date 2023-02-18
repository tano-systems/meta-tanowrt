#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2023 Tano Systems LLC. All rights reserved.
# Authors: Anton Kikin <a.kikin@tano-systems.com>
#
# Linux kernel for Rockchip SoC's
#

SECTION = "kernel"
DESCRIPTION = "Linux kernel 5.10 for Rockchip SoC's"
SUMMARY = "Linux kernel 5.10 for Rockchip SoC's"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

KERNEL_SRC_URI ?= "git://github.com/rockchip-linux/kernel.git"
KERNEL_SRC_BRANCH ?= "develop-5.10"
KERNEL_SRC_NOBRANCH ?= "0"
KERNEL_SRC_PROTOCOL ?= "https"
KERNEL_SRC_SRCREV ?= "a4423c56f430bfc7c75dbb68d4a9a1a0ef62b371"

LINUX_VERSION ?= "5.10.110"
LINUX_KERNEL_TYPE ?= "standard"
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

SRC_URI:append = "\
	file://config-fixup.scc \
"

KERNEL_FEATURES:append = "\
	config-fixup.scc \
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
