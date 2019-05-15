# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>
# Kernel support for IPWireless 3G devices

PR = "tano0"
SUMMARY = "Support for IPWireless 3G devices"
LICENSE = "MIT"

inherit kernel-kmod

RDEPENDS_${PN} += "\
	kmod-usb-serial \
	kmod-usb-serial-wwan \
"

#
# kmod-usb-serial-ipw
# ###############################
#
KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_USB_SERIAL_IPW, \
	required          = y|m, \
	m_rdepends        = kernel-module-ipw, \
	m_autoload        = ipw, \
	m_autoload_script = usb-serial-ipw \
}"
