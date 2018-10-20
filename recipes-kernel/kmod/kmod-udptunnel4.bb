# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>

PR = "tano0"
SUMMARY = "IPv4 UDP tunneling support"
LICENSE = "MIT"

inherit kernel-config-depends

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
