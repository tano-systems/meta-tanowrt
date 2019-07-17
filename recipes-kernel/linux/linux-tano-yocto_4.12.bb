#
# Copyright (C) 2019, Tano Systems, All rights reserved
# Authors: Anton Kikin <a.kikin@tano-systems.com>
#
# Yocto Linux kernel 4.12 (standard)
#

# Look in the generic major.minor directory for files
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-4.12:"

# Append to the MACHINE_KERNEL_PR so that a new SRCREV will cause a rebuild
MACHINE_KERNEL_PR_append = "tano4"
PR = "${MACHINE_KERNEL_PR}"

KERNEL_SRC_URI ?= "git://git.yoctoproject.org/linux-yocto-4.12.git"

KERNEL_SRC_BRANCH_qemuarm ?= "standard/arm-versatile-926ejs"
KERNEL_SRC_BRANCH_qemux86 ?= "standard/base"
KERNEL_SRC_BRANCH_qemux86-64 ?= "standard/base"
KERNEL_SRC_BRANCH ?= "standard/base"

KERNEL_SRC_SRCREV_machine_qemuarm ?= "45824c60ca37f414a5ac5783e970338db9a5a2af"
KERNEL_SRC_SRCREV_machine_qemux86 ?= "f9d67777b07ac97966186c1b56db78afe2a16f92"
KERNEL_SRC_SRCREV_machine_qemux86-64 ?= "f9d67777b07ac97966186c1b56db78afe2a16f92"
KERNEL_SRC_SRCREV ?= "f9d67777b07ac97966186c1b56db78afe2a16f92"

KERNEL_CACHE_SRC_BRANCH ?= "kernel-4.12"
KERNEL_CACHE_SRC_SRCREV ?= "cdf68aea95c31b53cb9a55744625bad5127c6579"

LINUX_VERSION ?= "4.12.24"
LINUX_KERNEL_TYPE ?= "standard"
PV = "${LINUX_VERSION}+git${SRCPV}"

YOCTO_KERNEL_CACHE_BRANCH = "yocto-4.12"
YOCTO_KERNEL_CACHE_SRCREV = "46171de19220c49d670544017cfbeffc1ec70e80"

require recipes-kernel/linux/linux-tano-yocto.inc
require recipes-kernel/linux/linux-tano.inc

SECTION = "kernel"
DESCRIPTION = "Yocto Linux kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"
