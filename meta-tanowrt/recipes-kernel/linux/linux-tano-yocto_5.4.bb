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

KERNEL_SRC_SRCREV_machine_qemuarm ?= "7a9ca83b483c096e6bd5e1b99cca7fe2fb79fd1a"
KERNEL_SRC_SRCREV_machine_qemuarm64 ?= "d2ea3664c5872b3046a2aa970035de51e359922f"
KERNEL_SRC_SRCREV_machine_qemux86 ?= "d54d61f9e363806a987c9ab01df0e66a31d4ead5"
KERNEL_SRC_SRCREV_machine_qemux86-64 ?= "d54d61f9e363806a987c9ab01df0e66a31d4ead5"
KERNEL_SRC_SRCREV ?= "d54d61f9e363806a987c9ab01df0e66a31d4ead5"

LINUX_VERSION ?= "5.4.153"
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
YOCTO_KERNEL_CACHE_SRCREV = "9e3ab4e615b651c1b63d4f0cce71da79a3e89763"

SECTION = "kernel"
DESCRIPTION = "Yocto Linux kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"
