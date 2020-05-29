# Copyright (C) 2018, 2020 Tano Systems LLC
# Author: Anton Kikin <a.kikin@tano-systems.com>

inherit kernel-kmod
inherit kmod/crypto-manager

#
# kmod-crypto-ecb
# ##################
#
KERNEL_CONFIG_DEPENDS += "{\
	option              = CONFIG_CRYPTO_ECB, \
	required            = y|m, \
	m_rdepends          = kernel-module-ecb, \
	m_autoload          = ecb, \
	m_autoload_priority = 9, \
	m_autoload_script   = crypto-ecb \
}"
