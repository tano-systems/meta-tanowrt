# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>

PR = "tano0"
SUMMARY = "802.1d Ethernet Bridging"
LICENSE = "MIT"

inherit kernel-kmod

#
# kmod-bridge
# ##################
#
KERNEL_CONFIG_DEPENDS += "{\
	option              = CONFIG_BRIDGE, \
	required            = y|m, \
	m_rdepends          = kernel-module-bridge, \
	m_autoload          = bridge, \
	m_autoload_priority = 30, \
	m_autoload_script   = bridge \
}"
