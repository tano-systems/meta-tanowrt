#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2018, 2020 Tano Systems LLC
# Author: Anton Kikin <a.kikin@tano-systems.com>
#

inherit kernel-kmod
inherit kmod/crypto-hash

#
# kmod-crypto-sha1
# ##################
#
KERNEL_CONFIG_DEPENDS += "{\
	option              = CONFIG_CRYPTO_SHA1, \
	required            = y|m, \
	m_rdepends          = kernel-module-sha1-generic, \
	m_autoload          = sha1_generic, \
	m_autoload_priority = 9, \
	m_autoload_script   = crypto-sha1 \
}"
