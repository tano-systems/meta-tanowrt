#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2020-2021 Tano Systems LLC. All rights reserved.
# Authors: Anton Kikin <a.kikin@tano-systems.com>
#

PR:append = ".6"

KCONFIG_MODE = "--alldefconfig"

RDEPENDS:${KERNEL_PACKAGE_NAME}-base:append:am574x-idk = " prueth-fw"

# Config
SRC_URI:append:am574x-idk = " file://defconfig"

SRC_URI:append:am574x-idk = "\
	file://0001-am57xx-idk-Change-SPI-NOR-flash-partitions-layout.patch \
"
