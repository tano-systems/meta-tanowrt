# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>

PR = "tano0"
SUMMARY = "QMI WWAN driver"
LICENSE = "MIT"

inherit kernel-config-depends

RDEPENDS_${PN} += "\
	kmod-usb-net \
	kmod-usb-wdm \
"

#
# kmod-usb-net-qmi-wwan
# #####################
#
KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_USB_NET_QMI_WWAN, \
	required          = y|m, \
	m_rdepends        = kernel-module-qmi-wwan, \
	m_autoload        = qmi_wwan, \
	m_autoload_script = usb-net-qmi-wwan \
}"
