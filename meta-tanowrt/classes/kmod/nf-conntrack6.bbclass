#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2018, 2020 Tano Systems LLC
# Author: Anton Kikin <a.kikin@tano-systems.com>
#

inherit kernel-kmod
inherit kmod/nf-conntrack

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
	version           = '< 4.18.0', \
	m_rdepends        = kernel-module-nf-conntrack-ipv6, \
	m_autoload        = nf_conntrack_ipv6, \
	m_autoload_script = nf-conntrack6 \
}"
