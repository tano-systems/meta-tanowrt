# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>
# Kernel support for USB CDC Ethernet devices

PR = "tano0"
SUMMARY = "Support for CDC ethernet connections"
LICENSE = "MIT"

inherit kernel-config-depends

RDEPENDS_${PN} += "\
	kmod-usb-net \
"

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
