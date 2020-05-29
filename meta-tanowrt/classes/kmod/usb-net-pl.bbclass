# Copyright (C) 2018, 2020 Tano Systems LLC
# Author: Anton Kikin <a.kikin@tano-systems.com>
#
# Kernel support for Prolific PL-2301/2302/25A1 based cables

inherit kernel-kmod

inherit kmod/usb-net

#
# kmod-usb-net-pp
# #####################
#
KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_USB_NET_PLUSB, \
	required          = y|m, \
	m_rdepends        = kernel-module-plusb, \
	m_autoload        = plusb, \
	m_autoload_script = usb-net-pl \
}"
