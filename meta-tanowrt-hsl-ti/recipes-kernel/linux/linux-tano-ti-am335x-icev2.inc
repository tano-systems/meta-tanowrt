#
# Copyright (C) 2020 Tano Systems LLC, All rights reserved
# Authors: Anton Kikin <a.kikin@tano-systems.com>
#

PR_append = ".8"

KCONFIG_MODE = "--alldefconfig"

RDEPENDS_${KERNEL_PACKAGE_NAME}-base_append_ti33x = " prueth-fw"

# Config
SRC_URI_append_am335x-icev2 = " file://defconfig"
