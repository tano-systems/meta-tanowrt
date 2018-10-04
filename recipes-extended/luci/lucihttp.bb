# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>

PR = "tano2"

DESCRIPTION = "LuCI HTTP utility library"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a6b7565d075eb26cd08b6ac739db35e3"
DEPENDS = "lua5.1"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

inherit cmake openwrt pkgconfig

SRC_URI = "\
	git://github.com/jow-/lucihttp.git;protocol=https \
	file://0001-test-utils-Use-portable-format-for-size_t-in-printf.patch \
"

SRCREV = "cb119deddee5f0f8f1da883b20c60aea7611b175" 

PACKAGECONFIG ??= "build-lua"
PACKAGECONFIG[build-lua] = "-DBUILD_LUA=ON,-DBUILD_LUA=OFF,"

OECMAKE_C_FLAGS += "-DBUILD_TESTS=OFF -DLUAPATH=/usr/lib/lua/5.1"

S = "${WORKDIR}/git/"

do_install_append() {
	install -dm 0755 ${D}${libdir}
	install -m 0755 ${B}/liblucihttp.so ${D}${libdir}/liblucihttp.so
	install -dm 0755 ${D}/usr/include/lucihttp
}

