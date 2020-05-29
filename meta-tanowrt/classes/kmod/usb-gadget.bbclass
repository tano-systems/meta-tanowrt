# Copyright (C) 2018, 2020 Tano Systems LLC
# Author: Anton Kikin <a.kikin@tano-systems.com>
#
# Kernel support for USB Gadget mode

inherit kernel-kmod

#
# kmod-usb-gadget
# ########################
#
KERNEL_CONFIG_DEPENDS += "{\
	option              = CONFIG_USB_GADGET, \
	required            = y|m, \
	m_rdepends          = kernel-module-udc-core, \
	m_autoload          = udc_core, \
	m_autoload_early    = true, \
	m_autoload_priority = 21, \
	m_autoload_script   = usb-gadget \
}"
