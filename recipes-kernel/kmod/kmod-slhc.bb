# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>

PR = "tano0"
SUMMARY = "Serial Line Header Compression"
LICENSE = "MIT"

inherit kernel-config-depends

RDEPENDS_${PN} += "\
	kmod-lib-crc-ccitt \
"

#
# kmod-slhc
# ############
#
KERNEL_CONFIG_DEPENDS += "{\
	option              = CONFIG_SLHC, \
	required            = y|m, \
	m_rdepends          = kernel-module-slhc, \
	m_autoload          = slhc, \
	m_autoload_script   = slhc \
}"
