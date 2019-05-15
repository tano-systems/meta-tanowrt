# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>

PR = "tano0"
SUMMARY = "AUTOFS4 filesystem support"
LICENSE = "MIT"

inherit kernel-kmod

#
# kmod-fs-autofs4
# ###############
#
KERNEL_CONFIG_DEPENDS += "{\
	option              = CONFIG_AUTOFS4_FS, \
	required            = y|m, \
	m_rdepends          = kernel-module-autofs4, \
	m_autoload          = autofs4, \
	m_autoload_priority = 30, \
	m_autoload_script   = autofs4 \
}"
