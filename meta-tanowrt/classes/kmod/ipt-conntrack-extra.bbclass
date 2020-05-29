# Copyright (C) 2018, 2020 Tano Systems LLC
# Author: Anton Kikin <a.kikin@tano-systems.com>
#
# Netfilter (IPv4) extra kernel modules for connection tracking

inherit kernel-kmod

inherit kmod/ipt-core
inherit kmod/ipt-conntrack

#
# kmod-ipt-conntrack-extra
# ###########################
#
KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_NETFILTER_XT_MATCH_CONNBYTES, \
	required          = y|m, \
	m_rdepends        = kernel-module-xt-connbytes, \
	m_autoload        = xt_connbytes, \
	m_autoload_script = ipt-conntrack-extra \
}"

KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_NETFILTER_XT_MATCH_CONNLIMIT, \
	required          = y|m, \
	m_rdepends        = kernel-module-xt-connlimit, \
	m_autoload        = xt_connlimit, \
	m_autoload_script = ipt-conntrack-extra \
}"

KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_NETFILTER_XT_CONNMARK, \
	required          = y|m, \
	m_rdepends        = kernel-module-xt-connmark, \
	m_autoload        = xt_connmark, \
	m_autoload_script = ipt-conntrack-extra \
}"

KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_NETFILTER_XT_MATCH_HELPER, \
	required          = y|m, \
	m_rdepends        = kernel-module-xt-helper, \
	m_autoload        = xt_helper, \
	m_autoload_script = ipt-conntrack-extra \
}"

KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_NETFILTER_XT_MATCH_RECENT, \
	required          = y|m, \
	m_rdepends        = kernel-module-xt-recent, \
	m_autoload        = xt_recent, \
	m_autoload_script = ipt-conntrack-extra \
}"

