#
# SPDX-License-Identifier: MIT
#
# Octo's ping library
#
# This file Copyright (C) 2018-2019 Tano Systems
# Anton Kikin <a.kikin@tano-systems.com>
#

SUMMARY = "Octo's ping library"
DESCRIPTION = "liboping is a C library to generate ICMP echo requests, better known as 'ping packets'. It is intended for use in network monitoring applications or applications that would otherwise need to fork ping frequently."
SECTION = "libs"
LICENSE = "LGPL-2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c"
DEPENDS = "ncurses"

PACKAGES += "oping noping"

PV = "1.9.0"
PR = "tano2"

SRC_URI = "https://noping.cc/files/${BPN}-${PV}.tar.gz \
           file://0001-Fix-building-for-GCC-8.2.patch \
           "

SRC_URI[md5sum] = "28d085b95d1ca1acd541fc2606d5e02d"
SRC_URI[sha256sum] = "86b44f684a3151bd4b5b75336876635ecb0f6cfe54a2fb29a6da06432f2dbb00"

S = "${WORKDIR}/${BPN}-${PV}"

inherit autotools

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

