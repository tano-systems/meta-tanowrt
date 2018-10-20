# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>

PR = "tano0"
SUMMARY = "Netfilter IPv4 reject support"
LICENSE = "MIT"

inherit kernel-config-depends

#
# kmod-nf-reject
# ##############
#
KERNEL_CONFIG_DEPENDS += "{\
	option   = CONFIG_NETFILTER, \
	required = y \
}"

KERNEL_CONFIG_DEPENDS += "{\
	option   = CONFIG_NETFILTER_ADVANCED, \
	required = y \
}"

KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_NF_REJECT_IPV4, \
	required          = y|m, \
	m_rdepends        = kernel-module-nf-reject-ipv4, \
	m_autoload        = nf_reject_ipv4, \
	m_autoload_script = nf-reject \
}"
