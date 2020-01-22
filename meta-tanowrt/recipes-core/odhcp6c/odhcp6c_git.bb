# Copyright (C) 2018 Daniel Dickinson <cshored@thecshore.com>
# Copyright (C) 2018-2019 Anton Kikin <a.kikin@tano-systems.com>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "OpenWrt DHCPv6 client"
HOMEPAGE = "http://git.openwrt.org/?p=project/odhcp63.git;a=summary"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://src/odhcp6c.c;beginline=1;endline=13;md5=41d01a2c8e6a8ef58b8e5f18e68118a8"
SECTION = "base"
DEPENDS = "libubox"
PR = "tano3"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

# 13.07.2018
# odhcp6c: add option to ignore Server Unicast option
SRCREV = "67ae6a71b5762292e114b281d0e329cc24209ae6"

SRC_URI = "\
	git://${GIT_OPENWRT_ORG}/project/odhcp6c.git;name=odhcp6c \
	file://dhcpv6.script \
	file://dhcpv6.sh \
"

S = "${WORKDIR}/git"

inherit cmake pkgconfig openwrt

SRCREV_openwrt = "${OPENWRT_SRCREV}"

do_install_append() {
	install -Dm 0755 ${WORKDIR}/dhcpv6.sh ${D}${base_libdir}/netifd/proto/dhcpv6.sh
	install -Dm 0755 ${WORKDIR}/dhcpv6.script ${D}${base_libdir}/netifd/dhcpv6.script
}

FILES_${PN} += "\
	${base_libdir}/netifd/proto/dhcpv6.sh \
	${base_libdir}/netifd/dhcpv6.script \
"

RRECOMMENDS_${PN} += "netifd"
