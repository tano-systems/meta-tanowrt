#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2018, 2020 Tano Systems LLC
# Author: Anton Kikin <a.kikin@tano-systems.com>
#

inherit kernel-kmod
inherit kmod/usb-core

#
# kmod-usb-serial
# ###############
#
KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_USB_SERIAL, \
	required          = y|m, \
	m_rdepends        = kernel-module-usbserial, \
	m_autoload        = usbserial, \
	m_autoload_script = usb-serial \
}"
