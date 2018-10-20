# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>

PR = "tano0"
SUMMARY = "Support for Sierra Wireless devices"
LICENSE = "MIT"

inherit kernel-config-depends

RDEPENDS_${PN} += "\
	kmod-usb-net \
"

#
# kmod-usb-net-sierrawireless
# ###############################
#
KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_USB_SIERRA_NET, \
	required          = y|m, \
	m_rdepends        = kernel-module-sierra-net, \
	m_autoload        = sierra-net, \
	m_autoload_script = usb-net-sierrawireless \
}"
