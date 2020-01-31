# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Copyright (C) 2018 Daniel Dickinson <cshored@thecshore.com>
# Copyright (C) 2018-2020 Anton Kikin <a.kikin@tano-systems.com>

# Released under the MIT license (see COPYING.MIT for the terms)

PR = "tano19"
SUMMARY = "Extras TanoWrt system requirements"
LICENSE = "MIT"

inherit packagegroup openwrt

PACKAGES = "\
	packagegroup-tanowrt-full \
	packagegroup-tanowrt-full-luci \
"

# packagegroup-tanowrt-full
RDEPENDS_${PN} = "\
	packagegroup-tanowrt-noweb-full \
	packagegroup-tanowrt-full-luci \
"

#luci-app-commands
#luci-app-qos
#luci-app-mwan3
#luci-app-nlbwmon
#luci-app-tn-ttyd

# packagegroup-tanowrt-full-luci
RDEPENDS_${PN}-luci = "\
	luci-app-tn-lldpd \
	luci-app-uhttpd \
	luci-app-openvpn \
	luci-app-statistics \
	luci-app-tn-snmpd \
	luci-app-tn-mstpd \
	luci-app-tn-netports \
	luci-app-tn-netports-hotplug \
	luci-app-ddns \
	luci-app-tn-vsftpd \
	luci-app-tn-shellinabox \
	${@bb.utils.contains('COMBINED_FEATURES', 'watchdog', 'luci-app-tn-watchdog', '', d)} \
	luci-proto-3g \
	luci-proto-ppp \
	luci-proto-ncm \
	luci-proto-qmi \
	luci-proto-wireguard \
"
