# Copyright (C) 2018, 2020 Tano Systems LLC
# Author: Anton Kikin <a.kikin@tano-systems.com>

inherit kernel-kmod

inherit kmod/ppp
inherit kmod/pppox

#
# kmod-pppoe
# ############
#
KERNEL_CONFIG_DEPENDS += "{\
	option              = CONFIG_PPPOE, \
	required            = y|m, \
	m_rdepends          = kernel-module-pppoe, \
	m_autoload          = pppoe, \
	m_autoload_script   = pppoe \
}"
