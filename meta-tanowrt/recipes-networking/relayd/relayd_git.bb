# Copyright (C) 2016 Khem Raj <raj.khem@gmail.com>
# Copyright (C) 2018-2019 Anton Kikin <a.kikin@tano-systems.com>
# Released under the MIT license (see COPYING.MIT for the terms)

PR = "tano2"

DESCRIPTION = "OpenWrt IPv4 pseudo-bridge routing daemon"
HOMEPAGE = "http://git.openwrt.org/?p=project/relayd.git;a=summary"
LICENSE = "GPL-2.0+"
LIC_FILES_CHKSUM = "file://main.c;beginline=1;endline=17;md5=86aad799085683e0a2e1c2684a20bab2"
SECTION = "base"
DEPENDS = "libubox"

SRC_URI = "git://${GIT_OPENWRT_ORG}/project/relayd.git \
          "

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRCREV = "ad0b25ad74345d367c62311e14b279f5ccb8ef13"

S = "${WORKDIR}/git"

inherit cmake pkgconfig tanowrt

FILES_${PN}  += "${libdir}/*"

SRC_URI += "\
	file://relayd.init \
"

inherit tanowrt-services

TANOWRT_SERVICE_PACKAGES = "relayd"
TANOWRT_SERVICE_SCRIPTS_relayd += "relayd"
TANOWRT_SERVICE_STATE_relayd-relayd ?= "enabled"

do_install_append() {
	install -dm 0755 ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/relayd.init ${D}${sysconfdir}/init.d/relayd
}
