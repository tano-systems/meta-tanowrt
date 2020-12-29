#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2018, 2020 Tano Systems LLC
# Author: Anton Kikin <a.kikin@tano-systems.com>
#
# USB lib composite
#

inherit kernel-kmod
inherit kmod/usb-gadget
inherit kmod/fs-configfs

#
# kmod-usb-lib-composite
# ########################
#
KERNEL_CONFIG_DEPENDS += "{\
	option              = CONFIG_USB_LIBCOMPOSITE, \
	required            = y|m, \
	m_rdepends          = kernel-module-libcomposite, \
	m_autoload          = libcomposite, \
	m_autoload_priority = 50, \
	m_autoload_script   = usb-lib-composite \
}"
