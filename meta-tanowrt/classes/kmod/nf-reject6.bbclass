# Copyright (C) 2018, 2020 Tano Systems LLC
# Author: Anton Kikin <a.kikin@tano-systems.com>

inherit kernel-kmod

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
	option            = CONFIG_NF_REJECT_IPV6, \
	required          = y|m, \
	m_rdepends        = kernel-module-nf-reject-ipv6, \
	m_autoload        = nf_reject_ipv6, \
	m_autoload_script = nf-reject6 \
}"
