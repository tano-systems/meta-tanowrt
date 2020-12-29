#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2019, 2020 Tano Systems LLC
# Author: Anton Kikin <a.kikin@tano-systems.com>
#

inherit kernel-kmod

#
# kmod-mac80211
# ##############
#
KERNEL_CONFIG_DEPENDS += "{\
	option              = CONFIG_WLAN, \
	required            = y \
}"

KERNEL_CONFIG_DEPENDS += "{\
	option              = CONFIG_MAC80211, \
	required            = y|m, \
	m_rdepends          = kernel-module-mac80211, \
	m_autoload          = mac80211, \
	m_autoload_script   = mac80211 \
}"
