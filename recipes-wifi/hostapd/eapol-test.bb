# This file Copyright (c) 2019 Anton Kikin <a.kikin@tano-systems.com>

require hostapd.inc

SUMMARY = "802.1x authentication test utility"
DEPENDS = "libnl openssl libubus libubox"
RDEPENDS_${PN} += "kmod-cfg80211"

PR = "${INC_PR}.0"

do_compile() {
	oe_runmake eapol_test -C ${B}/wpa_supplicant CONFIG_EAPOL_TEST=y
}

do_install() {
	install -d ${D}${sbindir}
	install -m 0755 ${B}/wpa_supplicant/eapol_test ${D}${sbindir}/eapol_test
}
