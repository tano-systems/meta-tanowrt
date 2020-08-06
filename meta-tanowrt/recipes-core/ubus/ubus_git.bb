# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Copyright (C) 2018-2020 Anton Kikin <a.kikin@tano-systems.com>
# Released under the MIT license (see COPYING.MIT for the terms)

PR = "tano12"
DESCRIPTION = "OpenWrt system message/RPC bus"
HOMEPAGE = "http://git.openwrt.org/?p=project/libubox.git;a=summary"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://ubusd.c;beginline=1;endline=12;md5=1b6a7aecd35bdd25de35da967668485d"
SECTION = "base"
DEPENDS = "json-c libubox"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI = "git://${GIT_OPENWRT_ORG}/project/ubus.git"

SRC_URI += "file://0001-cli-fix-type-displaying-for-BLOBMSG_TYPE_DOUBLE-argu.patch"
SRC_URI += "file://0002-cli-fix-type-displaying-for-BLOBMSG_TYPE_INT64.patch"
SRC_URI += "file://0003-Make-libubus-thread-safe.patch"

# 03.02.2020
# lua: avoid truncation of large numeric values
SRCREV = "171469e3138cce191892e20b6fd35b52c9368064"

S = "${WORKDIR}/git"

#
# ubus = /usr/bin/ubus
# ubusd = /usr/sbin/ubusd
# libubus = /usr/lib/*.so
# libubus-lua = /usr/lib/lua/51./*.so
#
PROVIDES += "ubusd libubus libubus-lua"
RPROVIDES_${PN} += "ubusd libubus libubus-lua"

inherit cmake pkgconfig tanowrt-lua

FILES_SOLIBSDEV = ""

do_install_append () {
    install -dm 0755 ${D}/sbin
    ln -s /usr/sbin/ubusd ${D}/sbin/ubusd
}
