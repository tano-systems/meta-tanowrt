# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>

PR = "tano0"
SUMMARY = "Netfilter IPv6 connection tracking"
LICENSE = "MIT"

inherit kernel-config-depends

RDEPENDS_${PN} += "\
	kmod-nf-conntrack \
"

#
# kmod-nf-conntrack6
# ##################
#
KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_NF_DEFRAG_IPV6, \
	required          = y|m, \
	m_rdepends        = kernel-module-nf-defrag-ipv6, \
	m_autoload        = nf_defrag_ipv6, \
	m_autoload_script = nf-conntrack6 \
}"

KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_NF_CONNTRACK_IPV6, \
	required          = y|m, \
	m_rdepends        = kernel-module-nf-conntrack-ipv6, \
	m_autoload        = nf_conntrack_ipv6, \
	m_autoload_script = nf-conntrack6 \
}"
