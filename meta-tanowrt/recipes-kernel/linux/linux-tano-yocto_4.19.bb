#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2019-2020 Tano Systems LLC. All rights reserved.
# Authors: Anton Kikin <a.kikin@tano-systems.com>
#
# Yocto Linux kernel 4.19 (standard)
#
KERNEL_SRC_URI ?= "git://git.yoctoproject.org/linux-yocto.git"

KERNEL_SRC_BRANCH:qemuarm ?= "v4.19/standard/arm-versatile-926ejs"
KERNEL_SRC_BRANCH:qemuarm64 ?= "v4.19/standard/qemuarm64"
KERNEL_SRC_BRANCH:qemux86 ?= "v4.19/standard/base"
KERNEL_SRC_BRANCH:qemux86-64 ?= "v4.19/standard/base"
KERNEL_SRC_BRANCH ?= "v4.19/standard/base"

KERNEL_SRC_SRCREV_machine:qemuarm ?= "4d9d89763cde098e4a8b879e8c831e35a5f39ae6"
KERNEL_SRC_SRCREV_machine:qemuarm64 ?= "2ba1fa4d5068982e785527ef8ad1a8b658f0add1"
KERNEL_SRC_SRCREV_machine:qemux86 ?= "2ba1fa4d5068982e785527ef8ad1a8b658f0add1"
KERNEL_SRC_SRCREV_machine:qemux86-64 ?= "2ba1fa4d5068982e785527ef8ad1a8b658f0add1"
KERNEL_SRC_SRCREV ?= "2ba1fa4d5068982e785527ef8ad1a8b658f0add1"

LINUX_VERSION ?= "4.19.87"
LINUX_KERNEL_TYPE ?= "standard"
PV = "${LINUX_VERSION}+git${SRCPV}"

# Append to the MACHINE_KERNEL_PR so that a new SRCREV will cause a rebuild
MACHINE_KERNEL_PR:append = "tano9"
PR = "${MACHINE_KERNEL_PR}"

require recipes-kernel/linux/linux-tano.inc
require recipes-kernel/linux/linux-tano-yocto.inc

# Look in the generic major.minor directory for files
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-4.19:"

YOCTO_KERNEL_CACHE_BRANCH = "yocto-4.19"
YOCTO_KERNEL_CACHE_SRCREV = "4f5d761316a9cf14605e5d0cc91b53c1b2e9dc6a"

SECTION = "kernel"
DESCRIPTION = "Yocto Linux kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"
