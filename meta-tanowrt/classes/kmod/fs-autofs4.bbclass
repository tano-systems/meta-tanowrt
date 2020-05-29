# Copyright (C) 2018, 2020 Tano Systems LLC
# Author: Anton Kikin <a.kikin@tano-systems.com>

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
