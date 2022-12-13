#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2019 Tano Systems LLC. All rights reserved.
# Authors: Anton Kikin <a.kikin@tano-systems.com>
#
# Raspberry Pi Linux kernel 4.19
#
LINUX_RPI_BRANCH ?= "rpi-4.19.y"

KERNEL_SRC_URI ?= "git://github.com/raspberrypi/linux.git"
KERNEL_SRC_BRANCH ?= "${LINUX_RPI_BRANCH}"
KERNEL_SRC_SRCREV ?= "106fa147d3daa58d2c1ae5f41a29d07036fe7d0a"
KERNEL_SRC_PROTOCOL ?= "https"

LINUX_VERSION ?= "4.19.127"
LINUX_KERNEL_TYPE ?= "standard"
PV = "${LINUX_VERSION}+git${SRCPV}"

# Append to the MACHINE_KERNEL_PR so that a new SRCREV will cause a rebuild
MACHINE_KERNEL_PR:append = "tano0"
PR = "${MACHINE_KERNEL_PR}"

require recipes-kernel/linux/linux-tano.inc
require recipes-kernel/linux/linux-tano-rpi.inc

# Look in the generic major.minor directory for files
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-4.19:"

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"
KERNEL_EXTRA_ARGS:append:rpi = " DTC_FLAGS='-@ -H epapr'"

# Already applied
KERNEL_FEATURES:remove = "patches/openwrt/generic-${LINUX_VERSION_FULL}/600.scc"
