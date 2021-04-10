#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2020-2021 Tano Systems LLC, All rights reserved
# Authors: Anton Kikin <a.kikin@tano-systems.com>
#
# Raspberry Pi Linux kernel 5.10
#
LINUX_RPI_BRANCH ?= "rpi-5.10.y"

KERNEL_SRC_URI ?= "git://github.com/raspberrypi/linux.git;protocol=git"
KERNEL_SRC_BRANCH ?= "${LINUX_RPI_BRANCH}"
KERNEL_SRC_SRCREV ?= "d1fd8a5727908bb677c003d2ae977e9d935a6f94"

LINUX_VERSION ?= "5.10.25"
LINUX_KERNEL_TYPE ?= "standard"
PV = "${LINUX_VERSION}+git${SRCPV}"

# Append to the MACHINE_KERNEL_PR so that a new SRCREV will cause a rebuild
MACHINE_KERNEL_PR_append = "tano0"
PR = "${MACHINE_KERNEL_PR}"

require recipes-kernel/linux/linux-tano.inc
require recipes-kernel/linux/linux-tano-rpi.inc

# Look in the generic major.minor directory for files
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-5.10:"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"
KERNEL_EXTRA_ARGS_append_rpi = " DTC_FLAGS='-@ -H epapr'"
