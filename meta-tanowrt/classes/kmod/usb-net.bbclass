#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2018, 2020 Tano Systems LLC
# Author: Anton Kikin <a.kikin@tano-systems.com>
#

inherit kernel-kmod

inherit kmod/mii
inherit kmod/usb-core

#
# kmod-usb-net
# ############
#
KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_USB_USBNET, \
	required          = y|m, \
	m_rdepends        = kernel-module-usbnet, \
	m_autoload        = usbnet, \
	m_autoload_script = usb-net \
}"

KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_USB_NET_DRIVERS, \
	required          = y|m \
}"
