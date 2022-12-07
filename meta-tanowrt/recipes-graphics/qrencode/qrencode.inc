#
# SPDX-License-Identifier: MIT
# Copyright (c) 2019-2020 Tano Systems LLC. All rights reserved.
#

SUMMARY = "C library for encoding data in a QR Code symbol"
AUTHOR = "Kentaro Fukuchi"
HOMEPAGE = "http://fukuchi.org/works/qrencode/"
SECTION = "libs"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=2d5025d4aa3495befef8f17206a5b0a1"

INC_PR = "1"

inherit autotools pkgconfig

PACKAGECONFIG ??= "png"
PACKAGECONFIG[png] = "--with-png,--without-png,libpng"

PACKAGES += "${PN}-tools"

FILES:${PN} = "${libdir}/libqrencode.so*"
RRECOMMENDS:${PN} += "${PN}-tools"

FILES:${PN}-tools = "${bindir}/"
RDEPENDS:${PN}-tools += "${PN}"

S = "${WORKDIR}/git"

EXTRA_OECONF += "--without-tests"
