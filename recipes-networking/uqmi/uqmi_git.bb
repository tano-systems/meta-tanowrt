# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Copyright (C) 2018-2019 Anton Kikin <a.kikin@tano-systems.com>
# Released under the MIT license (see COPYING.MIT for the terms)

PR = "tano7"

DESCRIPTION = "OpenWrt uqmi utility"
HOMEPAGE = "http://git.openwrt.org/?p=project/uqmi.git;a=summary"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://main.c;beginline=1;endline=20;md5=3f7041e5710007661d762bb6043a69c6"
SECTION = "base"
DEPENDS = "libubox json-c"

RDEPENDS_${PN} += "kmod-usb-net kmod-usb-net-qmi-wwan"
do_configure[depends] += "virtual/kernel:do_shared_workdir"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI = "git://${GIT_OPENWRT_ORG}/project/uqmi.git"

# 20.06.2019
# uqmi: add explicit check for message type when expecting a response
SRCREV = "1965c713937495a5cb029165c16acdb6572c3f87"

SRC_URI += "\
	file://qmi.sh \
"

S = "${WORKDIR}/git"

inherit cmake pkgconfig openwrt

B = "${S}"

FILES_${PN} += "\
	${base_libdir}/netifd/proto/qmi.sh \
"

do_install_append() {
	install -dm 0755 ${D}${base_libdir}/netifd/proto
	install -m 0755 ${WORKDIR}/qmi.sh ${D}${base_libdir}/netifd/proto/qmi.sh
}
