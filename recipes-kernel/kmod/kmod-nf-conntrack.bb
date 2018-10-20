# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>

PR = "tano0"
SUMMARY = "Netfilter connection tracking"
LICENSE = "MIT"

inherit kernel-config-depends

#
# kmod-nf-conntrack
# #################
#
KERNEL_CONFIG_DEPENDS += "{\
	option   = CONFIG_NF_CONNTRACK_MARK, \
	required = y \
}"

KERNEL_CONFIG_DEPENDS += "{\
	option   = CONFIG_NF_CONNTRACK_ZONES, \
	required = y \
}"

KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_NF_CONNTRACK, \
	required          = y|m, \
	m_rdepends        = kernel-module-nf-conntrack, \
	m_autoload        = nf_conntrack, \
	m_autoload_script = nf-conntrack \
}"

KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_NF_DEFRAG_IPV4, \
	required          = y|m, \
	m_rdepends        = kernel-module-nf-defrag-ipv4, \
	m_autoload        = nf_defrag_ipv4, \
	m_autoload_script = nf-conntrack \
}"

KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_NF_CONNTRACK_IPV4, \
	required          = y|m, \
	m_rdepends        = kernel-module-nf-conntrack-ipv4, \
	m_autoload        = nf_conntrack_ipv4, \
	m_autoload_script = nf-conntrack \
}"

# TODO: Need patch from OpenWrt
#KERNEL_CONFIG_DEPENDS += "{\
#	option            = CONFIG_NF_CONNTRACK_RTCACHE, \
#	required          = y|m, \
#	m_rdepends        = kernel-module-nf-conntrack-rtcache, \
#	m_autoload        = nf_conntrack_rtcache, \
#	m_autoload_script = nf-conntrack \
#}"
