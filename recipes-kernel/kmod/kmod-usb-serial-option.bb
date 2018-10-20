# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>
# Kernel support for Option HSDPA modems

PR = "tano0"
SUMMARY = "Support for Option HSDPA modems"
LICENSE = "MIT"

inherit kernel-config-depends

RDEPENDS_${PN} += "\
	kmod-usb-serial \
"

#
# kmod-usb-serial-option
# ###############################
#
KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_USB_SERIAL_OPTION, \
	required          = y|m, \
	m_rdepends        = kernel-module-option, \
	m_autoload        = option, \
	m_autoload_script = usb-serial-option \
}"
