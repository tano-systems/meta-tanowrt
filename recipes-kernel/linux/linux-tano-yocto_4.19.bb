#
# Copyright (C) 2019, Tano Systems, All rights reserved
# Authors: Anton Kikin <a.kikin@tano-systems.com>
#
# Yocto Linux kernel 4.19 (standard)
#

# Look in the generic major.minor directory for files
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-4.19:"

# Append to the MACHINE_KERNEL_PR so that a new SRCREV will cause a rebuild
MACHINE_KERNEL_PR_append = "tano2"
PR = "${MACHINE_KERNEL_PR}"

KERNEL_SRC_URI ?= "git://git.yoctoproject.org/linux-yocto.git"

KERNEL_SRC_BRANCH_qemuarm ?= "v4.19/standard/arm-versatile-926ejs"
KERNEL_SRC_BRANCH_qemux86 ?= "v4.19/standard/base"
KERNEL_SRC_BRANCH_qemux86-64 ?= "v4.19/standard/base"
KERNEL_SRC_BRANCH ?= "v4.19/standard/base"

KERNEL_SRC_SRCREV_machine_qemuarm ?= "b960e1b087a8b0f192e5a9a3441dd47aecfa7d48"
KERNEL_SRC_SRCREV_machine_qemux86 ?= "eebb51300a07804a020ec468b5f8c5bf720198d9"
KERNEL_SRC_SRCREV_machine_qemux86-64 ?= "eebb51300a07804a020ec468b5f8c5bf720198d9"
KERNEL_SRC_SRCREV ?= "eebb51300a07804a020ec468b5f8c5bf720198d9"

KERNEL_CACHE_SRC_BRANCH ?= "kernel-4.19"
KERNEL_CACHE_SRC_SRCREV ?= "4c14d9cbf2b510002e16b2b8c2edffff64343d1e"

LINUX_VERSION ?= "4.19.14"
LINUX_KERNEL_TYPE ?= "standard"
PV = "${LINUX_VERSION}+git${SRCPV}"

YOCTO_KERNEL_CACHE_BRANCH = "yocto-4.19"
YOCTO_KERNEL_CACHE_SRCREV = "70d33ded25747f73381baff8d8758e86967e4ee2"

require recipes-kernel/linux/linux-tano-yocto.inc
require recipes-kernel/linux/linux-tano.inc

SECTION = "kernel"
DESCRIPTION = "Yocto Linux kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"
