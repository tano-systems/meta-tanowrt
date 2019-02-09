# Copyright (C) 2019 Anton Kikin <a.kikin@tano-systems.com>

PR = "tano0"
SUMMARY = "WiFi support packages"
DESCRIPTION = "The set of packages required for a WiFi support"
LICENSE = "MIT"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup openwrt

# packagegroup-openwrt-cgroup
RDEPENDS_${PN} = "\
	iwinfo \
	cfg80211 \
	iw \
	hostapd \
"
