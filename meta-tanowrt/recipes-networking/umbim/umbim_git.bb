#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Copyright (C) 2018-2019, 2022 Anton Kikin <a.kikin@tano-systems.com>
#

PR = "tano9"

DESCRIPTION = "OpenWrt MBIM modem utility"
HOMEPAGE = "http://git.openwrt.org/?p=project/umbim.git;a=summary"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://mbim.h;beginline=1;endline=13;md5=8c7ce85ebfe23634010c75c30c3eb223"
SECTION = "base"
DEPENDS = "libubox"

inherit kmod/usb-net
inherit kmod/usb-net-cdc-mbim
do_configure[depends] += "virtual/kernel:do_shared_workdir"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI = "git://${GIT_OPENWRT_ORG}/project/umbim.git;branch=master \
          "

# 11.09.2019
# umbim: add home provider query support
SRCREV = "184b707ddaa0acee84d02e0ffe599cb8b67782bd"

CFLAGS += "${@oe.utils.version_less_or_equal('GCCVERSION', '8.4.0', '', '-Wno-error=address-of-packed-member', d)}"

SRC_URI += "\
	file://mbim.sh \
"

S = "${WORKDIR}/git"

inherit cmake pkgconfig

FILES:${PN} += "\
	${nonarch_base_libdir}/netifd/proto/mbim.sh \
"

do_install:append() {
	install -dm 0755 ${D}${nonarch_base_libdir}/netifd/proto
	install -m 0755 ${WORKDIR}/mbim.sh ${D}${nonarch_base_libdir}/netifd/proto/mbim.sh
}
