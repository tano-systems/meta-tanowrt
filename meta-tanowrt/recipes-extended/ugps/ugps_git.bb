#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2016 Khem Raj <raj.khem@gmail.com>
# Copyright (C) 2018-2021 Anton Kikin <a.kikin@tano-systems.com>
#
PR = "tano9"

DESCRIPTION = "OpenWrt GPS daemon"
HOMEPAGE = "http://git.openwrt.org/?p=project/ugps.git;a=summary"
LICENSE = "GPL-2.0+"
LIC_FILES_CHKSUM = "file://main.c;beginline=1;endline=17;md5=2bf63b09608cf97d9dbafe99c7ea23fe"
SECTION = "base"
DEPENDS = "libubox ubus"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI = "\
	git://${GIT_OPENWRT_ORG}/project/ugps.git \
	file://gps.config \
	file://ugps.init \
"

# 19.06.2021
# add support for multi GNSS solutions
SRCREV = "350b960b6f8b08409dcef719ef1e1e0c8911040f"

S = "${WORKDIR}/git"

inherit cmake pkgconfig

do_install_append() {
	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/ugps.init ${D}${sysconfdir}/init.d/ugps

	install -d ${D}${sysconfdir}/config
	install -m 0644 ${WORKDIR}/gps.config ${D}${sysconfdir}/config/gps
}

FILES_${PN} += "${libdir}/*"

CONFFILES_${PN}_append = "\
	${sysconfdir}/config/gps \
"
