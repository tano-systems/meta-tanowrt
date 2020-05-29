# Copyright (C) 2018, 2020 Tano Systems LLC
# Author: Anton Kikin <a.kikin@tano-systems.com>

inherit kernel-kmod

inherit kmod/crypto-aead
inherit kmod/crypto-hash

#
# kmod-crypto-manager
# ######################
#
KERNEL_CONFIG_DEPENDS += "{\
	option              = CONFIG_CRYPTO_MANAGER, \
	required            = y|m, \
	m_rdepends          = kernel-module-cryptomgr, \
	m_autoload          = cryptomgr, \
	m_autoload_early    = true, \
	m_autoload_priority = 9, \
	m_autoload_script   = crypto-manager \
}"

KERNEL_CONFIG_DEPENDS += "{\
	option              = CONFIG_CRYPTO_MANAGER2, \
	required            = y|m \
}"
