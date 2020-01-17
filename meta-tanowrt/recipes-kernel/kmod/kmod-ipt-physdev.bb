# Copyright (C) 2018-2019 Anton Kikin <a.kikin@tano-systems.com>

PR = "tano0"
SUMMARY = "The iptables physdev kernel module"
LICENSE = "MIT"

inherit kernel-kmod

RDEPENDS_${PN} += "\
	kmod-ipt-core \
	kmod-br-netfilter \
"

#
# kmod-ipt-physdev
# ################
#
KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_NETFILTER_XT_MATCH_PHYSDEV, \
	required          = y|m, \
	m_rdepends        = kernel-module-xt-physdev, \
	m_autoload        = xt_physdev, \
	m_autoload_script = ipt-physdev \
}"

