# Copyright (C) 2018-2020 Tano Systems LLC
# Author: Anton Kikin <a.kikin@tano-systems.com>

inherit kernel-kmod

inherit kmod/ipt-core
inherit kmod/br-netfilter

#
# kmod-ipt-physdev
# ################
#
KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_NETFILTER_XT_MATCH_PHYSDEV, \
	required          = y|m, \
	m_rdepends        = kernel-module-xt-physdev, \
	m_autoload        = xt_physdev, \
	m_autoload_script = ipt-physdev \
}"

