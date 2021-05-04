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

KERNEL_SRC_SRCREV_machine_qemuarm ?= "4f13e8499f2bfc9d95dcc70b981ea16351ff088b"
KERNEL_SRC_SRCREV_machine_qemuarm64 ?= "8465e471f5b4855d01012a18cd5fa516061c0358"
KERNEL_SRC_SRCREV_machine_qemux86 ?= "d6e20b2257ecfa6e796a45a4175863862a28fa11"
KERNEL_SRC_SRCREV_machine_qemux86-64 ?= "d6e20b2257ecfa6e796a45a4175863862a28fa11"
KERNEL_SRC_SRCREV ?= "d6e20b2257ecfa6e796a45a4175863862a28fa11"

LINUX_VERSION ?= "5.10.30"
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
YOCTO_KERNEL_CACHE_SRCREV = "40a967b115fdbac34fc980d0e27ac2373a031189"

SECTION = "kernel"
DESCRIPTION = "Yocto Linux kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"
