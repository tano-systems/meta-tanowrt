#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Copyright (C) 2018-2021 Anton Kikin <a.kikin@tano-systems.com>
#

PR = "tano5"

DESCRIPTION = "OpenWrt MDNS daemon"
HOMEPAGE = "http://git.openwrt.org/?p=project/mdnsd.git;a=summary"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://main.c;beginline=1;endline=12;md5=ce0be9da20a926574bf76c1285343bef"
SECTION = "base"
DEPENDS = "json-c libubox ubus"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI = "\
	git://${GIT_OPENWRT_ORG}/project/mdnsd.git;branch=master \
	file://0001-Fix-compile-warnings-treated-as-errors.patch \
	file://umdns.init \
	file://umdns.config \
"

# 13.05.2021
# service: fix compilation with GCC 10
SRCREV = "b777a0b53f7d89ab2a60e3eed7d98036806da9a4"

S = "${WORKDIR}/git"

inherit cmake pkgconfig

inherit tanowrt-services

TANOWRT_SERVICE_PACKAGES = "umdnsd"
TANOWRT_SERVICE_SCRIPTS_umdnsd += "umdns"
TANOWRT_SERVICE_STATE_umdnsd-umdns ?= "enabled"

FILES:${PN}  += "${libdir}/*"

do_install:append() {
	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/umdns.init ${D}${sysconfdir}/init.d/umdns

	install -d ${D}${sysconfdir}/config
	install -m 0755 ${WORKDIR}/umdns.config ${D}${sysconfdir}/config/umdns
}
