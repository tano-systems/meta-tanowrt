#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2021 Tano Systems LLC. All rights reserved.
# Authors: Anton Kikin <a.kikin@tano-systems.com>
#
# Yocto Linux kernel 5.10 (standard)
#
KERNEL_SRC_URI ?= "git://git.yoctoproject.org/linux-yocto.git"

KERNEL_SRC_BRANCH:qemuarm ?= "v5.10/standard/arm-versatile-926ejs"
KERNEL_SRC_BRANCH:qemuarm64 ?= "v5.10/standard/qemuarm64"
KERNEL_SRC_BRANCH:qemux86 ?= "v5.10/standard/base"
KERNEL_SRC_BRANCH:qemux86-64 ?= "v5.10/standard/base"
KERNEL_SRC_BRANCH ?= "v5.10/standard/base"

KERNEL_SRC_SRCREV_machine:qemuarm ?= "011882741f10bd0c725139baa383eb5a4d833bca"
KERNEL_SRC_SRCREV_machine:qemuarm64 ?= "098464b7c0c3d6f2a5b9226aab3245c3fcfb4797"
KERNEL_SRC_SRCREV_machine:qemux86 ?= "7dda2a9f69de7f80e572d38236896e97be79f39d"
KERNEL_SRC_SRCREV_machine:qemux86-64 ?= "7dda2a9f69de7f80e572d38236896e97be79f39d"
KERNEL_SRC_SRCREV ?= "7dda2a9f69de7f80e572d38236896e97be79f39d"

LINUX_VERSION ?= "5.10.70"
LINUX_KERNEL_TYPE ?= "standard"
PV = "${LINUX_VERSION}+git${SRCPV}"

# Append to the MACHINE_KERNEL_PR so that a new SRCREV will cause a rebuild
MACHINE_KERNEL_PR:append = "tano0"
PR = "${MACHINE_KERNEL_PR}"

require recipes-kernel/linux/linux-tano.inc
require recipes-kernel/linux/linux-tano-yocto.inc

# Look in the generic major.minor directory for files
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-5.10:"

YOCTO_KERNEL_CACHE_BRANCH = "yocto-5.10"
YOCTO_KERNEL_CACHE_SRCREV = "f8afd84b117f336477846b9e22178ebefb26c08d"

SECTION = "kernel"
DESCRIPTION = "Yocto Linux kernel"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"
