# Copyright (C) 2018, 2020 Tano Systems LLC
# Author: Anton Kikin <a.kikin@tano-systems.com>

inherit kernel-kmod
inherit kmod/nf-ipt

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
