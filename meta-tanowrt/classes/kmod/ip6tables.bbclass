#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2018, 2020 Tano Systems LLC
# Author: Anton Kikin <a.kikin@tano-systems.com>
#

inherit kernel-kmod

inherit kmod/nf-reject6
inherit kmod/nf-ipt6
inherit kmod/ipt-core

#
# kmod-ip6tables
# ##################
#
KERNEL_CONFIG_DEPENDS += "{\
	option              = CONFIG_IP6_NF_FILTER, \
	required            = y|m, \
	m_rdepends          = kernel-module-ip6table-filter, \
	m_autoload          = ip6table_filter, \
	m_autoload_priority = 42, \
	m_autoload_script   = ipt6tables \
}"

KERNEL_CONFIG_DEPENDS += "{\
	option              = CONFIG_IP6_NF_MANGLE, \
	required            = y|m, \
	m_rdepends          = kernel-module-ip6table-mangle, \
	m_autoload          = ip6table_mangle, \
	m_autoload_priority = 42, \
	m_autoload_script   = ipt6tables \
}"

#KERNEL_CONFIG_DEPENDS += "{\
#	option              = CONFIG_IP6_NF_QUEUE, \
#	required            = y|m, \
#	m_rdepends          = kernel-module-ip6-queue, \
#	m_autoload          = ip6_queue, \
#	m_autoload_priority = 42, \
#	m_autoload_script   = ipt6tables \
#}"

KERNEL_CONFIG_DEPENDS += "{\
	option              = CONFIG_NF_LOG_IPV6, \
	required            = y|m, \
	m_rdepends          = kernel-module-nf-log-ipv6, \
	m_autoload          = nf_log_ipv6, \
	m_autoload_priority = 42, \
	m_autoload_script   = ipt6tables \
}"

#KERNEL_CONFIG_DEPENDS += "{\
#	option              = CONFIG_IP6_NF_TARGET_LOG, \
#	required            = y|m, \
#	m_rdepends          = kernel-module-ip6t-log, \
#	m_autoload          = ip6t_LOG, \
#	m_autoload_priority = 42, \
#	m_autoload_script   = ipt6tables \
#}"

KERNEL_CONFIG_DEPENDS += "{\
	option              = CONFIG_IP6_NF_TARGET_REJECT, \
	required            = y|m, \
	m_rdepends          = kernel-module-ip6t-reject, \
	m_autoload          = ip6t_REJECT, \
	m_autoload_priority = 42, \
	m_autoload_script   = ipt6tables \
}"

