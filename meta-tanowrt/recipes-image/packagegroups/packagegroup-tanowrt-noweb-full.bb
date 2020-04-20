# Copyright (C) 2018-2020 Anton Kikin <a.kikin@tano-systems.com>

PR = "tano9"
SUMMARY = "Extras Openwrt system requirements without web-interface"
LICENSE = "MIT"

inherit packagegroup openwrt

PACKAGES = "\
	packagegroup-tanowrt-noweb-full \
	packagegroup-tanowrt-noweb-full-base \
	packagegroup-tanowrt-noweb-full-network \
	packagegroup-tanowrt-noweb-full-console \
"

# packagegroup-tanowrt-noweb-full
RDEPENDS_${PN} = "\
	packagegroup-tanowrt-noweb-full-base \
	packagegroup-tanowrt-noweb-full-network \
	packagegroup-tanowrt-noweb-full-console \
"

# packagegroup-tanowrt-noweb-full-base
RDEPENDS_${PN}-base = "\
	packagegroup-tanowrt-noweb-base \
	libubox-lua \
	fstools \
	eudev \
	${@bb.utils.contains('COMBINED_FEATURES', 'usbhost', 'libusb1', '',d)} \
	${@bb.utils.contains('COMBINED_FEATURES', 'usbhost', 'usbutils', '',d)} \
	${@bb.utils.contains('COMBINED_FEATURES', 'usbhost', 'usbreset', '',d)} \
	${@bb.utils.contains('COMBINED_FEATURES', 'usbhost', 'usbmode', '',d)} \
	mtd-utils \
	mtd-utils-ubifs \
	cpulimit \
	rpcd-mod-file \
	schedtool-dl \
	nano \
"

# packagegroup-tanowrt-noweb-full-console
RDEPENDS_${PN}-console = "\
	ncurses \
	ncurses-terminfo \
	htop \
"

# packagegroup-tanowrt-noweb-full-network
RDEPENDS_${PN}-network = "\
	tcpdump \
	umbim \
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
