#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2018, 2020 Tano Systems LLC
# Author: Anton Kikin <a.kikin@tano-systems.com>
#

inherit kernel-kmod

inherit kmod/slhc
inherit kmod/lib-crc-ccitt

#
# kmod-ppp
# ############
#
KERNEL_CONFIG_DEPENDS += "{\
	option              = CONFIG_PPP, \
	required            = y|m, \
	m_rdepends          = kernel-module-ppp-generic, \
	m_autoload          = ppp-generic, \
	m_autoload_script   = ppp \
}"

KERNEL_CONFIG_DEPENDS += "{\
	option              = CONFIG_PPP_ASYNC, \
	required            = y|m, \
	m_rdepends          = kernel-module-ppp-async, \
	m_autoload          = ppp-async, \
	m_autoload_script   = ppp \
}"
