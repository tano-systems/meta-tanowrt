#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2018, 2020 Tano Systems LLC
# Author: Anton Kikin <a.kikin@tano-systems.com>
#

inherit kernel-kmod
inherit kmod/usb-net

#
# kmod-usb-net-cdc-ncm
# #####################
#
KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_USB_NET_CDC_NCM, \
	required          = y|m, \
	m_rdepends        = kernel-module-cdc-ncm, \
	m_autoload        = cdc_ncm, \
	m_autoload_script = usb-net-cdc-ncm \
}"
