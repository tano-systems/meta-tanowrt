#
# SPDX-License-Identifier: MIT
# This file Copyright (c) 2019-2021 Anton Kikin <a.kikin@tano-systems.com>
#

require hostapd.inc

SUMMARY = "User space daemon for extended IEEE 802.11 management"
DEPENDS = "libnl openssl libubus libubox"
SECTION = "kernel/userland"

PR = "${INC_PR}.5"

PROVIDES += "wpa-supplicant wpad"
RPROVIDES_${PN} += "wpa-supplicant wpad"
RREPLACES_${PN}  += "wpa-supplicant"
RCONFLICTS_${PN}  += "wpa-supplicant"
inherit kmod/cfg80211

do_compile() {
	oe_runmake hostapd_cli hostapd_multi.a -C ${B}/hostapd MULTICALL=1
	oe_runmake wpa_cli wpa_supplicant_multi.a -C ${B}/wpa_supplicant MULTICALL=1

	${CC} ${TARGET_CFLAGS} -o ${B}/wpad \
		${WORKDIR}/multicall.c \
		${B}/hostapd/hostapd_multi.a \
		${B}/wpa_supplicant/wpa_supplicant_multi.a \
		${TARGET_LDFLAGS}
}

do_install() {
	install -d ${D}${sbindir}
	install -d ${D}${base_libdir}/netifd
	install -d ${D}${sysconfdir}/rc.button
	install -d ${D}${sysconfdir}/init.d
	install -d ${D}${sysconfdir}/hotplug.d/ieee80211
	install -d ${D}${sysconfdir}/capabilities
	install -d ${D}${datadir}/acl.d

	# wpad
	install -m 0755 ${B}/wpad ${D}${sbindir}/wpad
	ln -s wpad ${D}${sbindir}/hostapd
	ln -s wpad ${D}${sbindir}/wpa_supplicant
	install -m 0755 ${WORKDIR}/wpad.init ${D}${sysconfdir}/init.d/wpad

	# hostapd-common
	install -m 0644 ${WORKDIR}/hostapd.sh ${D}${base_libdir}/netifd/hostapd.sh
	install -m 0755 ${WORKDIR}/wps-hotplug.sh ${D}${sysconfdir}/rc.button/wps

	install -m 0644 ${WORKDIR}/wpad.json ${D}${sysconfdir}/capabilities/
	install -m 0644 ${WORKDIR}/wpad_acl.json ${D}${datadir}/acl.d/
}

FILES_${PN} += "${base_libdir} ${datadir}/acl.d"
