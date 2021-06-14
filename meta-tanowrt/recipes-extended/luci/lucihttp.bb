#
# SPDX-License-Identifier: MIT
# Copyright (C) 2018-2021 Anton Kikin <a.kikin@tano-systems.com>
#
PR = "tano7"

DESCRIPTION = "LuCI HTTP utility library"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a6b7565d075eb26cd08b6ac739db35e3"
DEPENDS = "lua5.1"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

inherit cmake tanowrt-lua pkgconfig

FILES_SOLIBSDEV = ""

SRC_URI = "\
	git://github.com/jow-/lucihttp.git;protocol=https \
"

SRCREV = "3dc89af443410c9eae7a0ff091bd7fb4c5d48f41"

PACKAGECONFIG ??= "build-lua"
PACKAGECONFIG[build-lua] = "-DBUILD_LUA=ON,-DBUILD_LUA=OFF,"

OECMAKE_C_FLAGS += "-DBUILD_TESTS=OFF -DLUAPATH=/usr/lib/lua/5.1"
OECMAKE_C_FLAGS += "${@bb.utils.contains('TOOLCHAIN', 'clang', '-Wno-unknown-warning-option', '', d)}"

S = "${WORKDIR}/git"

do_install_append() {
	install -dm 0755 ${D}${libdir}
	install -m 0755 ${B}/liblucihttp.so ${D}${libdir}/liblucihttp.so
	install -dm 0755 ${D}/usr/include/lucihttp
}

