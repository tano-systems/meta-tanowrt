# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>

PR = "tano0"
SUMMARY = "PPPoA support"
LICENSE = "MIT"

inherit kernel-config-depends

RDEPENDS_${PN} += "\
	kmod-ppp \
	kmod-atm \
"

#
# kmod-pppoa
# ############
#
KERNEL_CONFIG_DEPENDS += "{\
	option              = CONFIG_PPPOATM, \
	required            = y|m, \
	m_rdepends          = kernel-module-pppoatm, \
	m_autoload_priority = 40, \
	m_autoload          = pppoatm, \
	m_autoload_script   = pppoa \
}"

KERNEL_CONFIG_DEPENDS += "{\
	option              = CONFIG_ATM_DRIVERS, \
	required            = y \
}"
