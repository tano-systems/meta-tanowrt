#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2019-2020 Tano Systems LLC. All rights reserved.
# Authors: Anton Kikin <a.kikin@tano-systems.com>
#
# Yocto Linux kernel 4.19 (preempt-rt)
#
KERNEL_SRC_URI ?= "git://git.yoctoproject.org/linux-yocto.git"
KERNEL_SRC_BRANCH ?= "v4.19/standard/preempt-rt/base"
KERNEL_SRC_SRCREV ?= "d3fb163023de0a5a57a021e58b55976f68142fc0"

LINUX_VERSION ?= "4.19.87"
LINUX_KERNEL_TYPE ?= "preempt-rt"
PV = "${LINUX_VERSION}+git${SRCPV}"

# Append to the MACHINE_KERNEL_PR so that a new SRCREV will cause a rebuild
MACHINE_KERNEL_PR_append = "tano9"
PR = "${MACHINE_KERNEL_PR}"

require recipes-kernel/linux/linux-tano.inc
require recipes-kernel/linux/linux-tano-yocto.inc

# Look in the generic major.minor directory for files
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-4.19:"

YOCTO_KERNEL_CACHE_BRANCH = "yocto-4.19"
YOCTO_KERNEL_CACHE_SRCREV = "4f5d761316a9cf14605e5d0cc91b53c1b2e9dc6a"

SECTION = "kernel"
DESCRIPTION = "Yocto Linux kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"
