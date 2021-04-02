#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2021 Tano Systems LLC. All rights reserved.
# Authors: Anton Kikin <a.kikin@tano-systems.com>
#
# Linux kernel for Microchip ARM SoCs (aka AT91)
#

SECTION = "kernel"
DESCRIPTION = "Linux kernel for Microchip ARM SoCs (aka AT91)"
SUMMARY = "Linux kernel for Microchip ARM SoCs (aka AT91)"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

KERNEL_SRC_URI ?= "git://github.com/linux4sam/linux-at91.git"
KERNEL_SRC_BRANCH ?= "linux-4.19-at91"
KERNEL_SRC_PROTOCOL ?= "git"
KERNEL_SRC_SRCREV ?= "046113c43823ff5d560887848d824b8b8b27059f"

LINUX_VERSION ?= "4.19.78"
LINUX_KERNEL_TYPE ?= "standard"
PV = "${LINUX_VERSION}+git${SRCPV}"

# Append to the MACHINE_KERNEL_PR so that a new SRCREV will cause a rebuild
MACHINE_KERNEL_PR_append = "tano0"
PR = "${MACHINE_KERNEL_PR}"

require recipes-kernel/linux/linux-tano.inc
require recipes-kernel/linux/linux-tano-at91.inc

# Look in the generic major.minor directory for files
FILESEXTRAPATHS_prepend := "${THISDIR}/linux-tano-at91-4.19/files:"
FILESEXTRAPATHS_prepend := "${THISDIR}/linux-tano-at91-4.19/patches:"
FILESEXTRAPATHS_prepend := "${THISDIR}/linux-tano-at91-4.19/devicetree:"

# Remove EXTRAVERSION definition from Makefile
SRC_URI_append = "\
	file://0001-Makefile-Clear-EXTRAVERSION.patch \
	file://0002-Revert-dsa-Remove-phydev-parameter-from-disable_port.patch \
"
