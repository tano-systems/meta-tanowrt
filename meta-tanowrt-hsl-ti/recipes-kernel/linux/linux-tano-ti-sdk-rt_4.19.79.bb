#
# Copyright (C) 2020, Tano Systems, All rights reserved
# Authors: Anton Kikin <a.kikin@tano-systems.com>
#
# Linux kernel from Processor SDK Linux (preempt-rt)
#
SECTION = "kernel"
DESCRIPTION = "Linux kernel for TI devices"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

# Look in the generic major.minor directory for files
FILESEXTRAPATHS_prepend := "${THISDIR}/linux-tano-ti-sdk-4.19:"

# Append to the MACHINE_KERNEL_PR so that a new SRCREV will cause a rebuild
MACHINE_KERNEL_PR_append = "tano3"
PR = "${MACHINE_KERNEL_PR}"

KERNEL_SRC_URI ?= "git://git.ti.com/git/processor-sdk/processor-sdk-linux.git"
KERNEL_SRC_BRANCH ?= "processor-sdk-linux-rt-4.19.y"
KERNEL_SRC_PROTOCOL ?= "https"
KERNEL_SRC_SRCREV ?= "76cdb71a54cbd12fb13e92a4a4d4976ffcfd425b"

LINUX_VERSION ?= "4.19.79"
LINUX_KERNEL_TYPE ?= "preempt-rt"
PV = "${LINUX_VERSION}+git${SRCPV}"

LINUX_VERSION_FULL = "${LINUX_VERSION}"
LINUX_VERSION_SHORT = "${@oe.utils.trim_version("${LINUX_VERSION}", 2)}"

require recipes-kernel/linux/tano-kernel-cache-4.19.inc
require recipes-kernel/linux/linux-tano-ti.inc
require recipes-kernel/linux/linux-tano.inc
