#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Copyright (C) 2018-2019 Anton Kikin <a.kikin@tano-systems.com>
#

PR = "tano11"

DESCRIPTION = "OpenWrt uqmi utility"
HOMEPAGE = "http://git.openwrt.org/?p=project/uqmi.git;a=summary"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://main.c;beginline=1;endline=20;md5=3f7041e5710007661d762bb6043a69c6"
SECTION = "base"
DEPENDS = "libubox json-c"

inherit kmod/usb-net
inherit kmod/usb-net-qmi-wwan
do_configure[depends] += "virtual/kernel:do_shared_workdir"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI = "git://${GIT_OPENWRT_ORG}/project/uqmi.git"

# 07.10.2019
# uqmi: add timeout parameter
SRCREV = "0a19b5b77140465c29e2afa7d611fe93abc9672f"

SRC_URI += "\
	file://qmi.sh \
"

S = "${WORKDIR}/git"

inherit cmake pkgconfig

B = "${S}"

FILES_${PN} += "\
	${nonarch_base_libdir}/netifd/proto/qmi.sh \
"

do_install_append() {
	install -dm 0755 ${D}${nonarch_base_libdir}/netifd/proto
	install -m 0755 ${WORKDIR}/qmi.sh ${D}${nonarch_base_libdir}/netifd/proto/qmi.sh
}
