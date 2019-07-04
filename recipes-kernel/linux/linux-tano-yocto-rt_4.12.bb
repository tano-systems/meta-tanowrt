#
# Copyright (C) 2019, Tano Systems, All rights reserved
# Authors: Anton Kikin <a.kikin@tano-systems.com>
#
# Yocto Linux kernel 4.12 (preempt-rt)
#

# Look in the generic major.minor directory for files
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-4.12:"

# Append to the MACHINE_KERNEL_PR so that a new SRCREV will cause a rebuild
MACHINE_KERNEL_PR_append = "tano3"
PR = "${MACHINE_KERNEL_PR}"

KERNEL_SRC_URI ?= "git://git.yoctoproject.org/linux-yocto-4.12.git"
KERNEL_SRC_BRANCH ?= "standard/preempt-rt/base"
KERNEL_SRC_SRCREV ?= "705d03507a0c10dcbf9cad3ff70f5d60b70f2d99"

KERNEL_CACHE_SRC_BRANCH ?= "kernel-4.12"
KERNEL_CACHE_SRC_SRCREV ?= "f731157f8bd2785e42e8e9378fe09224fa4f19c3"

LINUX_VERSION ?= "4.12.24-rt0"
LINUX_KERNEL_TYPE ?= "preempt-rt"
PV = "${LINUX_VERSION}+git${SRCPV}"

YOCTO_KERNEL_CACHE_BRANCH = "yocto-4.12"
YOCTO_KERNEL_CACHE_SRCREV = "46171de19220c49d670544017cfbeffc1ec70e80"

require recipes-kernel/linux/linux-tano-yocto.inc
require recipes-kernel/linux/linux-tano.inc

SECTION = "kernel"
DESCRIPTION = "Yocto Linux kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"
