# Copyright (C) 2018, 2020 Tano Systems LLC
# Author: Anton Kikin <a.kikin@tano-systems.com>
#
# Kernel modules for Microsoft PPP compression/encryption

inherit kernel-kmod

inherit kmod/ppp
inherit kmod/crypto-sha1
inherit kmod/crypto-ecb

#
# kmod-mppe
# ###############
#
KERNEL_CONFIG_DEPENDS += "{\
	option              = CONFIG_PPP_MPPE, \
	required            = y|m, \
	m_rdepends          = kernel-module-ppp-mppe, \
	m_autoload          = ppp_mppe, \
	m_autoload_script   = mppe \
}"
