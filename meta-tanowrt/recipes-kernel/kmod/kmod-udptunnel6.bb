# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>

PR = "tano0"
SUMMARY = "IPv6 UDP tunneling support"
LICENSE = "MIT"

inherit kernel-kmod

#
# kmod-udptunnel6
# #################
#
KERNEL_CONFIG_DEPENDS += "{\
	option              = CONFIG_NET_UDP_TUNNEL, \
	required            = y|m, \
	m_rdepends          = kernel-module-ip6-udp-tunnel, \
	m_autoload          = ip6_udp_tunnel, \
	m_autoload_priority = 32, \
	m_autoload_script   = udptunnel6 \
}"

#KERNEL_CONFIG_DEPENDS += "{\
#	option              = CONFIG_VXLAN, \
#	required            = y|m \
#}"
