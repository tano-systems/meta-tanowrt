#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2018, 2020 Tano Systems LLC
# Author: Anton Kikin <a.kikin@tano-systems.com>
#
# Traffic shaper conntrack mark support
#

inherit kernel-kmod

inherit kmod/sched-core
inherit kmod/ipt-core
inherit kmod/ipt-conntrack-extra

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
