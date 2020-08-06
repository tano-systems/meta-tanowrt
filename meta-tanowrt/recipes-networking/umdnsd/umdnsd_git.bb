# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Copyright (C) 2018-2019 Anton Kikin <a.kikin@tano-systems.com>
# Released under the MIT license (see COPYING.MIT for the terms)

PR = "tano4"

DESCRIPTION = "OpenWrt MDNS daemon"
HOMEPAGE = "http://git.openwrt.org/?p=project/mdnsd.git;a=summary"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://main.c;beginline=1;endline=12;md5=ce0be9da20a926574bf76c1285343bef"
SECTION = "base"
DEPENDS = "json-c libubox ubus"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI = "\
	git://${GIT_OPENWRT_ORG}/project/mdnsd.git \
	file://0001-Fix-compile-warnings-treated-as-errors.patch \
	file://umdns.init \
	file://umdns.config \
"

# 02.01.2018
# umdnsd: Replace strerror(errno) with %m.
SRCREV = "78974417e182a3de8f78b7d73366ec0c98396b6c"

S = "${WORKDIR}/git"

inherit cmake pkgconfig

inherit tanowrt-services

TANOWRT_SERVICE_PACKAGES = "umdnsd"
TANOWRT_SERVICE_SCRIPTS_umdnsd += "umdns"
TANOWRT_SERVICE_STATE_umdnsd-umdns ?= "enabled"

FILES_${PN}  += "${libdir}/*"

do_install_append() {
	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/umdns.init ${D}${sysconfdir}/init.d/umdns

	install -d ${D}${sysconfdir}/config
	install -m 0755 ${WORKDIR}/umdns.config ${D}${sysconfdir}/config/umdns
}
