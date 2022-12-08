#
# SPDX-License-Identifier: MIT
#
# Octo's ping library
#
# This file Copyright (C) 2018-2019, 2022 Tano Systems LLC
# Anton Kikin <a.kikin@tano-systems.com>
#

SUMMARY = "Octo's ping library"
DESCRIPTION = "liboping is a C library to generate ICMP echo requests, better known as 'ping packets'. It is intended for use in network monitoring applications or applications that would otherwise need to fork ping frequently."
SECTION = "libs"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c"
DEPENDS = "ncurses"

PACKAGES += "oping noping"

PV = "1.10.0"
PR = "tano0"

SRC_URI = "https://noping.cc/files/${BPN}-${PV}.tar.gz"
SRC_URI[sha256sum] = "c206b05743d0730814be3115b48abd0b00016677525153c78730da307aba0846"

# Patches
SRC_URI += "\
	file://0001-Fix-building-for-GCC-11.patch \
"

S = "${WORKDIR}/${BPN}-${PV}"

inherit pkgconfig autotools

EXTRA_OECONF = "\
	--without-perl-bindings \
	--enable-shared \
	--enable-static \
"

LEAD_SONAME = "liboping.so"
FILES:${PN} = "${includedir} ${libdir}"
FILES:${PN}-doc = "${mandir}"

SUMMARY:oping = "oping - Send ICMP ECHO_REQUEST to network hosts"
FILES:oping += "${bindir}/oping"
RDEPENDS:oping += "liboping"

SUMMARY:noping = "noping - Send ICMP ECHO_REQUEST to network hosts"
FILES:noping += "${bindir}/noping"
RDEPENDS:noping += "ncurses liboping"
