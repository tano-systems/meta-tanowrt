#
# SPDX-License-Identifier: MIT
#
# Baikal-M Linux Kernel
# Based on Baikal-M SDK 5.2 (20210608)
#
# This file Copyright (C) 2021 Tano Systems LLC. All rights reserved.
# Authors: Anton Kikin <a.kikin@tano-systems.com>
#
SECTION = "kernel"
DESCRIPTION = "Linux kernel for Baikal-M platforms"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

LINUX_VERSION ?= "5.4.114"
LINUX_KERNEL_TYPE ?= "standard"
PV = "${LINUX_VERSION}"

KERNEL_SRC_URI ?= "https://cdn.kernel.org/pub/linux/kernel/v5.x/linux-${LINUX_VERSION}.tar.xz"
KERNEL_SRC_PROTOCOL ?= "https"
KERNEL_SRC_BRANCH ?= ""
KERNEL_SRC_SRCREV ?= ""

SRC_URI[machine.sha256sum] = "be98f087cb58f23bc5edb8ee80a4794a569d48049d75b1a8eaef9f80fede9557"

SRC_URI += "\
	file://0001-baikal-m-Add-support-for-Baikal-M-platforms.patch \
	file://0002-baikal-m-Add-defconfigs-for-Baikal-M-platforms.patch \
	file://0003-baikal_vdu_connector-Add-include-for-module.h.patch \
	file://0004-gpu-arm-midgard-Add-TX011-SW-99002-r26p0-01rel0-sour.patch \
	file://0005-gpu-arm-midgard-Fix-building-in-Yocto.patch \
"

# Append to the MACHINE_KERNEL_PR so that a new SRCREV will cause a rebuild
MACHINE_KERNEL_PR_append = "tano0"
PR = "${MACHINE_KERNEL_PR}"

KERNEL_IMAGETYPE ?= "Image"

require recipes-kernel/linux/linux-tano.inc
require recipes-kernel/linux/linux-tano-baikal.inc

# Look in the generic major.minor directory for files
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-5.4:"

S = "${WORKDIR}/linux-${LINUX_VERSION}"
