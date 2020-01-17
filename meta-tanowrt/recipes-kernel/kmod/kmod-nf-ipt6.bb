# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>

PR = "tano0"
SUMMARY = "Ip6tables core"
LICENSE = "MIT"

inherit kernel-kmod

RDEPENDS_${PN} += "\
	kmod-nf-ipt \
"

#
# kmod-nf-ipt6
# #############
#
KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_IP6_NF_IPTABLES, \
	required          = y|m, \
	m_rdepends        = kernel-module-ip6-tables, \
	m_autoload        = ip6_tables, \
	m_autoload_script = nf-ipt6 \
}"
