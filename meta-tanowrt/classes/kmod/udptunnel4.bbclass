# Copyright (C) 2018, 2020 Tano Systems LLC
# Author: Anton Kikin <a.kikin@tano-systems.com>

inherit kernel-kmod

#
# kmod-udptunnel4
# #################
#
KERNEL_CONFIG_DEPENDS += "{\
	option              = CONFIG_NET_UDP_TUNNEL, \
	required            = y|m, \
	m_rdepends          = kernel-module-udp-tunnel, \
	m_autoload          = udp_tunnel, \
	m_autoload_priority = 32, \
	m_autoload_script   = udptunnel4 \
}"

#KERNEL_CONFIG_DEPENDS += "{\
#	option              = CONFIG_VXLAN, \
#	required            = y|m \
#}"
