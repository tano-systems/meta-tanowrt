#
# Copyright (C) 2020 Tano Systems LLC, All rights reserved
# Authors: Anton Kikin <a.kikin@tano-systems.com>
#

PR_append = ".10"

KCONFIG_MODE = "--alldefconfig"

RDEPENDS_${KERNEL_PACKAGE_NAME}-base_append_ti33x = " prueth-fw"

# Config
SRC_URI_append_am335x-icev2 = " file://defconfig"

# Patches
SRC_URI_append_am335x-icev2 = " file://0001-am335x-icev2.dts-Enable-UART4.patch "

# Patches only for 4.19 kernel
SRC_URI_append_am335x-icev2 = "\
	${@oe.utils.conditional('LINUX_VERSION_SHORT', '4.19', 'file://0002-am335x-icev2.dts-Fix-I2C-LCD-definition-in-DTS.patch', '', d)} \
"
