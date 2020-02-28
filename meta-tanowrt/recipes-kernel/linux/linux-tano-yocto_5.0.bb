#
# Copyright (C) 2020, Tano Systems, All rights reserved
# Authors: Anton Kikin <a.kikin@tano-systems.com>
#
# Yocto Linux kernel 5.0 (standard)
#

# Look in the generic major.minor directory for files
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-5.0:"

# Append to the MACHINE_KERNEL_PR so that a new SRCREV will cause a rebuild
MACHINE_KERNEL_PR_append = "tano0"
PR = "${MACHINE_KERNEL_PR}"

KERNEL_SRC_URI ?= "git://git.yoctoproject.org/linux-yocto.git"

KERNEL_SRC_BRANCH_qemuarm ?= "v5.0/standard/arm-versatile-926ejs"
KERNEL_SRC_BRANCH_qemux86 ?= "v5.0/standard/base"
KERNEL_SRC_BRANCH_qemux86-64 ?= "v5.0/standard/base"
KERNEL_SRC_BRANCH ?= "v5.0/standard/base"

KERNEL_SRC_SRCREV_machine_qemuarm ?= "d1ed980ad989252d42386c8bc63b2f5f11985ea4"
KERNEL_SRC_SRCREV_machine_qemux86 ?= "55dd15336b7301b686a0c183f5372b49c1003d03"
KERNEL_SRC_SRCREV_machine_qemux86-64 ?= "55dd15336b7301b686a0c183f5372b49c1003d03"
KERNEL_SRC_SRCREV ?= "55dd15336b7301b686a0c183f5372b49c1003d03"

LINUX_VERSION ?= "5.0.19"
LINUX_KERNEL_TYPE ?= "standard"
PV = "${LINUX_VERSION}+git${SRCPV}"

YOCTO_KERNEL_CACHE_BRANCH = "yocto-5.0"
YOCTO_KERNEL_CACHE_SRCREV = "7f6e97c357746382d4339e7e0463637e715acd4b"

require recipes-kernel/linux/tano-kernel-cache-5.0.inc
require recipes-kernel/linux/linux-tano-yocto.inc
require recipes-kernel/linux/linux-tano.inc

SECTION = "kernel"
DESCRIPTION = "Yocto Linux kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"
