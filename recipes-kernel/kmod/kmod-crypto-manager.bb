# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>

PR = "tano0"
SUMMARY = "CryptoAPI algorithm manager"
LICENSE = "MIT"

inherit kernel-config-depends

RDEPENDS_${PN} += "\
	kmod-crypto-aead \
	kmod-crypto-hash \
"

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
