#
# Copyright (C) 2019-2020, Tano Systems, All rights reserved
# Authors: Anton Kikin <a.kikin@tano-systems.com>
#
# Yocto Linux kernel 4.19 (standard)
#

# Look in the generic major.minor directory for files
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-4.19:"

# Append to the MACHINE_KERNEL_PR so that a new SRCREV will cause a rebuild
MACHINE_KERNEL_PR_append = "tano8"
PR = "${MACHINE_KERNEL_PR}"

KERNEL_SRC_URI ?= "git://git.yoctoproject.org/linux-yocto.git"

KERNEL_SRC_BRANCH_qemuarm ?= "v4.19/standard/arm-versatile-926ejs"
KERNEL_SRC_BRANCH_qemux86 ?= "v4.19/standard/base"
KERNEL_SRC_BRANCH_qemux86-64 ?= "v4.19/standard/base"
KERNEL_SRC_BRANCH ?= "v4.19/standard/base"

KERNEL_SRC_SRCREV_machine_qemuarm ?= "4d9d89763cde098e4a8b879e8c831e35a5f39ae6"
KERNEL_SRC_SRCREV_machine_qemux86 ?= "2ba1fa4d5068982e785527ef8ad1a8b658f0add1"
KERNEL_SRC_SRCREV_machine_qemux86-64 ?= "2ba1fa4d5068982e785527ef8ad1a8b658f0add1"
KERNEL_SRC_SRCREV ?= "2ba1fa4d5068982e785527ef8ad1a8b658f0add1"

LINUX_VERSION ?= "4.19.87"
LINUX_KERNEL_TYPE ?= "standard"
PV = "${LINUX_VERSION}+git${SRCPV}"

YOCTO_KERNEL_CACHE_BRANCH = "yocto-4.19"
YOCTO_KERNEL_CACHE_SRCREV = "4f5d761316a9cf14605e5d0cc91b53c1b2e9dc6a"

LINUX_VERSION_SHORT = "${@oe.utils.trim_version("${LINUX_VERSION}", 2)}"

require recipes-kernel/linux/tano-kernel-cache-4.19.inc
require recipes-kernel/linux/linux-tano-yocto.inc
require recipes-kernel/linux/linux-tano.inc

SECTION = "kernel"
DESCRIPTION = "Yocto Linux kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"
