#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2022 Tano Systems LLC. All rights reserved.
# Authors: Anton Kikin <a.kikin@tano-systems.com>
#

PR_append = ".0"

KCONFIG_MODE = "--alldefconfig"

# Config
SRC_URI_append = " file://defconfig"

# Using devicetree from kernel sources
KERNEL_DEVICETREE_COPY = ""
