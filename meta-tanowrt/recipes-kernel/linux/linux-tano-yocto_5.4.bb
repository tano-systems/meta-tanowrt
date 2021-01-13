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

KERNEL_SRC_SRCREV_machine_qemuarm ?= "888fe3a6f7776f5732c3c4cf4c862447e646c25e"
KERNEL_SRC_SRCREV_machine_qemuarm64 ?= "4f2b484a791fac88262922aa26ddd5ac3df9720f"
KERNEL_SRC_SRCREV_machine_qemux86 ?= "4f2b484a791fac88262922aa26ddd5ac3df9720f"
KERNEL_SRC_SRCREV_machine_qemux86-64 ?= "4f2b484a791fac88262922aa26ddd5ac3df9720f"
KERNEL_SRC_SRCREV ?= "4f2b484a791fac88262922aa26ddd5ac3df9720f"

LINUX_VERSION ?= "5.4.85"
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
YOCTO_KERNEL_CACHE_SRCREV = "1c358e19696827b594de26a221f110fc2647dfa8"

SECTION = "kernel"
DESCRIPTION = "Yocto Linux kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"
