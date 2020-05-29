# Copyright (C) 2018, 2020 Tano Systems LLC
# Author: Anton Kikin <a.kikin@tano-systems.com>
#
# Generic Routing Encapsulation support

inherit kernel-kmod
inherit kmod/iptunnel

#
# kmod-gre
# ############
#
KERNEL_CONFIG_DEPENDS += "{\
	option              = CONFIG_NET_IPGRE, \
	required            = y|m, \
	m_rdepends          = kernel-module-ip-gre \
}"

KERNEL_CONFIG_DEPENDS += "{\
	option              = CONFIG_NET_IPGRE_DEMUX, \
	required            = y|m \
}"
