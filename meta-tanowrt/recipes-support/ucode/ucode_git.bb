#
# SPDX-License-Identifier: MIT
#
# Copyright (c) 2022-2023 Tano Systems LLC. All rights reserved.
#

PR = "tano2"
PV = "0.0.20221018+git${SRCPV}"

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

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI = "git://github.com/jow-/ucode.git;protocol=https;branch=master"

# Patches
SRC_URI += "file://0001-Use-libbl3-instead-of-libnl-tiny.patch"

# 01.02.2023
# Merge pull request #140 from nbd168/rtnl
SRCREV = "599a7fb59380a145c615fd1bad80a42ff8bdc360"
S = "${WORKDIR}/git"

inherit cmake pkgconfig

do_configure:prepend () {
	if [ -e "${S}/CMakeLists.txt" ] ; then
		sed -i -e "s:\${CMAKE_INSTALL_PREFIX}/lib:\${CMAKE_INSTALL_LIBDIR}:g" \
		       -e "s:LIBRARY DESTINATION lib/ucode:LIBRARY DESTINATION \${CMAKE_INSTALL_LIBDIR}/ucode:g" \
		       -e "s:LIBRARY DESTINATION lib:LIBRARY DESTINATION \${CMAKE_INSTALL_LIBDIR}:g" \
		       ${S}/CMakeLists.txt
	fi
}

EXTRA_OECMAKE += "\
	-DFS_SUPPORT=ON \
	-DMATH_SUPPORT=ON \
	-DUBUS_SUPPORT=ON \
	-DUCI_SUPPORT=ON \
	-DRTNL_SUPPORT=ON \
	-DNL80211_SUPPORT=ON \
	-DRESOLV_SUPPORT=ON \
	-DSTRUCT_SUPPORT=ON \
	-DULOOP_SUPPORT=ON \
	-DCMAKE_INSTALL_LIBDIR:PATH=${libdir} \
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
	${PN}-mod-uloop \
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

RDEPENDS:${PN} += "libucode"
FILES:${PN} = "${bindir}/"

SUMMARY:libucode = "${PKG_TITLE} - runtime library"
DESCRIPTION:libucode = "\
The libucode package provides the shared runtime library for the ucode interpreter."
FILES:libucode += "${libdir}/*.so*"
FILES:libucode-dev = "${includedir}/"

SUMMARY:${PN}-mod-fs = "${PKG_TITLE} (filesystem module)"
DESCRIPTION:${PN}-mod-fs = "\
The filesystem plugin module allows interaction with the local file system."
RDEPENDS:${PN}-mod-fs += "libucode"
FILES:${PN}-mod-fs = "${libdir}/ucode/fs.so"

SUMMARY:${PN}-mod-math = "${PKG_TITLE} (math module)"
DESCRIPTION:${PN}-mod-math = "\
The math plugin provides access to various <math.h> procedures."
RDEPENDS:${PN}-mod-math += "libucode"
FILES:${PN}-mod-math = "${libdir}/ucode/math.so"

SUMMARY:${PN}-mod-nl80211 = "${PKG_TITLE} (nl80211 module)"
DESCRIPTION:${PN}-mod-nl80211 = "\
The nl80211 plugin provides access to the Linux wireless 802.11 netlink API."
RDEPENDS:${PN}-mod-nl80211 += "libucode"
FILES:${PN}-mod-nl80211 = "${libdir}/ucode/nl80211.so"

SUMMARY:${PN}-mod-resolv = "${PKG_TITLE} (resolv module)"
DESCRIPTION:${PN}-mod-resolv = "\
The resolv plugin implements simple DNS resolving."
RDEPENDS:${PN}-mod-resolv += "libucode"
FILES:${PN}-mod-resolv = "${libdir}/ucode/resolv.so"

SUMMARY:${PN}-mod-rtnl = "${PKG_TITLE} (rtnl module)"
DESCRIPTION:${PN}-mod-rtnl = "\
The rtnl plugin provides access to the Linux routing netlink API."
RDEPENDS:${PN}-mod-rtnl += "libucode"
FILES:${PN}-mod-rtnl = "${libdir}/ucode/rtnl.so"

SUMMARY:${PN}-mod-struct = "${PKG_TITLE} (struct module)"
DESCRIPTION:${PN}-mod-struct = "\
The struct plugin implemnts Python 3 compatible struct.pack/unpack functionality."
RDEPENDS:${PN}-mod-struct += "libucode"
FILES:${PN}-mod-struct = "${libdir}/ucode/struct.so"

SUMMARY:${PN}-mod-ubus = "${PKG_TITLE} (ubus module)"
DESCRIPTION:${PN}-mod-ubus = "\
The ubus module allows ucode template scripts to enumerate and invoke ubus \
procedures."
RDEPENDS:${PN}-mod-ubus += "libucode"
FILES:${PN}-mod-ubus = "${libdir}/ucode/ubus.so"

SUMMARY:${PN}-mod-uci = "${PKG_TITLE} (uci module)"
DESCRIPTION:${PN}-mod-uci = "\
The uci module allows templates to read and modify uci configuration."
RDEPENDS:${PN}-mod-uci += "libucode"
FILES:${PN}-mod-uci = "${libdir}/ucode/uci.so"

SUMMARY:${PN}-mod-uloop = "${PKG_TITLE} (uloop module)"
DESCRIPTION:${PN}-mod-uloop = "\
The uloop module allows ucode scripts to interact with OpenWrt uloop event \
loop implementation."
RDEPENDS:${PN}-mod-uloop += "libucode"
FILES:${PN}-mod-uloop = "${libdir}/ucode/uloop.so"

BBCLASSEXTEND = "native"
