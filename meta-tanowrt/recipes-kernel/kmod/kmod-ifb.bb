# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>
# Intermediate Functional Block support

PR = "tano1"
SUMMARY = "Intermediate Functional Block support"
LICENSE = "MIT"

inherit kernel-kmod

#
# kmod-ifb
# ############
#
KERNEL_CONFIG_DEPENDS += "{\
	option              = CONFIG_IFB, \
	required            = y|m, \
	m_rdepends          = kernel-module-ifb \
}"

KERNEL_CONFIG_DEPENDS += "{\
	option              = CONFIG_NET_CLS, \
	required            = y \
}"
