# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>

PR = "tano0"
SUMMARY = "Kernel modules for USB-to-Ethernet convertors"
LICENSE = "MIT"

inherit kernel-kmod

RDEPENDS_${PN} += "\
	kmod-mii \
	kmod-usb-core \
"

#
# kmod-usb-net
# ############
#
KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_USB_USBNET, \
	required          = y|m, \
	m_rdepends        = kernel-module-usbnet, \
	m_autoload        = usbnet, \
	m_autoload_script = usb-net \
}"

KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_USB_NET_DRIVERS, \
	required          = y|m \
}"
