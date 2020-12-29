#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2018, 2020 Tano Systems LLC
# Author: Anton Kikin <a.kikin@tano-systems.com>
#
# Modules for matching/changing IP packet options
#

inherit kernel-kmod
inherit kmod/ipt-core

#
# kmod-ipt-ipopt
# ################
#
KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_NETFILTER_XT_MATCH_DSCP, \
	required          = y|m, \
	m_rdepends        = kernel-module-xt-dscp, \
	m_autoload        = xt_dscp, \
	m_autoload_script = ipt-ipopt \
}"

KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_NETFILTER_XT_TARGET_DSCP, \
	required          = y|m, \
	m_rdepends        = kernel-module-xt-dscp, \
	m_autoload        = xt_DSCP, \
	m_autoload_script = ipt-ipopt \
}"

KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_NETFILTER_XT_MATCH_LENGTH, \
	required          = y|m, \
	m_rdepends        = kernel-module-xt-length, \
	m_autoload        = xt_length, \
	m_autoload_script = ipt-ipopt \
}"

KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_NETFILTER_XT_MATCH_STATISTIC, \
	required          = y|m, \
	m_rdepends        = kernel-module-xt-statistic, \
	m_autoload        = xt_statistic, \
	m_autoload_script = ipt-ipopt \
}"

KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_NETFILTER_XT_MATCH_TCPMSS, \
	required          = y|m, \
	m_rdepends        = kernel-module-xt-tcpmss, \
	m_autoload        = xt_tcpmss, \
	m_autoload_script = ipt-ipopt \
}"

KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_NETFILTER_XT_TARGET_CLASSIFY, \
	required          = y|m, \
	m_rdepends        = kernel-module-xt-classify, \
	m_autoload        = xt_CLASSIFY, \
	m_autoload_script = ipt-ipopt \
}"

#KERNEL_CONFIG_DEPENDS += "{\
#	option            = CONFIG_IP_NF_MATCH_DSCP, \
#	required          = y|m, \
#	m_rdepends        = kernel-module-ipt-dscp, \
#	m_autoload        = ipt_dscp, \
#	m_autoload_script = ipt-ipopt \
#}"

KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_IP_NF_TARGET_ECN, \
	required          = y|m, \
	m_rdepends        = kernel-module-ipt-ecn, \
	m_autoload        = ipt_ECN, \
	m_autoload_script = ipt-ipopt \
}"

KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_NETFILTER_XT_MATCH_ECN, \
	required          = y|m, \
	m_rdepends        = kernel-module-xt-ecn, \
	m_autoload        = xt_ecn, \
	m_autoload_script = ipt-ipopt \
}"

KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_NETFILTER_XT_MATCH_HL, \
	required          = y|m, \
	m_rdepends        = kernel-module-xt-hl, \
	m_autoload        = xt_hl, \
	m_autoload_script = ipt-ipopt \
}"

KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_NETFILTER_XT_TARGET_HL, \
	required          = y|m, \
	m_rdepends        = kernel-module-xt-hl, \
	m_autoload        = xt_HL, \
	m_autoload_script = ipt-ipopt \
}"
