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

KERNEL_SRC_SRCREV_machine_qemuarm ?= "af93f3c5ef33dfb378d78b455f7193602ae732a7"
KERNEL_SRC_SRCREV_machine_qemuarm64 ?= "a673c127156c156a4a490ef66e0194d239cfbfa1"
KERNEL_SRC_SRCREV_machine_qemux86 ?= "a673c127156c156a4a490ef66e0194d239cfbfa1"
KERNEL_SRC_SRCREV_machine_qemux86-64 ?= "a673c127156c156a4a490ef66e0194d239cfbfa1"
KERNEL_SRC_SRCREV ?= "a673c127156c156a4a490ef66e0194d239cfbfa1"

LINUX_VERSION ?= "5.10.42"
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
YOCTO_KERNEL_CACHE_SRCREV = "422f8a09a856800f027bbae98dbab24cf3ae0f25"

SECTION = "kernel"
DESCRIPTION = "Yocto Linux kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"
