# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>
# Kernel support for USB GSM and CDMA modems

PR = "tano0"
SUMMARY = "Support for GSM and CDMA modems"
LICENSE = "MIT"

inherit kernel-kmod

RDEPENDS_${PN} += "\
	kmod-usb-serial \
"

#
# kmod-usb-serial-wwan
# ###############################
#
KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_USB_SERIAL_WWAN, \
	required          = y|m, \
	m_rdepends        = kernel-module-usb-wwan, \
	m_autoload        = usb_wwan, \
	m_autoload_script = usb-serial-wwan \
}"
