# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>

PR = "tano0"
SUMMARY = "Kernel module for MBIM Devices"
LICENSE = "MIT"

inherit kernel-config-depends

RDEPENDS_${PN} += "\
	kmod-usb-net \
	kmod-usb-wdm \
	kmod-usb-net-cdc-ncm \
"

#
# kmod-usb-net-cdc-mbim
# #####################
#
KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_USB_NET_CDC_MBIM, \
	required          = y|m, \
	m_rdepends        = kernel-module-cdc-mbim, \
	m_autoload        = cdc_mbim, \
	m_autoload_script = usb-net-cdc-mbim \
}"
