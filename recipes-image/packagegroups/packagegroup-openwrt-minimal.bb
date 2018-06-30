# Copyright (C) 2018 Daniel Dickinson <cshored@thecshore.com>
# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>
# Released under the MIT license (see COPYING.MIT for the terms)

PR = "tano0"
SUMMARY = "Minimal complete OpenWRT system requirements"
DESCRIPTION = "The set of packages required for core Openwrt system with network but no gui"
LICENSE = "MIT"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup openwrt

PACKAGES = "\
	packagegroup-openwrt-minimal \
	packagegroup-openwrt-minimal-base \
	packagegroup-openwrt-minimal-network \
"

RDEPENDS_${PN} = "\
	packagegroup-openwrt-minimal-base \
	packagegroup-openwrt-minimal-network \
"

RDEPENDS_${PN}-base = "\
	packagegroup-core-boot \
	rpcd \
	ubox \
	ubus \
	uci \
"

RDEPENDS_${PN}-network = "\
	dnsmasq \
	firewall3 \
	iptables \
	${VIRTUAL-RUNTIME_network_manager} \
	uclient \
	ustream-ssl \
	${@bb.utils.contains('COMBINED_FEATURES', 'wifi', 'iw', '',d)} \
	${@bb.utils.contains('COMBINED_FEATURES', 'wifi', 'hostapd', '',d)} \
	${@bb.utils.contains('DISTRO_FEATURES', 'ipv6', 'odhcp6c', '', d)} \
"
