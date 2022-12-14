#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2022 Tano Systems LLC. All rights reserved.
# Authors: Anton Kikin <a.kikin@tano-systems.com>
#

PR:append = ".0"

KCONFIG_MODE = "--alldefconfig"

# Config
SRC_URI:append = " file://defconfig"

# Devicetree files
SRC_URI:append = "\
	file://at91-sama5d3_xplained.dts \
"
