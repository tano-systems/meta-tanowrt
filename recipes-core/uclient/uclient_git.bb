# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>
# Released under the MIT license (see COPYING.MIT for the terms)

PR = "tano1"
DESCRIPTION = "libubox HTTP client library"
HOMEPAGE = "http://git.openwrt.org/?p=project/uclient.git;a=summary"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://uclient-backend.h;beginline=1;endline=17;md5=b96bb2c7c7edb5a7cff262e23626e382"
SECTION = "base"
DEPENDS = "libubox ustream-ssl"

SRC_URI = "git://git.openwrt.org/project/uclient.git \
          "

# 22.08.2018
# uclient-utils: Handle memory allocation failure for url file name
SRCREV = "eb850df45758be784b67b63dcbe31bd331c12483"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

S = "${WORKDIR}/git"

inherit cmake pkgconfig openwrt

OECMAKE_C_FLAGS += "-Wno-error=discarded-qualifiers"
