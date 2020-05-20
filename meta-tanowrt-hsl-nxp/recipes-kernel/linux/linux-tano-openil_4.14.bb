#
# Copyright (C) 2020, Tano Systems, All rights reserved
# Authors: Anton Kikin <a.kikin@tano-systems.com>
#
SECTION = "kernel"
DESCRIPTION = "OpenIL linux kernel for NXP platforms"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

# Look in the generic major.minor directory for files
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-4.14:"

# Append to the MACHINE_KERNEL_PR so that a new SRCREV will cause a rebuild
MACHINE_KERNEL_PR_append = "tano1"
PR = "${MACHINE_KERNEL_PR}"

KERNEL_SRC_URI ?= "git://github.com/openil/linux.git;nobranch=1"
KERNEL_SRC_BRANCH ?= ""
KERNEL_SRC_SRCREV ?= "OpenIL-v1.6.2-linux-201911"

LINUX_VERSION ?= "4.14.47"
LINUX_KERNEL_TYPE ?= "preempt-rt"
PV = "${LINUX_VERSION}+git${SRCPV}"

LINUX_VERSION_FULL = "${@kernel_full_version("${LINUX_VERSION}")}"
LINUX_VERSION_SHORT = "${@oe.utils.trim_version("${LINUX_VERSION}", 2)}"

require recipes-kernel/linux/tano-kernel-cache-4.14.inc
require recipes-kernel/linux/linux-tano-openil.inc
require recipes-kernel/linux/linux-tano.inc
