# This file Copyright (c) 2019 Anton Kikin <a.kikin@tano-systems.com>

require hostapd.inc

SUMMARY = "User space daemon for extended IEEE 802.11 management"
DEPENDS = "libnl openssl libubus libubox"
SECTION = "kernel/userland"

PR = "${INC_PR}.2"

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
	install -d ${D}${sysconfdir}/hotplug.d/ieee80211

	# wpad
	install -m 0755 ${B}/wpad ${D}${sbindir}/wpad
	ln -s wpad ${D}${sbindir}/hostapd
	ln -s wpad ${D}${sbindir}/wpa_supplicant

	# hostapd-common
	install -m 0644 ${WORKDIR}/hostapd.sh ${D}${base_libdir}/netifd/hostapd.sh
	install -m 0755 ${WORKDIR}/wps-hotplug.sh ${D}${sysconfdir}/rc.button/wps
	install -m 0755 ${WORKDIR}/hostapd.hotplug ${D}${sysconfdir}/hotplug.d/ieee80211/20-hostapd
}

FILES_${PN} += "${base_libdir}"
