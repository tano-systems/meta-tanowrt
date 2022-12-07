#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2016 Khem Raj <raj.khem@gmail.com>
# Copyright (C) 2018-2019 Anton Kikin <a.kikin@tano-systems.com>
#
PR = "tano2"

DESCRIPTION = "OpenWrt tiny signify replacement"
HOMEPAGE = "https://git.openwrt.org/?p=project/usign.git;a=summary"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://main.c;beginline=1;endline=17;md5=a51760fc5328fc7e0e27a5af562c6cfa"
SECTION = "base"

SRC_URI = "git://${GIT_OPENWRT_ORG}/project/usign.git;branch=master \
          "

# 23.10.2016
# README: replace unicode character
SRCREV = "3e6648b1356e54bcee351b8f5dbfacc6ee9dab53"

S = "${WORKDIR}/git"

inherit cmake pkgconfig

EXTRA_OECMAKE += "${EXTRA_OECONF}"

# avoids build breaks when using no-static-libs.inc
DISABLE_STATIC = ""

PACKAGECONFIG ??= "ubox"

PACKAGECONFIG[ubox] = "-DUSE_LIBUBOX=ON,,libubox,"

