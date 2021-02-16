#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2021 Tano Systems LLC. All rights reserved.
# Authors: Anton Kikin <a.kikin@tano-systems.com>
#
# Yocto Linux kernel 5.10 (standard)
#
KERNEL_SRC_URI ?= "git://git.yoctoproject.org/linux-yocto.git"

KERNEL_SRC_BRANCH_qemuarm ?= "v5.10/standard/arm-versatile-926ejs"
KERNEL_SRC_BRANCH_qemuarm64 ?= "v5.10/standard/qemuarm64"
KERNEL_SRC_BRANCH_qemux86 ?= "v5.10/standard/base"
KERNEL_SRC_BRANCH_qemux86-64 ?= "v5.10/standard/base"
KERNEL_SRC_BRANCH ?= "v5.10/standard/base"

KERNEL_SRC_SRCREV_machine_qemuarm ?= "3ea1273f3112f558eb8176bfc67b8dd65a3b16ac"
KERNEL_SRC_SRCREV_machine_qemuarm64 ?= "cdca78778415b4b3bd64e8390ee8adf04bf7e17a"
KERNEL_SRC_SRCREV_machine_qemux86 ?= "cdca78778415b4b3bd64e8390ee8adf04bf7e17a"
KERNEL_SRC_SRCREV_machine_qemux86-64 ?= "cdca78778415b4b3bd64e8390ee8adf04bf7e17a"
KERNEL_SRC_SRCREV ?= "cdca78778415b4b3bd64e8390ee8adf04bf7e17a"

LINUX_VERSION ?= "5.10.12"
LINUX_KERNEL_TYPE ?= "standard"
PV = "${LINUX_VERSION}+git${SRCPV}"

# Append to the MACHINE_KERNEL_PR so that a new SRCREV will cause a rebuild
MACHINE_KERNEL_PR_append = "tano0"
PR = "${MACHINE_KERNEL_PR}"

require recipes-kernel/linux/linux-tano.inc
require recipes-kernel/linux/linux-tano-yocto.inc

# Look in the generic major.minor directory for files
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-5.10:"

YOCTO_KERNEL_CACHE_BRANCH = "yocto-5.10"
YOCTO_KERNEL_CACHE_SRCREV = "5833ca701711d487c9094bd1efc671e8ef7d001e"

SECTION = "kernel"
DESCRIPTION = "Yocto Linux kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"
