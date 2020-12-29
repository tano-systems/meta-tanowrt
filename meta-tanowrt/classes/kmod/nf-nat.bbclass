#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2018, 2020 Tano Systems LLC
# Author: Anton Kikin <a.kikin@tano-systems.com>
#

inherit kernel-kmod
inherit kmod/nf-conntrack

#
# kmod-nf-nat
# ###########
#
KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_NF_NAT, \
	required          = y|m, \
	m_rdepends        = kernel-module-nf-nat, \
	m_autoload        = nf_nat, \
	m_autoload_script = nf-nat \
}"

KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_NF_NAT_REDIRECT, \
	version           = '>= 4.0.0', \
	required          = y|m, \
	m_rdepends        = kernel-module-nf-nat-redirect, \
	m_autoload        = nf_nat_redirect, \
	m_autoload_script = nf-nat \
}"

KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_NF_NAT_IPV4, \
	required          = y|m, \
	m_rdepends        = kernel-module-nf-nat-ipv4, \
	m_autoload        = nf_nat_ipv4, \
	m_autoload_script = nf-nat \
}"

KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_NF_NAT_MASQUERADE_IPV4, \
	required          = y|m, \
	m_rdepends        = kernel-module-nf-nat-masquerade-ipv4, \
	m_autoload        = nf_nat_masquerade_ipv4, \
	m_autoload_script = nf-nat \
}"

