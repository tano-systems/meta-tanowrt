# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>

PR = "tano0"
SUMMARY = "PPP modules"
LICENSE = "MIT"

inherit kernel-config-depends

RDEPENDS_${PN} += "\
	kmod-slhc \
	kmod-lib-crc-ccitt \
"

#
# kmod-ppp
# ############
#
KERNEL_CONFIG_DEPENDS += "{\
	option              = CONFIG_PPP, \
	required            = y|m, \
	m_rdepends          = kernel-module-ppp-generic, \
	m_autoload          = ppp-generic, \
	m_autoload_script   = ppp \
}"

KERNEL_CONFIG_DEPENDS += "{\
	option              = CONFIG_PPP_ASYNC, \
	required            = y|m, \
	m_rdepends          = kernel-module-ppp-async, \
	m_autoload          = ppp-async, \
	m_autoload_script   = ppp \
}"
