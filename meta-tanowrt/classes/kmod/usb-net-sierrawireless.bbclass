#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2018, 2020 Tano Systems LLC
# Author: Anton Kikin <a.kikin@tano-systems.com>
#

inherit kernel-kmod
inherit kmod/usb-net

#
# kmod-usb-net-sierrawireless
# ###############################
#
KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_USB_SIERRA_NET, \
	required          = y|m, \
	m_rdepends        = kernel-module-sierra-net, \
	m_autoload        = sierra-net, \
	m_autoload_script = usb-net-sierrawireless \
}"
