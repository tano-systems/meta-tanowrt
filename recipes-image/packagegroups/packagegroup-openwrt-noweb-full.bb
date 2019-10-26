# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>

PR = "tano7"
SUMMARY = "Extras Openwrt system requirements without web-interface"
LICENSE = "MIT"

inherit packagegroup openwrt

PACKAGES = "\
	packagegroup-openwrt-noweb-full \
	packagegroup-openwrt-noweb-full-base \
	packagegroup-openwrt-noweb-full-network \
	packagegroup-openwrt-noweb-full-console \
"

# packagegroup-openwrt-noweb-full
RDEPENDS_${PN} = "\
	packagegroup-openwrt-noweb-full-base \
	packagegroup-openwrt-noweb-full-network \
	packagegroup-openwrt-noweb-full-console \
"

# packagegroup-openwrt-noweb-full-base
RDEPENDS_${PN}-base = "\
	packagegroup-openwrt-noweb-base \
	libubox-lua \
	fstools \
	eudev \
	${@bb.utils.contains('COMBINED_FEATURES', 'usbhost', 'libusb1', '',d)} \
	${@bb.utils.contains('COMBINED_FEATURES', 'usbhost', 'usbutils', '',d)} \
	${@bb.utils.contains('COMBINED_FEATURES', 'usbhost', 'usbreset', '',d)} \
	${@bb.utils.contains('COMBINED_FEATURES', 'usbhost', 'usbmode', '',d)} \
	mtd-utils \
	mtd-utils-ubifs \
	apcupsd \
	cpulimit \
	ugps \
	rpcd-mod-file \
	schedtool-dl \
	nano \
"

# packagegroup-openwrt-noweb-full-console
RDEPENDS_${PN}-console = "\
	ncurses \
	ncurses-terminfo \
	htop \
"

# packagegroup-openwrt-noweb-full-network
RDEPENDS_${PN}-network = "\
	tcpdump \
	umbim \
	umdnsd \
	ethtool \
	curl \
	drill \
	dropbear \
	openssh-sftp-server \
	net-snmp-client \
	net-snmp-mibs \
	net-snmp-server-snmpd \
	net-snmp-server-snmptrapd \
	openvpn \
	openvpn-easy-rsa \
	lldpd \
	vsftpd \
	mstpd \
	ipset \
	wireguard-module \
	wireguard-tools \
"
