#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2018, 2020 Tano Systems LLC
# Author: Anton Kikin <a.kikin@tano-systems.com>
#

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
