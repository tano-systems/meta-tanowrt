# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>

PR = "tano0"
SUMMARY = "Layer Two Tunneling Protocol (L2TP)"
LICENSE = "MIT"

inherit kernel-config-depends

RDEPENDS_${PN} += "\
	kmod-udptunnel4 \
	${@bb.utils.contains('DISTRO_FEATURES', 'ipv6', 'kmod-udptunnel6', '', d)} \
"

#
# kmod-l2tp
# ###############
#
KERNEL_CONFIG_DEPENDS += "{\
	option              = CONFIG_L2TP, \
	required            = y|m, \
	m_rdepends          = kernel-module-l2tp-core, \
	m_rdepends          = kernel-module-l2tp-netlink, \
	m_autoload          = l2tp_core, \
	m_autoload          = l2tp_netlink, \
	m_autoload_priority = 32, \
	m_autoload_script   = l2tp \
}"

KERNEL_CONFIG_DEPENDS += "{\
	option              = CONFIG_L2TP_V3, \
	required            = y \
}"
