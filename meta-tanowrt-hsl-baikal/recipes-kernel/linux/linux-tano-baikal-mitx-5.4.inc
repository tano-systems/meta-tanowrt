#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2021 Tano Systems LLC. All rights reserved.
# Authors: Anton Kikin <a.kikin@tano-systems.com>
#
PR:append = ".0"

COMPATIBLE_MACHINE =. "mbm10|mbm20|"

# Config
SRC_URI:append:mitx = " file://defconfig "

RDEPENDS:${KERNEL_PACKAGE_NAME}-base:append = " button-hotplug"
