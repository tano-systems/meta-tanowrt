#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2020-2021 Tano Systems LLC. All rights reserved.
# Authors: Anton Kikin <a.kikin@tano-systems.com>
#

PR_append = ".4"

KCONFIG_MODE = "--alldefconfig"

RDEPENDS_${KERNEL_PACKAGE_NAME}-base_append_am574x-idk = " prueth-fw"

# Config
SRC_URI_append_am574x-idk = " file://defconfig"

SRC_URI_append_am574x-idk = "\
	file://0001-am57xx-idk-Change-SPI-NOR-flash-partitions-layout.patch \
"

# TI CMEM (meta-ti)
require recipes-kernel/linux/cmem.inc
RESERVE_CMEM = "1"
FILESEXTRAPATHS_append = ":${COREBASE}/meta-ti/recipes-kernel/linux/files"
CMEM_MACHINE_am574x-idk = "am571x am572x am572idk am5729-beagleboneai am574x"
