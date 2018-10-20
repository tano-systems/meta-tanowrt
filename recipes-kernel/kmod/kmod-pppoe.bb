# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>

PR = "tano0"
SUMMARY = "PPPoE support"
LICENSE = "MIT"

inherit kernel-config-depends

RDEPENDS_${PN} += "\
	kmod-ppp \
	kmod-pppox \
"

#
# kmod-pppoe
# ############
#
KERNEL_CONFIG_DEPENDS += "{\
	option              = CONFIG_PPPOE, \
	required            = y|m, \
	m_rdepends          = kernel-module-pppoe, \
	m_autoload          = pppoe, \
	m_autoload_script   = pppoe \
}"
