# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>

PR = "tano0"
SUMMARY = "PPPoX helper"
LICENSE = "MIT"

inherit kernel-kmod

RDEPENDS_${PN} += "\
	kmod-ppp \
"

#
# kmod-pppox
# ############
#
KERNEL_CONFIG_DEPENDS += "{\
	option              = CONFIG_PPPOE, \
	required            = y|m, \
	m_rdepends          = kernel-module-pppox, \
	m_autoload          = pppox, \
	m_autoload_script   = pppoe \
}"
