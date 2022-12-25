#
# SPDX-License-Identifier: MIT
# Copyright (c) 2022 Tano Systems LLC. All rights reserved.
#
SECTION = "kernel"
DESCRIPTION = "Linux kernel for Intel CPU"
SUMMARY = "Linux kernel for Intel CPU"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

require recipes-kernel/linux/linux-tano-yocto.inc

PR:append = ".0"

KERNEL_FEATURES_INTEL_COMMON ?= ""

COMPATIBLE_MACHINE:intel-x86-common = "${MACHINE}"
KERNEL_FEATURES:intel-x86-common = "${KERNEL_FEATURES_INTEL_COMMON}"

KMACHINE:corei7-64-intel-common = "intel-corei7-64"
KMACHINE:core2-32-intel-common = "intel-core2-32"
KMACHINE:skylake-64-intel-common = "intel-corei7-64"

INTEL_COMMON_AUTOLOAD ?= " uio iwlwifi i915"
KERNEL_MODULE_AUTOLOAD:intel-x86-common = " ${INTEL_COMMON_AUTOLOAD}"

FILESEXTRAPATHS:prepend := "${THISDIR}/linux-tano-intel:"
SRC_URI:append:core2-32-intel-common = " file://disable_skylake_sound.cfg"
KCONF_BSP_AUDIT_LEVEL = "2"
COMPATIBLE_MACHINE ?= "(intel-corei7-64|intel-core2-32)"

LINUX_VERSION_EXTENSION = "-tano-intel-${LINUX_KERNEL_TYPE}"

SRC_URI:append = " file://defconfig file://enable_ixgbe.cfg"

KERNEL_FEATURES:remove = "features/general/net/ppp.scc"
SRC_URI:append = " file://ppp.cfg"
