#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Copyright (C) 2018-2023 Anton Kikin <a.kikin@tano-systems.com>
#

PR = "tano21"
DESCRIPTION = "OpenWrt system message/RPC bus"
HOMEPAGE = "http://git.openwrt.org/?p=project/libubox.git;a=summary"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://ubusd.c;beginline=1;endline=12;md5=1b6a7aecd35bdd25de35da967668485d"
SECTION = "base"
DEPENDS = "json-c libubox"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI = "git://${GIT_OPENWRT_ORG}/project/ubus.git;branch=master"

SRC_URI += "file://0001-cli-fix-type-displaying-for-BLOBMSG_TYPE_DOUBLE-argu.patch"
SRC_URI += "file://0002-cli-fix-type-displaying-for-BLOBMSG_TYPE_INT64.patch"
SRC_URI += "file://0003-Make-libubus-thread-safe.patch"

# 15.06.2022
# ubusd: add lookup command queuing support
SRCREV = "9913aa61de739e3efe067a2d186021c20bcd65e2"

S = "${WORKDIR}/git"

#
# ubus = /usr/bin/ubus
# ubusd = /usr/sbin/ubusd
# libubus = /usr/lib/*.so
# libubus-lua = /usr/lib/lua/51./*.so
#
PROVIDES += "ubusd libubus libubus-lua"
RPROVIDES:${PN} += "ubusd libubus libubus-lua"

inherit cmake pkgconfig tanowrt-lua

FILES_SOLIBSDEV = ""

do_install:append () {
    install -dm 0755 ${D}/sbin
    install -dm 0755 ${D}/bin
    ln -s /usr/sbin/ubusd ${D}/sbin/ubusd
    ln -s /usr/bin/ubus ${D}/bin/ubus
}

inherit useradd

USERADD_PACKAGES = "${PN}"
USERADD_PARAM:${PN} = "\
	--system \
	-d /var/run/ubus \
	--no-create-home \
	--shell /bin/false \
	-g ubus \
	  ubus \
"

GROUPADD_PARAM:${PN} = "--system ubus"
