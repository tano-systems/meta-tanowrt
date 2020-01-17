# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>
# Kernel support for Apple iPhone USB Ethernet driver

PR = "tano0"
SUMMARY = "Apple iPhone USB Ethernet driver"
LICENSE = "MIT"

inherit kernel-kmod

RDEPENDS_${PN} += "\
	kmod-usb-net \
"

#
# kmod-usb-net-ipheth
# #####################
#
KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_USB_IPHETH, \
	required          = y|m, \
	m_rdepends        = kernel-module-ipheth, \
	m_autoload        = ipheth, \
	m_autoload_script = usb-net-ipheth \
}"
