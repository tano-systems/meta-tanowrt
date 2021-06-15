#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2020-2021 Tano Systems LLC. All rights reserved.
# Authors: Anton Kikin <a.kikin@tano-systems.com>
#
# TI staging Linux kernel
#
SECTION = "kernel"
DESCRIPTION = "Linux kernel for TI devices"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

KERNEL_SRC_URI ?= "git://git.ti.com/ti-linux-kernel/ti-linux-kernel.git"
KERNEL_SRC_BRANCH ?= "ti-linux-5.4.y"
KERNEL_SRC_PROTOCOL ?= "git"
KERNEL_SRC_SRCREV ?= "023faefa70274929bff92dc41167b007f7523792"

LINUX_VERSION ?= "5.4.106"
LINUX_KERNEL_TYPE ?= "standard"
PV = "${LINUX_VERSION}+git${SRCPV}"

# Append to the MACHINE_KERNEL_PR so that a new SRCREV will cause a rebuild
MACHINE_KERNEL_PR_append = "tano0"
PR = "${MACHINE_KERNEL_PR}"

require recipes-kernel/linux/linux-tano.inc
require recipes-kernel/linux/linux-tano-ti.inc

# Look in the generic major.minor directory for files
FILESEXTRAPATHS_prepend := "${THISDIR}/linux-ti-staging-5.4:"
