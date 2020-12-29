#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2018, 2020 Tano Systems LLC
# Author: Anton Kikin <a.kikin@tano-systems.com>
#
# Kernel support for IPWireless 3G devices
#

inherit kernel-kmod

inherit kmod/usb-serial
inherit kmod/usb-serial-wwan

#
# kmod-usb-serial-ipw
# ###############################
#
KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_USB_SERIAL_IPW, \
	required          = y|m, \
	m_rdepends        = kernel-module-ipw, \
	m_autoload        = ipw, \
	m_autoload_script = usb-serial-ipw \
}"
