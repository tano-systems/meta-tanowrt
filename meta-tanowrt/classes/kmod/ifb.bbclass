#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2018, 2020 Tano Systems LLC
# Author: Anton Kikin <a.kikin@tano-systems.com>
#
# Intermediate Functional Block support
#

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
