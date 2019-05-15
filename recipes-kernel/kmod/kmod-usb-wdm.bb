# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>

PR = "tano0"
SUMMARY = "USB Wireless Device Management"
LICENSE = "MIT"

inherit kernel-kmod

RDEPENDS_${PN} += "\
	kmod-usb-core \
	kmod-usb-net \
"

#
# kmod-usb-wdm
# ############
#
KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_USB_WDM, \
	required          = y|m, \
	m_rdepends        = kernel-module-cdc-wdm, \
	m_autoload        = cdc-wdm, \
	m_autoload_script = usb-wdm \
}"
