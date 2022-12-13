#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2022 Tano Systems LLC. All rights reserved.
# Authors: Anton Kikin <a.kikin@tano-systems.com>
#
# Intel Linux RT kernel 5.15
#

# Skip processing of this recipe if it is not explicitly specified as the
# PREFERRED_PROVIDER for virtual/kernel. This avoids errors when trying
# to build multiple virtual/kernel providers, e.g. as dependency of
# core-image-rt-sdk, core-image-rt.
python () {
    if d.getVar("KERNEL_PACKAGE_NAME", True) == "kernel" and d.getVar("PREFERRED_PROVIDER_virtual/kernel") != "linux-tano-intel-rt":
        raise bb.parse.SkipPackage("Set PREFERRED_PROVIDER_virtual/kernel to linux-tano-intel-rt to enable it")
}

LINUX_INTEL_BRANCH = "5.15/preempt-rt"

KERNEL_SRC_URI ?= "git://github.com/intel/linux-intel-lts.git"
KERNEL_SRC_BRANCH ?= "${LINUX_INTEL_BRANCH}"
KERNEL_SRC_SRCREV ?= "f09e0e5e5d73c753dbdf2e9f0ac2d533a51e8dd8"
KERNEL_SRC_PROTOCOL ?= "https"

LINUX_VERSION ?= "5.15.2"
LINUX_KERNEL_TYPE ?= "preempt-rt"
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
