# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>
# Kernel support for USB Ethernet Gadget

PR = "tano0"
SUMMARY = "Kernel support for USB Ethernet Gadget"
LICENSE = "MIT"

inherit kernel-kmod

RDEPENDS_${PN} += "\
	kmod-usb-gadget \
	kmod-usb-lib-composite \
"

#
# kmod-usb-gadget-usb-eth
# ########################
#
KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_USB_ETH_EEM, \
	required          = n, \
}"

KERNEL_CONFIG_DEPENDS += "{\
	option              = CONFIG_USB_ETH, \
	required            = y|m, \
	m_rdepends          = kernel-module-g-ether, \
	m_autoload          = g_ether, \
	m_autoload_priority = 52, \
	m_autoload_script   = usb-gadget-eth, \
}"

KERNEL_CONFIG_DEPENDS += "{\
	option              = CONFIG_USB_U_ETHER, \
	required            = y|m, \
	m_rdepends          = kernel-module-u-ether, \
	m_autoload          = u_ether, \
	m_autoload_priority = 52, \
	m_autoload_script   = usb-gadget-eth, \
}"

KERNEL_CONFIG_DEPENDS += "{\
	option              = CONFIG_USB_F_ECM, \
	required            = y|m, \
	m_rdepends          = kernel-module-usb-f-ecm, \
	m_autoload          = usb_f_ecm, \
	m_autoload_priority = 52, \
	m_autoload_script   = usb-gadget-eth, \
}"

KERNEL_CONFIG_DEPENDS += "{\
	option              = CONFIG_USB_F_SUBSET, \
	required            = y|m, \
	m_rdepends          = kernel-module-usb-f-ecm-subnet, \
	m_autoload          = usb_f_ecm_subnet, \
	m_autoload_priority = 52, \
	m_autoload_script   = usb-gadget-eth, \
}"

KERNEL_CONFIG_DEPENDS += "{\
	option              = CONFIG_USB_ETH_RNDIS, \
	required            = y|m,, \
	m_rdepends          = kernel-module-usb-f-rndis, \
	m_autoload          = usb_f_rndis, \
	m_autoload_priority = 52, \
	m_autoload_script   = usb-gadget-eth, \
}"
