#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2020 Tano Systems LLC
# Author: Anton Kikin <a.kikin@tano-systems.com>
#

inherit kernel-kmod
inherit kmod/usb-core

#
# kmod-usb-ledtrig-usbport
# ##############################
#
KERNEL_CONFIG_DEPENDS += "{\
	option              = CONFIG_USB_LEDS_TRIGGER_USBPORT, \
	required            = y|m, \
	m_rdepends          = kernel-module-ledtrig-usbport, \
	m_autoload          = ledtrig-usbport, \
	m_autoload_script   = usb-ledtrig \
}"
