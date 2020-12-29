#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2018, 2020 Tano Systems LLC
# Author: Anton Kikin <a.kikin@tano-systems.com>
#

inherit kernel-kmod

inherit kmod/ppp
inherit kmod/gre
inherit kmod/pppox

#
# kmod-pptp
# ############
#
KERNEL_CONFIG_DEPENDS += "{\
	option              = CONFIG_PPTP, \
	required            = y|m, \
	m_rdepends          = kernel-module-pptp, \
	m_autoload          = pptp, \
	m_autoload_script   = pptp \
}"
