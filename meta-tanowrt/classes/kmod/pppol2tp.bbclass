#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2018, 2020 Tano Systems LLC
# Author: Anton Kikin <a.kikin@tano-systems.com>
#

inherit kernel-kmod

inherit kmod/ppp
inherit kmod/pppox
inherit kmod/l2tp

#
# kmod-pppol2tp
# ###############
#
KERNEL_CONFIG_DEPENDS += "{\
	option              = CONFIG_PPPOL2TP, \
	required            = y|m, \
	m_rdepends          = kernel-module-l2tp-ppp, \
	m_autoload          = l2tp_ppp, \
	m_autoload_script   = pppol2tp \
}"
