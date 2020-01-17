#
# Copyright (C) 2020, Tano Systems, All rights reserved
# Authors: Anton Kikin <a.kikin@tano-systems.com>
#
# TI staging Linux kernel (RT)
#
SECTION = "kernel"
DESCRIPTION = "Linux kernel for TI devices (RT)"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

# Look in the generic major.minor directory for files
FILESEXTRAPATHS_prepend := "${THISDIR}/linux-tano-ti-staging-5.4:"

# Append to the MACHINE_KERNEL_PR so that a new SRCREV will cause a rebuild
MACHINE_KERNEL_PR_append = "tano0"
PR = "${MACHINE_KERNEL_PR}"

KERNEL_SRC_URI ?= "git://git.ti.com/ti-linux-kernel/ti-linux-kernel.git"
KERNEL_SRC_BRANCH ?= "ti-rt-linux-5.4.y"
KERNEL_SRC_PROTOCOL ?= "git"
KERNEL_SRC_SRCREV ?= "8591cbda22d3b1a85bb8fae1195ce06323949bd3"

LINUX_VERSION ?= "5.4.40"
LINUX_KERNEL_TYPE ?= "preempt-rt"
PV = "${LINUX_VERSION}+git${SRCPV}"

LINUX_VERSION_FULL = "${@kernel_full_version("${LINUX_VERSION}")}"
LINUX_VERSION_SHORT = "${@oe.utils.trim_version("${LINUX_VERSION}", 2)}"

require recipes-kernel/linux/tano-kernel-cache-5.4.inc
require recipes-kernel/linux/linux-tano-ti.inc
require recipes-kernel/linux/linux-tano.inc
