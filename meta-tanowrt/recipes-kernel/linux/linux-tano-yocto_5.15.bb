#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2022 Tano Systems LLC. All rights reserved.
# Authors: Anton Kikin <a.kikin@tano-systems.com>
#
# Yocto Linux kernel 5.15 (standard)
#

# Skip processing of this recipe if it is not explicitly specified as the
# PREFERRED_PROVIDER for virtual/kernel.
python () {
    if d.getVar("KERNEL_PACKAGE_NAME") == "kernel" and d.getVar("PREFERRED_PROVIDER_virtual/kernel") != "linux-tano-yocto":
        raise bb.parse.SkipRecipe("Set PREFERRED_PROVIDER_virtual/kernel to linux-tano-yocto to enable it")
}

KERNEL_SRC_URI ?= "git://git.yoctoproject.org/linux-yocto.git"

KERNEL_SRC_BRANCH:qemuarm ?= "v5.15/standard/arm-versatile-926ejs"
KERNEL_SRC_BRANCH:qemuarm64 ?= "v5.15/standard/qemuarm64"
KERNEL_SRC_BRANCH:qemux86 ?= "v5.15/standard/base"
KERNEL_SRC_BRANCH:qemux86-64 ?= "v5.15/standard/base"
KERNEL_SRC_BRANCH ?= "v5.15/standard/base"

KERNEL_SRC_SRCREV_machine:qemuarm ?= "d3aa5916b2b02966ef37bfe3fc527c99754571ec"
KERNEL_SRC_SRCREV_machine:qemuarm64 ?= "a1d364fbe3d8a916426a107f07b89fd0338923c7"
KERNEL_SRC_SRCREV_machine:qemux86 ?= "8cd3f1c8dc13e8fa2d9a25ce0285d3526705eea7"
KERNEL_SRC_SRCREV_machine:qemux86-64 ?= "8cd3f1c8dc13e8fa2d9a25ce0285d3526705eea7"
KERNEL_SRC_SRCREV ?= "8cd3f1c8dc13e8fa2d9a25ce0285d3526705eea7"

LINUX_VERSION ?= "5.15.78"
LINUX_KERNEL_TYPE ?= "standard"
PV = "${LINUX_VERSION}+git${SRCPV}"

# Append to the MACHINE_KERNEL_PR so that a new SRCREV will cause a rebuild
MACHINE_KERNEL_PR:append = "tano0"
PR = "${MACHINE_KERNEL_PR}"

require recipes-kernel/linux/linux-tano.inc
require recipes-kernel/linux/linux-tano-yocto.inc

# Look in the generic major.minor directory for files
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-5.15:"

YOCTO_KERNEL_CACHE_BRANCH = "yocto-5.15"
YOCTO_KERNEL_CACHE_SRCREV = "f475b1a9deddbde23f48d7d535abdd5fb133b837"

SECTION = "kernel"
DESCRIPTION = "Yocto Linux kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"
