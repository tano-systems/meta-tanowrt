# Copyright (C) 2019 Anton Kikin <a.kikin@tano-systems.com>

PR = "tano2"
SUMMARY = "WiFi support packages"
DESCRIPTION = "The set of packages required for a WiFi support"
LICENSE = "MIT"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup openwrt

PACKAGES = "\
	${PN} \
	${PN}-firmware \
"

# packagegroup-openwrt-wifi
RDEPENDS_${PN} = "\
	${PN}-firmware \
	iwinfo \
	rpcd-mod-iwinfo \
	cfg80211 \
	iw \
	hostapd \
"

# packagegroup-openwrt-wifi-firmware
RDEPENDS_${PN}-firmware = "\
	linux-firmware-rtl8192ce \
	linux-firmware-rtl8192cu \
	linux-firmware-rtl8192su \
	linux-firmware-ralink \
	linux-firmware-mt7601u \
	linux-firmware-carl9170 \
	linux-firmware-ath9k \
	linux-firmware-wl18xx \
"
