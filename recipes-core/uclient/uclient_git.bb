# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Copyright (C) 2018-2019 Anton Kikin <a.kikin@tano-systems.com>
# Released under the MIT license (see COPYING.MIT for the terms)

PR = "tano3"
DESCRIPTION = "libubox HTTP client library"
HOMEPAGE = "http://git.openwrt.org/?p=project/uclient.git;a=summary"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://uclient-backend.h;beginline=1;endline=17;md5=b96bb2c7c7edb5a7cff262e23626e382"
SECTION = "base"
DEPENDS = "libubox ustream-ssl"

SRC_URI = "git://${GIT_OPENWRT_ORG_URL}/project/uclient.git \
          "

# 04.08.2018
# uclient-http: Close ustream file handle only if allocated
SRCREV = "ae1c656ff041c6f1ccb37b070fa261e0d71f2b12"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

S = "${WORKDIR}/git"

inherit cmake pkgconfig openwrt

OECMAKE_C_FLAGS += "-Wno-error=discarded-qualifiers"
