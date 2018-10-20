# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>

PR = "tano0"
SUMMARY = "Support for USB-to-Serial converters"
LICENSE = "MIT"

inherit kernel-config-depends

RDEPENDS_${PN} += "\
	kmod-usb-core \
"

#
# kmod-usb-serial
# ###############
#
KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_USB_SERIAL, \
	required          = y|m, \
	m_rdepends        = kernel-module-usbserial, \
	m_autoload        = usbserial, \
	m_autoload_script = usb-serial \
}"
