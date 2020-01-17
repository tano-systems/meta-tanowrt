#
# Copyright (C) 2019, Tano Systems, All rights reserved
# Authors: Anton Kikin <a.kikin@tano-systems.com>
#
# Raspberry Pi Linux kernel 4.19 (preempt-rt)
#

# Look in the generic major.minor directory for files
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-4.19:"

# Append to the MACHINE_KERNEL_PR so that a new SRCREV will cause a rebuild
MACHINE_KERNEL_PR_append = "tano0"
PR = "${MACHINE_KERNEL_PR}"

LINUX_RPI_BRANCH ?= "rpi-4.19.y-rt"

KERNEL_SRC_URI ?= "git://github.com/raspberrypi/linux.git;protocol=git"
KERNEL_SRC_BRANCH ?= "${LINUX_RPI_BRANCH}"
KERNEL_SRC_SRCREV ?= "03648815ecfbb1c1fa64a2baafb8a3f6f5902996"

LINUX_VERSION ?= "4.19.50-rt"
LINUX_KERNEL_TYPE ?= "preempt-rt"
PV = "${LINUX_VERSION}+git${SRCPV}"

require recipes-kernel/linux/tano-kernel-cache-4.19.inc
require recipes-kernel/linux/linux-tano-rpi.inc
require recipes-kernel/linux/linux-tano.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"
KERNEL_EXTRA_ARGS_append_rpi = " DTC_FLAGS='-@ -H epapr'"
