#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2018, 2020 Tano Systems LLC
# Author: Anton Kikin <a.kikin@tano-systems.com>
#
# Kernel support for RNDIS connections
#

inherit kernel-kmod

inherit kmod/usb-net
inherit kmod/usb-net-cdc-ether

#
# kmod-usb-net-rndis
# ########################
#
KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_USB_NET_RNDIS_HOST, \
	required          = y|m, \
	m_rdepends        = kernel-module-rndis-host, \
	m_autoload        = rndis_host, \
	m_autoload_script = usb-net-rndis \
}"
