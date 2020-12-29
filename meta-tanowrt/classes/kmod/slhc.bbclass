#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2018, 2020 Tano Systems LLC
# Author: Anton Kikin <a.kikin@tano-systems.com>
#

inherit kernel-kmod
inherit kmod/lib-crc-ccitt

#
# kmod-slhc
# ############
#
KERNEL_CONFIG_DEPENDS += "{\
	option              = CONFIG_SLHC, \
	required            = y|m, \
	m_rdepends          = kernel-module-slhc, \
	m_autoload          = slhc, \
	m_autoload_script   = slhc \
}"
