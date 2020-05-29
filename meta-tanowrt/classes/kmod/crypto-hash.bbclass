# Copyright (C) 2018, 2020 Tano Systems LLC
# Author: Anton Kikin <a.kikin@tano-systems.com>

inherit kernel-kmod

#
# kmod-crypto-hash
# ##################
#
KERNEL_CONFIG_DEPENDS += "{\
	option              = CONFIG_CRYPTO_HASH, \
	required            = y|m, \
	m_rdepends          = kernel-module-crypto-hash, \
	m_autoload          = crypto_hash, \
	m_autoload_early    = true, \
	m_autoload_priority = 2, \
	m_autoload_script   = crypto-hash \
}"
