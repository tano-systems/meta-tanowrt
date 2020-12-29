#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2020 Tano Systems LLC. All rights reserved.
# Authors: Anton Kikin <a.kikin@tano-systems.com>
#
# Raspberry Pi Linux kernel 5.4
#
LINUX_RPI_BRANCH ?= "rpi-5.4.y"

KERNEL_SRC_URI ?= "git://github.com/raspberrypi/linux.git;protocol=git"
KERNEL_SRC_BRANCH ?= "${LINUX_RPI_BRANCH}"
KERNEL_SRC_SRCREV ?= "423495b33efd681dc1c8be10b1303e679216be4c"

LINUX_VERSION ?= "5.4.59"
LINUX_KERNEL_TYPE ?= "standard"
PV = "${LINUX_VERSION}+git${SRCPV}"

# Append to the MACHINE_KERNEL_PR so that a new SRCREV will cause a rebuild
MACHINE_KERNEL_PR_append = "tano0"
PR = "${MACHINE_KERNEL_PR}"

require recipes-kernel/linux/linux-tano.inc
require recipes-kernel/linux/linux-tano-rpi.inc

# Look in the generic major.minor directory for files
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-5.4:"

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"
KERNEL_EXTRA_ARGS_append_rpi = " DTC_FLAGS='-@ -H epapr'"
