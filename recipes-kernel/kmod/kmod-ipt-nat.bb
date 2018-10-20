# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>

PR = "tano0"
SUMMARY = "Basic NAT targets"
LICENSE = "MIT"

inherit kernel-config-depends

RDEPENDS_${PN} += "\
	kmod-nf-nat \
"

#
# kmod-ipt-nat
# ############
#
KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_NETFILTER_XT_NAT, \
	required          = y|m, \
	m_rdepends        = kernel-module-xt-nat, \
	m_autoload        = xt_nat, \
	m_autoload_script = ipt-nat \
}"

KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_IP_NF_NAT, \
	required          = y|m, \
	m_rdepends        = kernel-module-iptable-nat, \
	m_autoload        = iptable_nat, \
	m_autoload_script = ipt-nat \
}"

KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_IP_NF_TARGET_MASQUERADE, \
	required          = y|m, \
	m_rdepends        = kernel-module-ipt-masquerade, \
	m_autoload        = ipt_MASQUERADE, \
	m_autoload_script = ipt-nat \
}"

KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_IP_NF_TARGET_REDIRECT, \
	required          = y|m, \
	m_rdepends        = kernel-module-xt-redirect, \
	m_autoload        = xt_REDIRECT, \
	m_autoload_script = ipt-nat \
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
