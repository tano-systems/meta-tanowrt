#
# SPDX-License-Identifier: MIT
#
# This file Copyright (C) 2019-2022 Tano Systems LLC
# Author: Anton Kikin <a.kikin@tano-systems.com>
#
# Wireless configuration API
#
PR = "tano14"

DESCRIPTION = "cfg80211 is the Linux wireless LAN (802.11) configuration API"
SUMMARY = "Wireless configuration API"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=801f80980d171dd6425610833a22dbe6"
SECTION = "net"

RDEPENDS:${PN} += "iw wireless-regdb-static"
inherit kmod/cfg80211

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI = "\
	file://files/mac80211.hotplug \
	file://files/lib/netifd/wireless/mac80211.sh \
	file://files/lib/netifd/mac80211.sh \
	file://files/lib/wifi/mac80211.sh \
	file://LICENSE \
"

FILES:${PN} = "${sysconfdir}/hotplug.d/ieee80211 ${nonarch_base_libdir}/"

S = "${WORKDIR}"

do_compile[noexec] = "1"
do_configure[noexec] = "1"

do_install() {
	install -dm 0755 ${D}${sysconfdir}/hotplug.d/ieee80211
	install -m 0644 ${WORKDIR}/files/mac80211.hotplug ${D}${sysconfdir}/hotplug.d/ieee80211/10-wifi-detect

	install -dm 0755 ${D}${nonarch_base_libdir}/wifi
	install -m 0644 ${WORKDIR}/files/lib/wifi/mac80211.sh ${D}${nonarch_base_libdir}/wifi/mac80211.sh

	install -dm 0755 ${D}${nonarch_base_libdir}/netifd/wireless
	install -m 0755 ${WORKDIR}/files/lib/netifd/wireless/mac80211.sh ${D}${nonarch_base_libdir}/netifd/wireless/mac80211.sh
	install -m 0755 ${WORKDIR}/files/lib/netifd/mac80211.sh ${D}${nonarch_base_libdir}/netifd/mac80211.sh
}
