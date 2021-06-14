#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Copyright (C) 2018-2021 Anton Kikin <a.kikin@tano-systems.com>
#

PR = "tano7"
DESCRIPTION = "libubox HTTP client library"
HOMEPAGE = "http://git.openwrt.org/?p=project/uclient.git;a=summary"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://uclient-backend.h;beginline=1;endline=17;md5=b96bb2c7c7edb5a7cff262e23626e382"
SECTION = "base"
DEPENDS = "libubox ustream-ssl"

SRC_URI = "git://${GIT_OPENWRT_ORG}/project/uclient.git \
          "

# 15.05.2021
# uclient-http: set eof mark when content-length is 0
SRCREV = "6a6011df3429ffa5958d12b1327eeda4fd9daa47"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

S = "${WORKDIR}/git"

inherit cmake pkgconfig

FILES_SOLIBSDEV = ""

FILES_${PN} += "${libdir}/*"

OECMAKE_C_FLAGS += "${@bb.utils.contains('TOOLCHAIN', 'clang', '-Wno-error=ignored-qualifiers', '-Wno-error=discarded-qualifiers', d)} "
