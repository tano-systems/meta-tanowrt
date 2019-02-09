# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>

PR = "tano7"
SUMMARY = "Normal Openwrt system requirements without web-interface"
DESCRIPTION = "The set of packages required for a more traditional full-featured Openwrt system without web-interface"
LICENSE = "MIT"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup openwrt

PACKAGES = "\
	packagegroup-openwrt-noweb-base \
	packagegroup-openwrt-noweb-base-network \
"

# packagegroup-openwrt-noweb-base
RDEPENDS_${PN} = "\
	packagegroup-openwrt-minimal \
	packagegroup-openwrt-noweb-base-network \
	make-ext4fs \
	tzdata-europe \
	tzdata-asia \
"

# packagegroup-openwrt-noweb-base-network
RDEPENDS_${PN}-network = "\
	dnsmasq \
	${@bb.utils.contains('DISTRO_FEATURES', 'ipv6', 'odhcp6c', '', d)} \
	odhcpd \
	umdnsd \
"
