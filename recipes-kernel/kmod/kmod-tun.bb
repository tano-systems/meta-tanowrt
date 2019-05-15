# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>

PR = "tano0"
SUMMARY = "Universal TUN/TAP driver"
LICENSE = "MIT"

inherit kernel-kmod

#
# kmod-tun
# ############
#
KERNEL_CONFIG_DEPENDS += "{\
	option              = CONFIG_TUN, \
	required            = y|m, \
	m_rdepends          = kernel-module-tun, \
	m_autoload          = tun, \
	m_autoload_priority = 30, \
	m_autoload_script   = tun \
}"
