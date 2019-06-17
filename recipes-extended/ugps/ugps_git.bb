# Copyright (C) 2016 Khem Raj <raj.khem@gmail.com>
# Copyright (C) 2018-2019 Anton Kikin <a.kikin@tano-systems.com>
# Released under the MIT license (see COPYING.MIT for the terms)

PR = "tano2"

DESCRIPTION = "OpenWrt GPS daemon"
HOMEPAGE = "http://git.openwrt.org/?p=project/ugps.git;a=summary"
LICENSE = "GPL-2.0+"
LIC_FILES_CHKSUM = "file://main.c;beginline=1;endline=17;md5=2bf63b09608cf97d9dbafe99c7ea23fe"
SECTION = "base"
DEPENDS = "libubox ubus"

SRC_URI = "\
	git://${GIT_OPENWRT_ORG_URL}/project/ugps.git \
"

# 12.07.2018
# check timegm return code
SRCREV = "b88037b6bf6a6c812183566d96887cb0b201491b"

S = "${WORKDIR}/git"

inherit cmake pkgconfig openwrt

FILES_${PN}  += "${libdir}/*"
