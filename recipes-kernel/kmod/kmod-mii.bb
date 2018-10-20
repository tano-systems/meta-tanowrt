# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>

PR = "tano0"
SUMMARY = "MII library"
LICENSE = "MIT"

inherit kernel-config-depends

#
# kmod-mii
# #############
#
KERNEL_CONFIG_DEPENDS += "{\
	option              = CONFIG_MII, \
	required            = y|m, \
	m_rdepends          = kernel-module-mii , \
	m_autoload          = mii, \
	m_autoload_early    = true, \
	m_autoload_priority = 16, \
	m_autoload_script   = mii \
}"
