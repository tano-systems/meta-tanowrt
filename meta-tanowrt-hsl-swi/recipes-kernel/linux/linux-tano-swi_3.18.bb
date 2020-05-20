#
# Copyright (C) 2019-2020, Tano Systems, All rights reserved
# Authors: Anton Kikin <a.kikin@tano-systems.com>
#
SECTION = "kernel"
DESCRIPTION = "Linux kernel for SWI"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

# Look in the generic major.minor directory for files
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-3.18:"
FILESEXTRAPATHS_prepend := "${THISDIR}/features:"

# Append to the MACHINE_KERNEL_PR so that a new SRCREV will cause a rebuild
MACHINE_KERNEL_PR_append = "tn-1"
PR = "${MACHINE_KERNEL_PR}"

# TAG = SWI9X07Y_02.28.03.05
KERNEL_SRC_URI ?= "git://gitlab.com/legato.io/msm-3.18.git;nobranch=1;tag=SWI9X06Y_02.32.01.00"
KERNEL_SRC_PROTOCOL ?= "https"
KERNEL_SRC_BRANCH ?= ""
KERNEL_SRC_SRCREV ?= "SWI9X06Y_02.32.01.00"


LINUX_VERSION ?= "3.18.140"
LINUX_KERNEL_TYPE ?= "standard"
PV = "${LINUX_VERSION}+git${SRCPV}"

LINUX_VERSION_FULL = "${@kernel_full_version("${LINUX_VERSION}")}"
LINUX_VERSION_SHORT = "${@oe.utils.trim_version("${LINUX_VERSION}", 2)}"

require recipes-kernel/linux/tano-kernel-cache-3.18.inc
require recipes-kernel/linux/linux-tano.inc
require recipes-kernel/linux/linux-tano-swi.inc

KERNEL_FEATURES_remove = "features/general/fs/f2fs.scc"
KERNEL_FEATURES_remove = "features/general/usb-serial/usb-serial.scc"
KERNEL_FEATURES_remove = "features/general/usb-net/usb-net.scc"
KERNEL_FEATURES_remove = "features/general/wifi/wifi-usb.scc"
KERNEL_FEATURES_remove = "features/general/wifi/wifi-pci.scc"

SRC_URI_append = " file://netfilter.scc "
KERNEL_FEATURES_remove = "features/general/netfilter/netfilter.scc"
KERNEL_FEATURES_append = " netfilter.scc "

SRC_URI_append = " file://coresight.scc "
KERNEL_FEATURES_append = " coresight.scc "

# Already applied
KERNEL_FEATURES_remove = "patches/openwrt/generic-${LINUX_VERSION_FULL}/613.scc"
