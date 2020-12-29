#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2018, 2020 Tano Systems LLC
# Author: Anton Kikin <a.kikin@tano-systems.com>
#

inherit kernel-kmod

#
# kmod-bridge
# ##################
#
KERNEL_CONFIG_DEPENDS += "{\
	option              = CONFIG_BRIDGE, \
	required            = y|m, \
	m_rdepends          = kernel-module-bridge, \
	m_autoload          = bridge, \
	m_autoload_priority = 30, \
	m_autoload_script   = bridge \
}"
