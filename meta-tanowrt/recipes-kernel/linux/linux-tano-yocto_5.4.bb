#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2020-2021 Tano Systems LLC. All rights reserved.
# Authors: Anton Kikin <a.kikin@tano-systems.com>
#
# Yocto Linux kernel 5.4 (standard)
#
KERNEL_SRC_URI ?= "git://git.yoctoproject.org/linux-yocto.git"

KERNEL_SRC_BRANCH_qemuarm ?= "v5.4/standard/arm-versatile-926ejs"
KERNEL_SRC_BRANCH_qemuarm64 ?= "v5.4/standard/qemuarm64"
KERNEL_SRC_BRANCH_qemux86 ?= "v5.4/standard/base"
KERNEL_SRC_BRANCH_qemux86-64 ?= "v5.4/standard/base"
KERNEL_SRC_BRANCH ?= "v5.4/standard/base"

KERNEL_SRC_SRCREV_machine_qemuarm ?= "28bc6b294bb1e49da671b2848234f9011efcad88"
KERNEL_SRC_SRCREV_machine_qemuarm64 ?= "2d0a4ea86fe97f13a4bc2a92a097e4edb51d737d"
KERNEL_SRC_SRCREV_machine_qemux86 ?= "2d0a4ea86fe97f13a4bc2a92a097e4edb51d737d"
KERNEL_SRC_SRCREV_machine_qemux86-64 ?= "2d0a4ea86fe97f13a4bc2a92a097e4edb51d737d"
KERNEL_SRC_SRCREV ?= "2d0a4ea86fe97f13a4bc2a92a097e4edb51d737d"

LINUX_VERSION ?= "5.4.98"
LINUX_KERNEL_TYPE ?= "standard"
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
