# Copyright (C) 2018, 2020 Anton Kikin <a.kikin@tano-systems.com>

PR = "tano1"
SUMMARY = "Null CryptoAPI module"
LICENSE = "MIT"

inherit kernel-kmod

RDEPENDS_${PN} += "\
	kmod-crypto-hash \
"

#
# kmod-crypto-null
# ######################
#
KERNEL_CONFIG_DEPENDS += "{\
	option              = CONFIG_CRYPTO_NULL2, \
	required            = y|m, \
	m_rdepends          = kernel-module-crypto-null, \
	version             = '>= 4.3.0', \
	m_autoload          = crypto_null, \
	m_autoload_priority = 9, \
	m_autoload_script   = crypto-null \
}"

KERNEL_CONFIG_DEPENDS += "{\
	option              = CONFIG_CRYPTO_NULL, \
	required            = y|m \
}"
