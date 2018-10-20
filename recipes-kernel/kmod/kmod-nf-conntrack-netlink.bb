# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>
# Kernel modules support for a netlink-based connection tracking
# userspace interface

PR = "tano0"
SUMMARY = "Connection tracking netlink interface"
LICENSE = "MIT"

inherit kernel-config-depends

RDEPENDS_${PN} += "\
	kmod-ipt-conntrack \
	kmod-nfnetlink \
"

#
# kmod-nf-conntrack-netlink
# ###########################
#
KERNEL_CONFIG_DEPENDS += "{\
	option   = CONFIG_NF_CONNTRACK_EVENTS, \
	required = y \
}"

KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_NF_CT_NETLINK, \
	required          = y|m, \
	m_rdepends        = kernel-module-nf-conntrack-netlink, \
	m_autoload        = nf_conntrack_netlink, \
	m_autoload_script = nf-conntrack-netlink \
}"
