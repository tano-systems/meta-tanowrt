# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>
# Intermediate Functional Block support

PR = "tano0"
SUMMARY = "Intermediate Functional Block support"
LICENSE = "MIT"

inherit kernel-config-depends

#
# kmod-ifb
# ############
#
KERNEL_CONFIG_DEPENDS += "{\
	option              = CONFIG_IFB, \
	required            = y|m, \
	m_rdepends          = kernel-module-ifb, \
	m_autoload          = ifb, \
	m_autoload_priority = 34, \
	m_autoload_script   = ifb \
}"

KERNEL_CONFIG_DEPENDS += "{\
	option              = CONFIG_NET_CLS, \
	required            = y \
}"
