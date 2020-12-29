#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2019, 2020 Tano Systems LLC
# Author: Anton Kikin <a.kikin@tano-systems.com>
#

inherit kernel-kmod
inherit kmod/mac80211

#
# kmod-cfg80211
# ##############
#
KERNEL_CONFIG_DEPENDS += "{\
	option              = CONFIG_WLAN, \
	required            = y \
}"

KERNEL_CONFIG_DEPENDS += "{\
	option              = CONFIG_CFG80211, \
	required            = y|m, \
	m_rdepends          = kernel-module-cfg80211, \
	m_autoload          = cfg80211, \
	m_autoload_script   = cfg80211 \
}"
