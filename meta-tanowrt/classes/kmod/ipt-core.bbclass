#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2018-2020 Tano Systems LLC
# Author: Anton Kikin <a.kikin@tano-systems.com>
#

inherit kernel-kmod

inherit kmod/nf-reject
inherit kmod/nf-ipt

#
# kmod-ipt-core
# #############
#
KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_NETFILTER_XTABLES, \
	required          = y|m, \
	m_rdepends        = kernel-module-xt-tcpudp, \
	m_autoload        = xt_tcpudp, \
	m_autoload_script = ipt-core \
}"

KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_IP_NF_FILTER, \
	required          = y|m, \
	m_rdepends        = kernel-module-iptable-filter, \
	m_autoload        = iptable_filter, \
	m_autoload_script = ipt-core \
}"

KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_IP_NF_MANGLE, \
	required          = y|m, \
	m_rdepends        = kernel-module-iptable-mangle, \
	m_autoload        = iptable_mangle, \
	m_autoload_script = ipt-core \
}"

KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_IP_NF_IPTABLES, \
	required          = y|m, \
	m_rdepends        = kernel-module-xt-comment, \
	m_autoload        = xt_comment, \
	m_autoload_script = ipt-core \
}"

KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_NETFILTER_XT_MATCH_LIMIT, \
	required          = y|m, \
	m_rdepends        = kernel-module-xt-limit, \
	m_autoload        = xt_limit, \
	m_autoload_script = ipt-core \
}"

KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_NETFILTER_XT_MATCH_MAC, \
	required          = y|m, \
	m_rdepends        = kernel-module-xt-mac, \
	m_autoload        = xt_mac, \
	m_autoload_script = ipt-core \
}"

KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_NETFILTER_XT_MATCH_MULTIPORT, \
	required          = y|m, \
	m_rdepends        = kernel-module-xt-multiport, \
	m_autoload        = xt_multiport, \
	m_autoload_script = ipt-core \
}"

KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_NETFILTER_XT_MATCH_COMMENT, \
	required          = y|m, \
	m_rdepends        = kernel-module-xt-comment, \
	m_autoload        = xt_comment, \
	m_autoload_script = ipt-core \
}"

KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_NETFILTER_XT_TARGET_LOG, \
	required          = y|m, \
	m_rdepends        = kernel-module-xt-log, \
	m_autoload        = xt_LOG, \
	m_autoload_script = ipt-core \
}"

KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_NF_LOG_IPV4, \
	required          = y|m, \
	m_rdepends        = kernel-module-nf-log-ipv4, \
	m_autoload        = nf_log_ipv4, \
	m_autoload_script = ipt-core \
}"

KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_NF_LOG_COMMON, \
	required          = y|m, \
	m_rdepends        = kernel-module-nf-log-common, \
	m_autoload        = nf_log_common, \
	m_autoload_script = ipt-core \
}"

KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_NETFILTER_XT_TARGET_TCPMSS, \
	required          = y|m, \
	m_rdepends        = kernel-module-xt-tcpmss, \
	m_autoload        = xt_TCPMSS, \
	m_autoload_script = ipt-core \
}"

KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_IP_NF_TARGET_REJECT, \
	required          = y|m, \
	m_rdepends        = kernel-module-ipt-reject, \
	m_autoload        = ipt_REJECT, \
	m_autoload_script = ipt-core \
}"

KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_NETFILTER_XT_MATCH_TIME, \
	required          = y|m, \
	m_rdepends        = kernel-module-xt-time, \
	m_autoload        = xt_time, \
	m_autoload_script = ipt-core \
}"

KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_NETFILTER_XT_MARK, \
	required          = y|m, \
	m_rdepends        = kernel-module-xt-mark, \
	m_autoload        = xt_mark, \
	m_autoload_script = ipt-core \
}"
