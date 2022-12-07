#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Copyright (C) 2018-2021 Anton Kikin <a.kikin@tano-systems.com>
#
require recipes-image/images/tanowrt-image-base.bb

PR:append = ".6"
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
	${@bb.utils.filter('MACHINE_FEATURES', 'crypto wifi watchdog cgroup stp lldp swupdate', d)} \
"

CORE_IMAGE_EXTRA_INSTALL += "\
	ncurses \
	ncurses-terminfo \
	htop \
"
