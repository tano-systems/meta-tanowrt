# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>

PR = "tano0"
SUMMARY = "Electronic CodeBook CryptoAPI module"
LICENSE = "MIT"

inherit kernel-config-depends

RDEPENDS_${PN} += "\
	kmod-crypto-manager \
"

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
