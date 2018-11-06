# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>
# Released under the MIT license (see COPYING.MIT for the terms)

PR = "tano5"
DESCRIPTION = "OpenWrt system message/RPC bus"
HOMEPAGE = "http://git.openwrt.org/?p=project/libubox.git;a=summary"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://ubusd.c;beginline=1;endline=12;md5=1b6a7aecd35bdd25de35da967668485d"
SECTION = "base"
DEPENDS = "json-c libubox"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI = "git://git.openwrt.org/project/ubus.git"

SRC_URI += "file://0001-Make-libubus-thread-safe.patch"

# 06.10.2018
# ubusd_acl: event send access list support
SRCREV = "221ce7e7ff1bd1a0c9995fa9d32f58e865f7207f"

S = "${WORKDIR}/git"

#
# ubus = /usr/bin/ubus
# ubusd = /usr/sbin/ubusd
# libubus = /usr/lib/*.so
# libubus-lua = /usr/lib/lua/51./*.so
#
PROVIDES += "ubusd libubus libubus-lua"
RPROVIDES_${PN} += "ubusd libubus libubus-lua"

inherit cmake pkgconfig openwrt

do_install_append () {
    install -dm 0755 ${D}/sbin
    ln -s /usr/sbin/ubusd ${D}/sbin/ubusd
}
