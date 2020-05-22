# Copyright (C) 2018 Daniel Dickinson <cshored@thecshore.com>
# Copyright (C) 2018-2020 Anton Kikin <a.kikin@tano-systems.com>
# Released under the MIT license (see COPYING.MIT for the terms)

PR = "tano3"
SUMMARY = "Minimal complete TanoWrt system requirements"
DESCRIPTION = "The set of packages required for core Openwrt system with network but no gui"
LICENSE = "MIT"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup tanowrt

PACKAGES = "\
	packagegroup-tanowrt-minimal \
	packagegroup-tanowrt-minimal-base \
	packagegroup-tanowrt-minimal-network \
"

RDEPENDS_${PN} = "\
	packagegroup-tanowrt-minimal-base \
	packagegroup-tanowrt-minimal-network \
"

RDEPENDS_${PN}-base = "\
	packagegroup-core-boot \
	urandom-seed \
	rng-tools \
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
	${@bb.utils.contains('DISTRO_FEATURES', 'ipv6', 'odhcp6c', '', d)} \
"
