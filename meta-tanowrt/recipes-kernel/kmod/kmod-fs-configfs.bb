# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>

PR = "tano0"
SUMMARY = "Kernel module for configfs support"
LICENSE = "MIT"

inherit kernel-kmod

#
# kmod-fs-configfs
# ################
#
KERNEL_CONFIG_DEPENDS += "{\
	option              = CONFIG_CONFIGFS_FS, \
	required            = y|m, \
	m_rdepends          = kernel-module-configfs, \
	m_autoload          = configfs, \
	m_autoload_priority = 30, \
	m_autoload_script   = configfs \
}"
