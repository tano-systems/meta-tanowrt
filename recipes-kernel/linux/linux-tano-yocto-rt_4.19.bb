#
# Copyright (C) 2019, Tano Systems, All rights reserved
# Authors: Anton Kikin <a.kikin@tano-systems.com>
#
# Yocto Linux kernel 4.19 (preempt-rt)
#

# Look in the generic major.minor directory for files
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-4.19:"

# Append to the MACHINE_KERNEL_PR so that a new SRCREV will cause a rebuild
MACHINE_KERNEL_PR_append = "tano5"
PR = "${MACHINE_KERNEL_PR}"

KERNEL_SRC_URI ?= "git://git.yoctoproject.org/linux-yocto.git"
KERNEL_SRC_BRANCH ?= "v4.19/standard/preempt-rt/base"
KERNEL_SRC_SRCREV ?= "4dbcaca4c9ff89e866d2b5d01df005f317c618a4"

KERNEL_CACHE_SRC_BRANCH ?= "kernel-4.19"
KERNEL_CACHE_SRC_SRCREV ?= "5005e4d3c8be586c82b881974090578b18896ae8"

LINUX_VERSION ?= "4.19.44-rt0"
LINUX_KERNEL_TYPE ?= "preempt-rt"
PV = "${LINUX_VERSION}+git${SRCPV}"

YOCTO_KERNEL_CACHE_BRANCH = "yocto-4.19"
YOCTO_KERNEL_CACHE_SRCREV = "ad235db461bf4595c668700ca8a909c322009cc1"

require recipes-kernel/linux/linux-tano-yocto.inc
require recipes-kernel/linux/linux-tano.inc

SECTION = "kernel"
DESCRIPTION = "Yocto Linux kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"
