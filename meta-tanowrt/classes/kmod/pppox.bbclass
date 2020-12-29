#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2018, 2020 Tano Systems LLC
# Author: Anton Kikin <a.kikin@tano-systems.com>
#

inherit kernel-kmod
inherit kmod/ppp

#
# kmod-pppox
# ############
#
KERNEL_CONFIG_DEPENDS += "{\
	option              = CONFIG_PPPOE, \
	required            = y|m, \
	m_rdepends          = kernel-module-pppox, \
	m_autoload          = pppox, \
	m_autoload_script   = pppoe \
}"
