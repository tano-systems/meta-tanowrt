# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>

PR = "tano0"
SUMMARY = "PPtP support"
LICENSE = "MIT"

inherit kernel-kmod

RDEPENDS_${PN} += "\
	kmod-ppp \
	kmod-gre \
	kmod-pppox \
"

#
# kmod-pptp
# ############
#
KERNEL_CONFIG_DEPENDS += "{\
	option              = CONFIG_PPTP, \
	required            = y|m, \
	m_rdepends          = kernel-module-pptp, \
	m_autoload          = pptp, \
	m_autoload_script   = pptp \
}"
