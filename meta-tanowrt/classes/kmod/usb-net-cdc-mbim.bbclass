# Copyright (C) 2018, 2020 Tano Systems LLC
# Author: Anton Kikin <a.kikin@tano-systems.com>

inherit kernel-kmod

inherit kmod/usb-net
inherit kmod/usb-wdm
inherit kmod/usb-net-cdc-ncm

#
# kmod-usb-net-cdc-mbim
# #####################
#
KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_USB_NET_CDC_MBIM, \
	required          = y|m, \
	m_rdepends        = kernel-module-cdc-mbim, \
	m_autoload        = cdc_mbim, \
	m_autoload_script = usb-net-cdc-mbim \
}"
