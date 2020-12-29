#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2018, 2020 Tano Systems LLC
# Author: Anton Kikin <a.kikin@tano-systems.com>
#

inherit kernel-kmod

inherit kmod/usb-core
inherit kmod/usb-net

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
