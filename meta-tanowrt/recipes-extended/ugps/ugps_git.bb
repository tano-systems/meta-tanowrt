#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2016 Khem Raj <raj.khem@gmail.com>
# Copyright (C) 2018-2023 Anton Kikin <a.kikin@tano-systems.com>
#
PR = "tano10"

DESCRIPTION = "OpenWrt GPS daemon"
HOMEPAGE = "http://git.openwrt.org/?p=project/ugps.git;a=summary"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://main.c;beginline=1;endline=17;md5=2bf63b09608cf97d9dbafe99c7ea23fe"
SECTION = "base"
DEPENDS = "libubox ubus"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI = "\
	git://${GIT_OPENWRT_ORG}/project/ugps.git;branch=master \
	file://gps.config \
	file://ugps.init \
"

# 27.01.2023
# main.c: -S does not take any options
SRCREV = "a8171a07193351a9125024d5c759c0ebd6a6895c"

S = "${WORKDIR}/git"

inherit cmake pkgconfig

do_install:append() {
	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/ugps.init ${D}${sysconfdir}/init.d/ugps

	install -d ${D}${sysconfdir}/config
	install -m 0644 ${WORKDIR}/gps.config ${D}${sysconfdir}/config/gps
}

FILES:${PN} += "${libdir}/*"

CONFFILES:${PN}:append = "\
	${sysconfdir}/config/gps \
"
