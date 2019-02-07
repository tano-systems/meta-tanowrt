# Copyright (C) 2019 Anton Kikin <a.kikin@tano-systems.com>

PR = "tano0"
SUMMARY = "Linux 802.11 Wireless Networking Stack"
LICENSE = "MIT"

inherit kernel-config-depends

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
