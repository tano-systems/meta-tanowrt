# Copyright (C) 2018-2020 Anton Kikin <a.kikin@tano-systems.com>

PR = "tano8"
SUMMARY = "Normal TanoWrt system requirements without web-interface"
DESCRIPTION = "The set of packages required for a more traditional full-featured Openwrt system without web-interface"
LICENSE = "MIT"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup tanowrt

PACKAGES = "\
	packagegroup-tanowrt-noweb-base \
	packagegroup-tanowrt-noweb-base-network \
"

# packagegroup-tanowrt-noweb-base
RDEPENDS_${PN} = "\
	packagegroup-tanowrt-minimal \
	packagegroup-tanowrt-noweb-base-network \
	make-ext4fs \
	tzdata-europe \
	tzdata-asia \
"

# packagegroup-tanowrt-noweb-base-network
RDEPENDS_${PN}-network = "\
	dnsmasq \
	${@bb.utils.contains('DISTRO_FEATURES', 'ipv6', 'odhcp6c', '', d)} \
	odhcpd \
"
