# Copyright (C) 2018, 2020 Tano Systems LLC
# Author: Anton Kikin <a.kikin@tano-systems.com>
#
# Kernel support for USB GSM and CDMA modems

inherit kernel-kmod
inherit kmod/usb-serial

#
# kmod-usb-serial-wwan
# ###############################
#
KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_USB_SERIAL_WWAN, \
	required          = y|m, \
	m_rdepends        = kernel-module-usb-wwan, \
	m_autoload        = usb_wwan, \
	m_autoload_script = usb-serial-wwan \
}"
