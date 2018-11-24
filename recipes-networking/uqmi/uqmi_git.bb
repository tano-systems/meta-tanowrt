# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>
# Released under the MIT license (see COPYING.MIT for the terms)

PR = "tano4"

DESCRIPTION = "OpenWrt uqmi utility"
HOMEPAGE = "http://git.openwrt.org/?p=project/uqmi.git;a=summary"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://main.c;beginline=1;endline=20;md5=3f7041e5710007661d762bb6043a69c6"
SECTION = "base"
DEPENDS = "libubox json-c"

RDEPENDS_${PN} += "kmod-usb-net kmod-usb-net-qmi-wwan"
do_configure[depends] += "virtual/kernel:do_shared_workdir"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI = "git://git.openwrt.org/project/uqmi.git"

# 24.05.2017
# uqmi_add_command: fixed command argument assignment
SRCREV = "01944dd7089b15f55b463072e1b46f1144e8aab4"

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
