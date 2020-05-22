# This file Copyright (C) 2019-2020 Anton Kikin <a.kikin@tano-systems.com>

PR = "tano1"
PV = "1.0.2"

DESCRIPTION = "C extension module for Lua 5.1/5.2 which adds bitwise operations on numbers"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://README;md5=58546e94cc441c9e77c2d59935ce5bed"
DEPENDS = "lua5.1"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

inherit tanowrt-lua pkgconfig

SRC_URI = "http://bitop.luajit.org/download/LuaBitOp-${PV}.tar.gz"
SRC_URI[md5sum] = "d0c1080fe0c844e8477279668e2d0d06"
SRC_URI[sha256sum] = "1207c9293dcd52eb9dca6538d1b87352bd510f4e760938f5048433f7f272ce99"

SRC_URI += "file://Makefile.patch \
            file://0001-use-LNUM_-instead-of-LUA_NUMBER_.patch"

S = "${WORKDIR}/LuaBitOp-${PV}"

do_install_append() {
	install -dm 0755 ${D}${libdir}/lua/5.1
	install -m 0755 ${S}/bit.so ${D}${libdir}/lua/5.1
}
