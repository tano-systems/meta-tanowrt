#
# Copyright (C) 2020, Tano Systems, All rights reserved
# Authors: Anton Kikin <a.kikin@tano-systems.com>
#
# Yocto Linux kernel 5.4 (standard)
#

# Look in the generic major.minor directory for files
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-5.4:"

# Append to the MACHINE_KERNEL_PR so that a new SRCREV will cause a rebuild
MACHINE_KERNEL_PR_append = "tano0"
PR = "${MACHINE_KERNEL_PR}"

KERNEL_SRC_URI ?= "git://git.yoctoproject.org/linux-yocto.git"

KERNEL_SRC_BRANCH_qemuarm ?= "v5.4/standard/arm-versatile-926ejs"
KERNEL_SRC_BRANCH_qemux86 ?= "v5.4/standard/base"
KERNEL_SRC_BRANCH_qemux86-64 ?= "v5.4/standard/base"
KERNEL_SRC_BRANCH ?= "v5.4/standard/base"

KERNEL_SRC_SRCREV_machine_qemuarm ?= "cbe84f8dbb7fe4a67a337795d81cca735346f4c8"
KERNEL_SRC_SRCREV_machine_qemux86 ?= "29f44c85c379c38f15e544828e7e77b3c008f378"
KERNEL_SRC_SRCREV_machine_qemux86-64 ?= "29f44c85c379c38f15e544828e7e77b3c008f378"
KERNEL_SRC_SRCREV ?= "29f44c85c379c38f15e544828e7e77b3c008f378"

LINUX_VERSION ?= "5.4.40"
LINUX_KERNEL_TYPE ?= "standard"
PV = "${LINUX_VERSION}+git${SRCPV}"

LINUX_VERSION_FULL = "${@kernel_full_version("${LINUX_VERSION}")}"
LINUX_VERSION_SHORT = "${@oe.utils.trim_version("${LINUX_VERSION}", 2)}"

YOCTO_KERNEL_CACHE_BRANCH = "yocto-5.4"
YOCTO_KERNEL_CACHE_SRCREV = "4ed477131e6e508d335d70c070eb84a5c3d673e5"

require recipes-kernel/linux/tano-kernel-cache-5.4.inc
require recipes-kernel/linux/linux-tano-yocto.inc
require recipes-kernel/linux/linux-tano.inc

SECTION = "kernel"
DESCRIPTION = "Yocto Linux kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"
