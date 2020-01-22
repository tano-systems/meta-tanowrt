# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>

PR = "tano0"
SUMMARY = "Netlink-based userspace interface"
LICENSE = "MIT"

inherit kernel-kmod

#
# kmod-nfnetlink
# ##############
#
KERNEL_CONFIG_DEPENDS += "{\
	option              = CONFIG_NETFILTER_NETLINK, \
	required            = y|m, \
	m_rdepends          = kernel-module-nfnetlink, \
	m_autoload          = nfnetlink, \
	m_autoload_script   = nfnetlink \
}"
