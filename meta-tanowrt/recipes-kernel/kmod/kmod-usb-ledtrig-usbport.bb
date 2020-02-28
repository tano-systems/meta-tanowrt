# Copyright (C) 2020 Anton Kikin <a.kikin@tano-systems.com>

PR = "tano0"
SUMMARY = "USB port LED trigger"
LICENSE = "MIT"

inherit kernel-kmod

RDEPENDS_${PN} += "\
	kmod-usb-core \
"

#
# kmod-usb-ledtrig-usbport
# ##############################
#
KERNEL_CONFIG_DEPENDS += "{\
	option              = CONFIG_USB_LEDS_TRIGGER_USBPORT, \
	required            = y|m, \
	m_rdepends          = kernel-module-ledtrig-usbport, \
	m_autoload          = ledtrig-usbport, \
	m_autoload_script   = usb-ledtrig \
}"
