# Copyright (C) 2018, 2020 Tano Systems LLC
# Author: Anton Kikin <a.kikin@tano-systems.com>

inherit kernel-kmod
inherit kmod/usb-serial

#
# kmod-usb-serial-sierrawireless
# ###############################
#
KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_USB_SERIAL_SIERRAWIRELESS, \
	required          = y|m, \
	m_rdepends        = kernel-module-sierra, \
	m_autoload        = sierra, \
	m_autoload_script = usb-serial-sierrawireless \
}"
