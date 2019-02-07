# Copyright (C) 2019 Anton Kikin <a.kikin@tano-systems.com>

PR = "tano0"
SUMMARY = "Linux wireless LAN (802.11) configuration API"
LICENSE = "MIT"

inherit kernel-config-depends

RDEPENDS_${PN} += "\
	kmod-mac80211 \
"

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
