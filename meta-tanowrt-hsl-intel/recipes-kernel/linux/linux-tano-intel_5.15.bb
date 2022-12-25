#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2022 Tano Systems LLC. All rights reserved.
# Authors: Anton Kikin <a.kikin@tano-systems.com>
#
# Intel Linux kernel 5.15
#
LINUX_INTEL_BRANCH = "5.15/linux"

KERNEL_SRC_URI ?= "git://github.com/intel/linux-intel-lts.git"
KERNEL_SRC_BRANCH ?= "${LINUX_INTEL_BRANCH}"
KERNEL_SRC_SRCREV ?= "d7d7ea689cc887081dd8bf8ef2f296b5cd9c593e"
KERNEL_SRC_PROTOCOL ?= "https"

LINUX_VERSION ?= "5.15.1"
LINUX_KERNEL_TYPE ?= "standard"
PV = "${LINUX_VERSION}+git${SRCPV}"

require recipes-kernel/linux/linux-tano.inc
require recipes-kernel/linux/linux-tano-intel.inc

YOCTO_KERNEL_CACHE_BRANCH = "yocto-5.15"
YOCTO_KERNEL_CACHE_SRCREV = "bee5d6a15909f05935f4a61f83a72cddfca7934a"

SRC_URI:append = "\
	file://0001-menuconfig-mconf-cfg-Allow-specification-of-ncurses-.patch \
"

DEPENDS += "elfutils-native openssl-native util-linux-native"

# For Crystalforest and Romley
KERNEL_MODULE_AUTOLOAD:append:core2-32-intel-common = " uio"
KERNEL_MODULE_AUTOLOAD:append:corei7-64-intel-common = " uio"

# Disabling CONFIG_SND_SOC_INTEL_SKYLAKE for 32-bit, does not allow to set CONFIG_SND_SOC_INTEL_SST too, which
# causes config warning too.
KCONF_AUDIT_LEVEL:core2-32-intel-common = "0"
