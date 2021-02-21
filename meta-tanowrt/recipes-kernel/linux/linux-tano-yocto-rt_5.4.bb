#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2020-2021 Tano Systems LLC. All rights reserved.
# Authors: Anton Kikin <a.kikin@tano-systems.com>
#
# Yocto Linux kernel 5.4 (preempt-rt)
#
KERNEL_SRC_URI ?= "git://git.yoctoproject.org/linux-yocto.git"
KERNEL_SRC_BRANCH ?= "v5.4/standard/preempt-rt/base"
KERNEL_SRC_SRCREV ?= "0406e600800a40015d02b16ee6a4a46c6673c66f"

LINUX_VERSION ?= "5.4.98"
LINUX_KERNEL_TYPE ?= "preempt-rt"
PV = "${LINUX_VERSION}+git${SRCPV}"

# Append to the MACHINE_KERNEL_PR so that a new SRCREV will cause a rebuild
MACHINE_KERNEL_PR_append = "tano0"
PR = "${MACHINE_KERNEL_PR}"

require recipes-kernel/linux/linux-tano.inc
require recipes-kernel/linux/linux-tano-yocto.inc

# Look in the generic major.minor directory for files
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-5.4:"

YOCTO_KERNEL_CACHE_BRANCH = "yocto-5.4"
YOCTO_KERNEL_CACHE_SRCREV = "4f6d6c23cc8ca5d9c39b1efc2619b1dfec1ef2bc"

SECTION = "kernel"
DESCRIPTION = "Yocto Linux kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"
