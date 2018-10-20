# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>

PR = "tano0"
SUMMARY = "Support for CDC NCM connections"
LICENSE = "MIT"

inherit kernel-config-depends

RDEPENDS_${PN} += "\
	kmod-usb-net \
"

#
# kmod-usb-net-cdc-ncm
# #####################
#
KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_USB_NET_CDC_NCM, \
	required          = y|m, \
	m_rdepends        = kernel-module-cdc-ncm, \
	m_autoload        = cdc_ncm, \
	m_autoload_script = usb-net-cdc-ncm \
}"
