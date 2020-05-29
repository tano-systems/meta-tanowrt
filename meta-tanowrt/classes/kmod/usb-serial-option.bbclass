# Copyright (C) 2018, 2020 Tano Systems LLC
# Author: Anton Kikin <a.kikin@tano-systems.com>
#
# Kernel support for Option HSDPA modems

inherit kernel-kmod
inherit kmod/usb-serial

#
# kmod-usb-serial-option
# ###############################
#
KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_USB_SERIAL_OPTION, \
	required          = y|m, \
	m_rdepends        = kernel-module-option, \
	m_autoload        = option, \
	m_autoload_script = usb-serial-option \
}"
