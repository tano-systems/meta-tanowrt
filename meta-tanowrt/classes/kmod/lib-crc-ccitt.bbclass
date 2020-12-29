#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2018, 2020 Tano Systems LLC
# Author: Anton Kikin <a.kikin@tano-systems.com>
#

inherit kernel-kmod

#
# kmod-lib-crc-ccitt
# ####################
#
KERNEL_CONFIG_DEPENDS += "{\
	option              = CONFIG_CRC_CCITT, \
	required            = y|m, \
	m_rdepends          = kernel-module-crc-ccitt, \
	m_autoload          = crc-ccitt, \
	m_autoload_script   = crc-ccitt \
}"
