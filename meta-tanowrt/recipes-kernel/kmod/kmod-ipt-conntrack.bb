# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>

PR = "tano0"
SUMMARY = "Basic connection tracking modules"
LICENSE = "MIT"

inherit kernel-kmod

RDEPENDS_${PN} += "\
	kmod-nf-conntrack \
"

#
# kmod-ipt-conntrack
# ##################
#
KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_NETFILTER_XT_MATCH_STATE, \
	required          = y|m, \
	m_rdepends        = kernel-module-xt-state, \
	m_autoload        = xt_state, \
	m_autoload_script = ipt-conntrack \
}"

KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_NETFILTER_XT_TARGET_CT, \
	required          = y|m, \
	m_rdepends        = kernel-module-xt-ct, \
	m_autoload        = xt_CT, \
	m_autoload_script = ipt-conntrack \
}"

KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_NETFILTER_XT_MATCH_CONNTRACK, \
	required          = y|m, \
	m_rdepends        = kernel-module-xt-conntrack, \
	m_autoload        = xt_conntrack, \
	m_autoload_script = ipt-conntrack \
}"
