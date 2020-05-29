# Copyright (C) 2018, 2020 Tano Systems LLC
# Author: Anton Kikin <a.kikin@tano-systems.com>
#
# Kernel module for generic IP tunnel support

inherit kernel-kmod

#
# kmod-iptunnel
# ###############
#
KERNEL_CONFIG_DEPENDS += "{\
	option              = CONFIG_NET_IP_TUNNEL, \
	required            = y|m, \
	m_rdepends          = kernel-module-ip-tunnel, \
	m_autoload          = ip_tunnel, \
	m_autoload_priority = 31, \
	m_autoload_script   = iptunnel \
}"
