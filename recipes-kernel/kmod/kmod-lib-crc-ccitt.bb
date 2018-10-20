# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>

PR = "tano0"
SUMMARY = "CRC-CCITT support"
LICENSE = "MIT"

inherit kernel-config-depends

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
