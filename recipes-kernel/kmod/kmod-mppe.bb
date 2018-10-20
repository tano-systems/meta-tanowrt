# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>
# Kernel modules for Microsoft PPP compression/encryption

PR = "tano0"
SUMMARY = "Microsoft PPP compression/encryption"
LICENSE = "MIT"

inherit kernel-config-depends

RDEPENDS_${PN} += "\
	kmod-ppp \
	kmod-crypto-sha1 \
	kmod-crypto-ecb \
"

#
# kmod-mppe
# ###############
#
KERNEL_CONFIG_DEPENDS += "{\
	option              = CONFIG_PPP_MPPE, \
	required            = y|m, \
	m_rdepends          = kernel-module-ppp-mppe, \
	m_autoload          = ppp_mppe, \
	m_autoload_script   = mppe \
}"
