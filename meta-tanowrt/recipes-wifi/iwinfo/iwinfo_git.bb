# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Copyright (C) 2018-2020 Anton Kikin <a.kikin@tano-systems.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Library for accessing wireless device drivers"
HOMEPAGE = "http://git.openwrt.org/?p=project/iwinfo.git;a=summary"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe"
SECTION = "base"
DEPENDS += "ubus uci lua5.1"
PR = "tano12"

RDEPENDS_${PN} += "kmod-cfg80211"
RPROVIDES_${PN} += "libiwinfo libiwinfo-lua"
PROVIDES += "libiwinfo libiwinfo-lua"

# 29.01.2020
# iwinfo: add device id for Atheros AR9287
SRCREV = "bb216982951698833bbdf4a88872e9b5ccd026a5"

inherit tanowrt pkgconfig

PACKAGECONFIG ??= "backend-nl80211"
PACKAGECONFIG[backend-nl80211] = ",,libnl,"
PACKAGECONFIG[backend-wl] = ",,,"
PACKAGECONFIG[backend-madwifi] = ",,,"

SRC_URI = "git://${GIT_OPENWRT_ORG}/project/iwinfo.git"

# Patches
SRC_URI += "\
	file://0001-fix-typo-in-spcifying-typename-luaL_Reg.patch \
	file://0001-fix-order-of-linker-cmdline-to-help-linking-when-usi.patch \
	file://0001-Makefile-LDFLAGS-set-liblua5.1-for-lua-lib.patch \
	file://0002-use-libnl3-instead-of-libnl-tiny.patch \
"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

S = "${WORKDIR}/git"

TARGET_CFLAGS += "\
	-fPIC \
	-D_GNU_SOURCE \
	${@bb.utils.contains('PACKAGECONFIG', 'backend-nl80211', '-I${STAGING_INCDIR}/libnl3', '', d)} \
"

IWINFO_BACKENDS = "\
	${@bb.utils.contains('PACKAGECONFIG', 'backend-nl80211', 'nl80211', '', d)} \
	${@bb.utils.contains('PACKAGECONFIG', 'backend-wl', 'wl', '', d)} \
	${@bb.utils.contains('PACKAGECONFIG', 'backend-madwifi', 'madwifi', '', d)} \
"

EXTRA_OEMAKE = "\
	'BACKENDS=${IWINFO_BACKENDS}' \
"

# iwinfo breaks with parallel make
PARALLEL_MAKE = ""

do_install() {
	install -D -m 0755 ${B}/libiwinfo.so ${D}${libdir}/libiwinfo.so
	install -D -m 0755 ${B}/iwinfo.so ${D}${libdir}/lua/5.1/iwinfo.so
	install -D -m 0755 ${B}/iwinfo ${D}${bindir}/iwinfo
	install -D -m 0644 ${S}/include/iwinfo.h ${D}${includedir}/iwinfo.h
	install -D -m 0644 ${S}/include/iwinfo/utils.h ${D}${includedir}/iwinfo/utils.h
	install -D -m 0644 ${S}/hardware.txt ${D}${datadir}/libiwinfo/hardware.txt
}

FILES_${PN} += "${datadir}"
