# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>
# Released under the MIT license (see COPYING.MIT for the terms)

PR = "tano3"

DESCRIPTION = "OpenWrt MBIM modem utility"
HOMEPAGE = "http://git.openwrt.org/?p=project/umbim.git;a=summary"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://mbim.h;beginline=1;endline=13;md5=8c7ce85ebfe23634010c75c30c3eb223"
SECTION = "base"
DEPENDS = "libubox"

RDEPENDS_${PN} += "kmod-usb-net kmod-usb-net-cdc-mbim"
do_configure[depends] += "virtual/kernel:do_shared_workdir"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI = "git://git.openwrt.org/project/umbim.git \
          "

# 11.05.2016
# add radio_state set/query support
SRCREV = "29aaf43b097ee57f7aa1bb24341db6cc4148cbf3"

SRC_URI += "\
	file://mbim.sh \
"

S = "${WORKDIR}/git"

inherit cmake pkgconfig openwrt

FILES_${PN} += "\
	${base_libdir}/netifd/proto/mbim.sh \
"

do_install_append() {
	install -dm 0755 ${D}${base_libdir}/netifd/proto
	install -m 0755 ${WORKDIR}/mbim.sh ${D}${base_libdir}/netifd/proto/mbim.sh
}
