# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>

PR = "tano0"
SUMMARY = "Iptables core"
LICENSE = "MIT"

inherit kernel-config-depends

#
# kmod-nf-ipt
# #############
#
KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_IP_NF_IPTABLES, \
	required          = y|m, \
	m_rdepends        = kernel-module-ip-tables, \
	m_autoload        = ip_tables, \
	m_autoload_script = nf-ipt \
}"

KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_NETFILTER_XTABLES, \
	required          = y|m, \
	m_rdepends        = kernel-module-x-tables, \
	m_autoload        = x_tables, \
	m_autoload_script = nf-ipt \
}"
