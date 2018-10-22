# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Copyright (C) 2018 Daniel Dickinson <cshored@thecshore.com>
# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>

# Released under the MIT license (see COPYING.MIT for the terms)

PR = "tano7"
SUMMARY = "Extras Openwrt system requirements"
LICENSE = "MIT"

inherit packagegroup openwrt

PACKAGES = "\
	packagegroup-openwrt-full \
	packagegroup-openwrt-full-base \
	packagegroup-openwrt-full-network \
	packagegroup-openwrt-full-luci \
	packagegroup-openwrt-full-console \
"

RDEPENDS_${PN} = "\
	packagegroup-openwrt-full-base \
	packagegroup-openwrt-full-network \
	packagegroup-openwrt-full-luci \
	packagegroup-openwrt-full-console \
"

RDEPENDS_${PN}-base = "\
	packagegroup-openwrt-base \
	libubox-lua \
	fstools \
	eudev \
	${@bb.utils.contains('DISTRO_FEATURES', 'usbhost', 'libusb1', '',d)} \
	${@bb.utils.contains('DISTRO_FEATURES', 'usbhost', 'usbutils', '',d)} \
	${@bb.utils.contains('DISTRO_FEATURES', 'usbhost', 'usbreset', '',d)} \
	mtd-utils \
	mtd-utils-ubifs \
	apcupsd \
	cpulimit \
"

#luci-app-commands
#luci-app-terminal
#luci-app-qos
#luci-app-mwan3
#luci-app-qos
#luci-app-mwan3
#luci-app-nlbwmon

RDEPENDS_${PN}-luci = "\
	luci-app-lldpd \
	luci-app-uhttpd \
	luci-app-openvpn \
	luci-app-statistics \
	luci-app-snmpd \
	luci-app-mstpd \
	luci-app-ddns \
	luci-app-vsftpd \
	luci-proto-3g \
	luci-proto-ppp \
	luci-proto-ncm \
	luci-proto-qmi \
"

RDEPENDS_${PN}-console = "\
	ncurses \
	ncurses-terminfo \
"

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
"
