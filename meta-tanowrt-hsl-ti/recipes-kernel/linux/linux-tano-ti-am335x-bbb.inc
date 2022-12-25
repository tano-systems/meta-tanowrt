#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2020-2021 Tano Systems LLC. All rights reserved.
# Authors: Anton Kikin <a.kikin@tano-systems.com>
#

PR:append = ".2"

KCONFIG_MODE = "--alldefconfig"

# Config
SRC_URI:append:am335x-bbb = " file://defconfig"
