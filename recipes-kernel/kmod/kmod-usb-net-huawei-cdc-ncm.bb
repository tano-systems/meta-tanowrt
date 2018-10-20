# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>
# Kernel support for Huawei CDC NCM connections

PR = "tano0"
SUMMARY = "Support for Huawei CDC NCM connections"
LICENSE = "MIT"

inherit kernel-config-depends

RDEPENDS_${PN} += "\
	kmod-usb-net \
	kmod-usb-net-cdc-ncm \
	kmod-usb-wdm \
"

#
# kmod-usb-net-huawei-cdc-ncm
# ###############################
#
KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_USB_NET_HUAWEI_CDC_NCM, \
	required          = y|m, \
	m_rdepends        = kernel-module-huawei-cdc-ncm, \
	m_autoload        = huawei_cdc_ncm, \
	m_autoload_script = usb-net-huawei-cdc-ncm \
}"
