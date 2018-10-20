# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>
# Generic Routing Encapsulation support

PR = "tano0"
SUMMARY = "GRE support"
LICENSE = "MIT"

inherit kernel-config-depends

RDEPENDS_${PN} += "\
	kmod-iptunnel \
"

#
# kmod-gre
# ############
#
KERNEL_CONFIG_DEPENDS += "{\
	option              = CONFIG_NET_IPGRE, \
	required            = y|m, \
	m_rdepends          = kernel-module-gre, \
	m_rdepends          = kernel-module-ip-gre, \
	m_autoload          = gre, \
	m_autoload          = ip_gre, \
	m_autoload_priority = 39, \
	m_autoload_script   = gre \
}"

KERNEL_CONFIG_DEPENDS += "{\
	option              = CONFIG_NET_IPGRE_DEMUX, \
	required            = y|m \
}"
