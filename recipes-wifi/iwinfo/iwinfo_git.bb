# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Copyright (C) 2018-2019 Anton Kikin <a.kikin@tano-systems.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Library for accessing wireless device drivers"
HOMEPAGE = "http://git.openwrt.org/?p=project/iwinfo.git;a=summary"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe"
SECTION = "base"
DEPENDS += "ubus uci lua5.1"
PR = "tano4"

RPROVIDES_${PN} += "libiwinfo libiwinfo-lua"
PROVIDES += "libiwinfo libiwinfo-lua"

# 31.07.2018
# Revert "build: compile with -ffunction-sections, -fdata-sections and LTO"
SRCREV = "65b8333f5b66f8812c1ed21a4d5b28a576e6ba63"

inherit openwrt

SRC_URI = "${GIT_OPENWRT_ORG_URL}/project/iwinfo.git \
           file://0001-fix-typo-in-spcifying-typename-luaL_Reg.patch \
           file://0001-fix-order-of-linker-cmdline-to-help-linking-when-usi.patch \
           file://0001-Makefile-LDFLAGS-set-liblua5.1-for-lua-lib.patch \
          "

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

S = "${WORKDIR}/git"

CFLAGS += "-fPIC"

# iwinfo breaks with parallel make
PARALLEL_MAKE = ""

do_install() {
	install -D -m 0755 ${B}/libiwinfo.so ${D}${libdir}/libiwinfo.so
	install -D -m 0755 ${B}/iwinfo.so ${D}${libdir}/lua/5.1/iwinfo.so
	install -D -m 0755 ${B}/iwinfo ${D}${bindir}/iwinfo
	install -D -m 0644 ${S}/include/iwinfo.h ${D}${includedir}/iwinfo.h
	install -D -m 0644 ${S}/include/iwinfo/utils.h ${D}${includedir}/iwinfo/utils.h
}
