# Copyright (C) 2018, 2020 Tano Systems LLC
# Author: Anton Kikin <a.kikin@tano-systems.com>
#
# Kernel support for Huawei CDC NCM connections

inherit kernel-kmod

inherit kmod/usb-net
inherit kmod/usb-net-cdc-ncm
inherit kmod/usb-wdm

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
