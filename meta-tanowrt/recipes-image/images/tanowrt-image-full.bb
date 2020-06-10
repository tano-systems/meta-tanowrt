# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Copyright (C) 2018-2020 Anton Kikin <a.kikin@tano-systems.com>
# Released under the MIT license (see COPYING.MIT for the terms)

require recipes-image/images/tanowrt-image-base.bb

PR_append = ".4"
SUMMARY = "TanoWrt full featured image"
PACKAGE_ARCH = "${MACHINE_ARCH}"

IMAGE_FEATURES += "\
	perftools \
	mbusgw \
	snmp \
	ddns \
	ftp \
	openvpn \
	modem \
	mqtt \
	statistics \
	webterminal \
	wireguard \
	${@bb.utils.filter('MACHINE_FEATURES', 'wifi watchdog cgroup stp lldp', d)} \
"

CORE_IMAGE_EXTRA_INSTALL += "\
	ncurses \
	ncurses-terminfo \
	htop \
"
