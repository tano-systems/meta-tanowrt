#
# SPDX-License-Identifier: MIT
#
# Copyright (c) 2022 Tano Systems LLC. All rights reserved.
#

PR = "tano0"

PKG_TITLE = "ucode - Tiny scripting and templating language"
SUMMARY = "${PKG_TITLE}"
DESCRIPTION = "\
ucode is a tiny script interpreter featuring an ECMAScript oriented \
script language and Jinja-inspired templating."

HOMEPAGE = "http://github.com/jow-/ucode"
LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b42eb47dc3802282b0d1be1bc8f5336c"
SECTION = "utils"
DEPENDS += "json-c libuci libubus libnl libblobmsg-json"

PROVIDES += "libucode"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI = "git://github.com/jow-/ucode.git;protocol=https;branch=master"

# Patches
SRC_URI += "file://0001-Use-libbl3-instead-of-libnl-tiny.patch"

# 15.02.2022
# tests: fix proto() testcase
SRCREV = "1af23a9db98575eecfc97e01e8f581221d1a224b"
S = "${WORKDIR}/git"

inherit cmake

EXTRA_OECMAKE += "\
	-DFS_SUPPORT=ON \
	-DMATH_SUPPORT=ON \
	-DUBUS_SUPPORT=ON \
	-DUCI_SUPPORT=ON \
	-DRTNL_SUPPORT=ON \
	-DNL80211_SUPPORT=ON \
	-DRESOLV_SUPPORT=ON \
	-DSTRUCT_SUPPORT=ON \
	-DCMAKE_INSTALL_LIBDIR:PATH=/usr/lib \
"

PACKAGES += "\
	libucode \
	libucode-dev \
	${PN}-mod-fs \
	${PN}-mod-math \
	${PN}-mod-nl80211 \
	${PN}-mod-resolv \
	${PN}-mod-rtnl \
	${PN}-mod-struct \
	${PN}-mod-ubus \
	${PN}-mod-uci \
"

PRIVATE_LIBS = "\
	fs.so \
	math.so \
	nl80211.so \
	resolv.so \
	rtnl.so \
	struct.so \
	ubus.so \
	uci.so \
"

RDEPENDS_${PN} += "libucode"
FILES_${PN} = "${bindir}/"

SUMMARY_libucode = "${PKG_TITLE} - runtime library"
DESCRIPTION_libucode = "\
The libucode package provides the shared runtime library for the ucode interpreter."
FILES_libucode += "${libdir}/*.so*"
FILES_libucode-dev = "${includedir}/"

SUMMARY_${PN}-mod-fs = "${PKG_TITLE} (filesystem module)"
DESCRIPTION_${PN}-mod-fs = "\
The filesystem plugin module allows interaction with the local file system."
RDEPENDS_${PN}-mod-fs += "libucode"
FILES_${PN}-mod-fs = "${libdir}/ucode/fs.so"

SUMMARY_${PN}-mod-math = "${PKG_TITLE} (math module)"
DESCRIPTION_${PN}-mod-math = "\
The math plugin provides access to various <math.h> procedures."
RDEPENDS_${PN}-mod-math += "libucode"
FILES_${PN}-mod-math = "${libdir}/ucode/math.so"

SUMMARY_${PN}-mod-nl80211 = "${PKG_TITLE} (nl80211 module)"
DESCRIPTION_${PN}-mod-nl80211 = "\
The nl80211 plugin provides access to the Linux wireless 802.11 netlink API."
RDEPENDS_${PN}-mod-nl80211 += "libucode"
FILES_${PN}-mod-nl80211 = "${libdir}/ucode/nl80211.so"

SUMMARY_${PN}-mod-resolv = "${PKG_TITLE} (resolv module)"
DESCRIPTION_${PN}-mod-resolv = "\
The resolv plugin implements simple DNS resolving."
RDEPENDS_${PN}-mod-resolv += "libucode"
FILES_${PN}-mod-resolv = "${libdir}/ucode/resolv.so"

SUMMARY_${PN}-mod-rtnl = "${PKG_TITLE} (rtnl module)"
DESCRIPTION_${PN}-mod-rtnl = "\
The rtnl plugin provides access to the Linux routing netlink API."
RDEPENDS_${PN}-mod-rtnl += "libucode"
FILES_${PN}-mod-rtnl = "${libdir}/ucode/rtnl.so"

SUMMARY_${PN}-mod-struct = "${PKG_TITLE} (struct module)"
DESCRIPTION_${PN}-mod-struct = "\
The struct plugin implemnts Python 3 compatible struct.pack/unpack functionality."
RDEPENDS_${PN}-mod-struct += "libucode"
FILES_${PN}-mod-struct = "${libdir}/ucode/struct.so"

SUMMARY_${PN}-mod-ubus = "${PKG_TITLE} (ubus module)"
DESCRIPTION_${PN}-mod-ubus = "\
The ubus module allows ucode template scripts to enumerate and invoke ubus \
procedures."
RDEPENDS_${PN}-mod-ubus += "libucode"
FILES_${PN}-mod-ubus = "${libdir}/ucode/ubus.so"

SUMMARY_${PN}-mod-uci = "${PKG_TITLE} (uci module)"
DESCRIPTION_${PN}-mod-uci = "\
The uci module allows templates to read and modify uci configuration."
RDEPENDS_${PN}-mod-uci += "libucode"
FILES_${PN}-mod-uci = "${libdir}/ucode/uci.so"

BBCLASSEXTEND = "native"
