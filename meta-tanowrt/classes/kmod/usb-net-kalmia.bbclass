#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2018, 2020 Tano Systems LLC
# Author: Anton Kikin <a.kikin@tano-systems.com>
#
# Kernel support for Samsung Kalmia based LTE USB modem
#

inherit kernel-kmod
inherit kmod/usb-net

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
