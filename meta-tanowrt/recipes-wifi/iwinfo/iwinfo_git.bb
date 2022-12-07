#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Copyright (C) 2018-2021 Anton Kikin <a.kikin@tano-systems.com>
#

DESCRIPTION = "Library for accessing wireless device drivers"
HOMEPAGE = "http://git.openwrt.org/?p=project/iwinfo.git;a=summary"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe"
SECTION = "base"
DEPENDS += "ubus uci lua5.1"
PR = "tano21"

inherit kmod/cfg80211
RPROVIDES:${PN} += "libiwinfo libiwinfo-lua"
PROVIDES += "libiwinfo libiwinfo-lua"

# 09.06.2021
# iwinfo: nl80211: fix typo
SRCREV = "c0414642fead263a4a6a686ad3cb7e965ec8a23a"
IWINFO_ABI_VERSION = "20210106"

FILES_SOLIBSDEV = ""

inherit tanowrt-lua pkgconfig

PACKAGECONFIG ??= "backend-nl80211"
PACKAGECONFIG[backend-nl80211] = ",,libnl,"
PACKAGECONFIG[backend-wl] = ",,,"
PACKAGECONFIG[backend-madwifi] = ",,,"

SRC_URI = "git://${GIT_OPENWRT_ORG}/project/iwinfo.git;branch=master"

# Patches
SRC_URI += "\
	file://0001-fix-typo-in-spcifying-typename-luaL_Reg.patch \
	file://0002-fix-order-of-linker-cmdline-to-help-linking-when-usi.patch \
	file://0003-Makefile-LDFLAGS-set-liblua5.1-for-lua-lib.patch \
	file://0004-use-libnl3-instead-of-libnl-tiny.patch \
	file://0005-iwinfo-add-device-id-for-Intel-Dual-Band-Wireless-AC.patch \
	file://0006-iwinfo-add-device-id-for-Realtek-RTL8821CE.patch \
	file://0007-iwinfo-add-device-id-for-Broadcom-BCM4350.patch \
"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

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
	'SOVERSION=${IWINFO_ABI_VERSION}' \
"

# iwinfo breaks with parallel make
PARALLEL_MAKE = ""

do_install() {
	install -D -m 0755 ${B}/libiwinfo.so ${D}${libdir}/libiwinfo.so
	install -D -m 0755 ${B}/libiwinfo.so.${IWINFO_ABI_VERSION} ${D}${libdir}/libiwinfo.so.${IWINFO_ABI_VERSION}
	install -D -m 0755 ${B}/iwinfo.so ${D}${libdir}/lua/5.1/iwinfo.so
	install -D -m 0755 ${B}/iwinfo ${D}${bindir}/iwinfo
	install -D -m 0644 ${S}/include/iwinfo.h ${D}${includedir}/iwinfo.h
	install -D -m 0644 ${S}/include/iwinfo/utils.h ${D}${includedir}/iwinfo/utils.h
	install -D -m 0644 ${S}/hardware.txt ${D}${datadir}/libiwinfo/hardware.txt
}

FILES:${PN} += "${datadir} ${libdir}"

LEAD_SONAME = "libiwinfo.so"
BBCLASSEXTEND = "native nativesdk"
