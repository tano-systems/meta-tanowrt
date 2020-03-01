#
# Copyright (C) 2019-2020, Tano Systems, All rights reserved
# Authors: Anton Kikin <a.kikin@tano-systems.com>
#

PR_append = ".1"

python __anonymous() {
    if not d.getVar("KERNEL_CACHE_DIR"):
        bb.error("You need to set properly value for KERNEL_CACHE_DIR")
}

# LEVELS:
#   0: no reporting
#   1: report options that are specified, but not in the final config
#   2: report options that are not hardware related, but set by a BSP
KCONF_AUDIT_LEVEL ?= "1"
KCONF_BSP_AUDIT_LEVEL ?= "0"
KMETA_AUDIT ?= "yes"

KBRANCH ?= "${KERNEL_SRC_BRANCH}"
KMACHINE ?= "${MACHINE}"

KERNEL_SRC_PROTOCOL ?= "git"

FILESEXTRAPATHS_prepend := "${THISDIR}:"

#
# Kernel source SRC_URI
#
SRC_URI += "\
	${KERNEL_SRC_URI};name=machine;protocol=${KERNEL_SRC_PROTOCOL}${@oe.utils.conditional('KERNEL_SRC_BRANCH', '', '', ';branch=', d)}${KERNEL_SRC_BRANCH} \
"

#
# Kernel cache SRC_URI
#
# Override all other kmeta data by specifying name starting with 'zz-' (destsuffix).
# All include paths will be sorted by spp with 'sort' tool.
#
SRC_URI += "\
	file://${KERNEL_CACHE_DIR};type=kmeta;name=metatano;destsuffix=zz-kernel-cache-tano/${KERNEL_CACHE_DIR};subdir=zz-kernel-cache-tano \
"

SRCREV_machine = "${KERNEL_SRC_SRCREV}"
SRCREV_FORMAT = "machine"

require recipes-kernel/linux/linux-yocto.inc

inherit kernel-tano

LINUX_VERSION_EXTENSION ??= "-tano-${LINUX_KERNEL_TYPE}"

do_kernel_configme[depends] += "virtual/${TARGET_PREFIX}binutils:do_populate_sysroot"
do_kernel_configme[depends] += "virtual/${TARGET_PREFIX}gcc:do_populate_sysroot"
do_kernel_configme[depends] += "bc-native:do_populate_sysroot bison-native:do_populate_sysroot"
