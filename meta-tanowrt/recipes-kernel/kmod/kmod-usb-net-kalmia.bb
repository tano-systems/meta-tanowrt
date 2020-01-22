# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>
# Kernel support for Samsung Kalmia based LTE USB modem

PR = "tano0"
SUMMARY = "Samsung Kalmia based LTE USB modem"
LICENSE = "MIT"

inherit kernel-kmod

RDEPENDS_${PN} += "\
	kmod-usb-net \
"

#
# kmod-usb-net-kalmia
# #####################
#
KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_USB_NET_KALMIA, \
	required          = y|m, \
	m_rdepends        = kernel-module-kalmia, \
	m_autoload        = kalmia, \
	m_autoload_script = usb-net-kalmia \
}"
