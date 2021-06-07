#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2021 Tano Systems LLC. All rights reserved.
# Authors: Anton Kikin <a.kikin@tano-systems.com>
#
PR_append = ".0"

COMPATIBLE_MACHINE =. "mbm10|mbm20|"

# Config
SRC_URI_append_mitx = " file://defconfig "

RDEPENDS_${KERNEL_PACKAGE_NAME}-base_append = " button-hotplug"
