#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2020 Tano Systems LLC. All rights reserved.
# Authors: Anton Kikin <a.kikin@tano-systems.com>
#
# Linux kernel from Processor SDK Linux 06.03.00.106 (preempt-rt)
#
SECTION = "kernel"
DESCRIPTION = "Linux kernel for TI devices"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

KERNEL_SRC_URI ?= "git://git.ti.com/git/processor-sdk/processor-sdk-linux.git"
KERNEL_SRC_BRANCH ?= "processor-sdk-linux-rt-4.19.y"
KERNEL_SRC_PROTOCOL ?= "https"
KERNEL_SRC_SRCREV ?= "a242ccf3f13f03d41d521411ce2cc09775c873a2"

LINUX_VERSION ?= "4.19.94-rt39"
LINUX_KERNEL_TYPE ?= "preempt-rt"
PV = "${LINUX_VERSION}+git${SRCPV}"

# Append to the MACHINE_KERNEL_PR so that a new SRCREV will cause a rebuild
MACHINE_KERNEL_PR_append = "tano3"
PR = "${MACHINE_KERNEL_PR}"

require recipes-kernel/linux/linux-tano.inc
require recipes-kernel/linux/linux-tano-ti.inc

# Look in the generic major.minor directory for files
FILESEXTRAPATHS_prepend := "${THISDIR}/linux-tano-ti-sdk-4.19:"
