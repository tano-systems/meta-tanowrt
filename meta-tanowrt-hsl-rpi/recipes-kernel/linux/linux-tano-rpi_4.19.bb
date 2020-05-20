#
# Copyright (C) 2019, Tano Systems, All rights reserved
# Authors: Anton Kikin <a.kikin@tano-systems.com>
#
# Raspberry Pi Linux kernel 4.19
#

# Look in the generic major.minor directory for files
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-4.19:"

# Append to the MACHINE_KERNEL_PR so that a new SRCREV will cause a rebuild
MACHINE_KERNEL_PR_append = "tano2"
PR = "${MACHINE_KERNEL_PR}"

LINUX_RPI_BRANCH ?= "rpi-4.19.y"

KERNEL_SRC_URI ?= "git://github.com/raspberrypi/linux.git;protocol=git"
KERNEL_SRC_BRANCH ?= "${LINUX_RPI_BRANCH}"
KERNEL_SRC_SRCREV ?= "55b89745a4782db50e86291bd1adee8fb44b84ad"

LINUX_VERSION ?= "4.19.57"
LINUX_KERNEL_TYPE ?= "standard"
PV = "${LINUX_VERSION}+git${SRCPV}"

LINUX_VERSION_FULL = "${@kernel_full_version("${LINUX_VERSION}")}"
LINUX_VERSION_SHORT = "${@oe.utils.trim_version("${LINUX_VERSION}", 2)}"

require recipes-kernel/linux/tano-kernel-cache-4.19.inc
require recipes-kernel/linux/linux-tano-rpi.inc
require recipes-kernel/linux/linux-tano.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"
KERNEL_EXTRA_ARGS_append_rpi = " DTC_FLAGS='-@ -H epapr'"
