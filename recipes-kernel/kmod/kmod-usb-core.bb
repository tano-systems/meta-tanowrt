# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>

PR = "tano0"
SUMMARY = "Support for USB"
LICENSE = "MIT"

inherit kernel-kmod

#
# kmod-usb-core
# #############
#
KERNEL_CONFIG_DEPENDS += "{\
	option              = CONFIG_USB, \
	required            = y|m, \
	m_rdepends          = kernel-module-usbcore, \
	m_rdepends          = kernel-module-usb-common, \
	m_autoload          = usbcore, \
	m_autoload          = usb-common, \
	m_autoload_priority = 20, \
	m_autoload_script   = usb-core \
}"

KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_USB_SUPPORT, \
	required          = y \
}"

KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_XPS_USB_HCD_XILINX, \
	required          = n \
}"

KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_USB_FHCI_HCD, \
	required          = n \
}"

