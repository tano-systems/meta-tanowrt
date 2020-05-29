# Copyright (C) 2018, 2020 Tano Systems LLC
# Author: Anton Kikin <a.kikin@tano-systems.com>

inherit kernel-kmod

inherit kmod/ppp
inherit kmod/atm

#
# kmod-pppoa
# ############
#
KERNEL_CONFIG_DEPENDS += "{\
	option              = CONFIG_PPPOATM, \
	required            = y|m, \
	m_rdepends          = kernel-module-pppoatm, \
	m_autoload_priority = 40, \
	m_autoload          = pppoatm, \
	m_autoload_script   = pppoa \
}"

KERNEL_CONFIG_DEPENDS += "{\
	option              = CONFIG_ATM_DRIVERS, \
	required            = y \
}"
