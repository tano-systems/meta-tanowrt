#
# Copyright (C) 2020 Tano Systems LLC, All rights reserved
# Authors: Anton Kikin <a.kikin@tano-systems.com>
#

PR_append = ".0"

KCONFIG_MODE = "--alldefconfig"

# Config
SRC_URI_append_am335x-bbb = " file://defconfig"
