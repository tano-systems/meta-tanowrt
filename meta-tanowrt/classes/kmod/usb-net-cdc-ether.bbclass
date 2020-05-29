# Copyright (C) 2018, 2020 Tano Systems LLC
# Author: Anton Kikin <a.kikin@tano-systems.com>
#
# Kernel support for USB CDC Ethernet devices

inherit kernel-kmod
inherit kmod/usb-net

#
# kmod-usb-net-cdc-ether
# ########################
#
KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_USB_NET_CDCETHER, \
	required          = y|m, \
	m_rdepends        = kernel-module-cdc-ether, \
	m_autoload        = cdc_ether, \
	m_autoload_script = usb-net-cdc-ether \
}"
