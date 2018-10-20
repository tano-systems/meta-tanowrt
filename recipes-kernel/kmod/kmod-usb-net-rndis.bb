# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>
# Kernel support for RNDIS connections

PR = "tano0"
SUMMARY = "Support for RNDIS connections"
LICENSE = "MIT"

inherit kernel-config-depends

RDEPENDS_${PN} += "\
	kmod-usb-net \
	kmod-usb-net-cdc-ether \
"

#
# kmod-usb-net-rndis
# ########################
#
KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_USB_NET_RNDIS_HOST, \
	required          = y|m, \
	m_rdepends        = kernel-module-rndis-host, \
	m_autoload        = rndis_host, \
	m_autoload_script = usb-net-rndis \
}"
