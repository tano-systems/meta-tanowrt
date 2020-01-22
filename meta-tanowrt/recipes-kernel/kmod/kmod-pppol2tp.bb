# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>

PR = "tano0"
SUMMARY = "PPPoL2TP support"
LICENSE = "MIT"

inherit kernel-kmod

RDEPENDS_${PN} += "\
	kmod-ppp \
	kmod-pppox \
	kmod-l2tp \
"

#
# kmod-pppol2tp
# ###############
#
KERNEL_CONFIG_DEPENDS += "{\
	option              = CONFIG_PPPOL2TP, \
	required            = y|m, \
	m_rdepends          = kernel-module-l2tp-ppp, \
	m_autoload          = l2tp_ppp, \
	m_autoload_script   = pppol2tp \
}"
