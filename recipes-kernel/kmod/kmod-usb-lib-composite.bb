# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>
# USB lib composite

PR = "tano0"
SUMMARY = "USB lib composite"
LICENSE = "MIT"

inherit kernel-config-depends

RDEPENDS_${PN} += "\
	kmod-usb-gadget \
	kmod-fs-configfs \
"

#
# kmod-usb-lib-composite
# ########################
#
KERNEL_CONFIG_DEPENDS += "{\
	option              = CONFIG_USB_LIBCOMPOSITE, \
	required            = y|m, \
	m_rdepends          = kernel-module-libcomposite, \
	m_autoload          = libcomposite, \
	m_autoload_priority = 50, \
	m_autoload_script   = usb-lib-composite \
}"
