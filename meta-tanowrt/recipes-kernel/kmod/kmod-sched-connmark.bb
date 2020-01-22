# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>
# Traffic shaper conntrack mark support

PR = "tano0"
SUMMARY = "Traffic shaper conntrack mark support"
LICENSE = "MIT"

inherit kernel-kmod

RDEPENDS_${PN} += "\
	kmod-sched-core \
	kmod-ipt-core \
	kmod-ipt-conntrack-extra \
"

#
# kmod-sched-connmark
# #######################
#
KERNEL_CONFIG_DEPENDS += "{\
	option              = CONFIG_NET_ACT_CONNMARK, \
	required            = y|m, \
	m_rdepends          = kernel-module-act-connmark, \
	m_autoload          = act_connmark, \
	m_autoload_priority = 71, \
	m_autoload_script   = sched-connmark \
}"
