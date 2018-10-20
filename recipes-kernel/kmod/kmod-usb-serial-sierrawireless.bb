# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>

PR = "tano0"
SUMMARY = "Support for Sierra Wireless devices"
LICENSE = "MIT"

inherit kernel-config-depends

RDEPENDS_${PN} += "\
	kmod-usb-serial \
"

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
