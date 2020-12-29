#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2018, 2020 Tano Systems LLC
# Author: Anton Kikin <a.kikin@tano-systems.com>
#

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
