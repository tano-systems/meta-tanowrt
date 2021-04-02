#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2021 Tano Systems LLC. All rights reserved.
# Authors: Anton Kikin <a.kikin@tano-systems.com>
#

PR_append = ".0"

KCONFIG_MODE = "--alldefconfig"

# Config
SRC_URI_append_evb-ksz9477 = " file://defconfig"

# Devicetree files
SRC_URI_append = "\
	file://at91-evb-ksz9477.dts \
	file://at91-evb-ksz9477-sfp.dts \
"
