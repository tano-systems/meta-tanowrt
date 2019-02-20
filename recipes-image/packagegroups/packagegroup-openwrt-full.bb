# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Copyright (C) 2018 Daniel Dickinson <cshored@thecshore.com>
# Copyright (C) 2018-2019 Anton Kikin <a.kikin@tano-systems.com>

# Released under the MIT license (see COPYING.MIT for the terms)

PR = "tano14"
SUMMARY = "Extras Openwrt system requirements"
LICENSE = "MIT"

inherit packagegroup openwrt

PACKAGES = "\
	packagegroup-openwrt-full \
	packagegroup-openwrt-full-luci \
"

# packagegroup-openwrt-full
RDEPENDS_${PN} = "\
	packagegroup-openwrt-noweb-full \
	packagegroup-openwrt-full-luci \
"

#luci-app-commands
#luci-app-qos
#luci-app-mwan3
#luci-app-nlbwmon
#luci-app-ttyd

# packagegroup-openwrt-full-luci
RDEPENDS_${PN}-luci = "\
	luci-app-lldpd \
	luci-app-uhttpd \
	luci-app-openvpn \
	luci-app-statistics \
	luci-app-snmpd-tn \
	luci-app-mstpd \
	luci-app-netports \
	luci-app-netports-hotplug \
	luci-app-ddns \
	luci-app-vsftpd \
	luci-app-shellinabox \
	luci-app-watchdog \
	luci-proto-3g \
	luci-proto-ppp \
	luci-proto-ncm \
	luci-proto-qmi \
"
