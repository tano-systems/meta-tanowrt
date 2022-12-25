#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2016 Khem Raj <raj.khem@gmail.com>
# Copyright (C) 2018-2019 Anton Kikin <a.kikin@tano-systems.com>
#

DESCRIPTION = "OpenWrt JSON parsing utility"
HOMEPAGE = "http://git.openwrt.org/?p=project/jsonpath.git;a=summary"
LICENSE = "BSD-1-Clause"
LIC_FILES_CHKSUM = "file://main.c;beginline=1;endline=15;md5=e1b007aed2273fc3ec1d16560a17aadc"
SECTION = "base"
DEPENDS = "json-c libubox"
PR = "tano2"

SRC_URI = "git://${GIT_OPENWRT_ORG}/project/jsonpath.git;name=jsonpath;branch=master \
          file://0001-sync-lemon-parser.patch \
          file://0002-Declare-ParseTrace.patch \
          file://0100-break-lemon-dependency-cycle.patch \
          "

# 04.02.2018
# implement POSIX regexp support
SRCREV_jsonpath = "c7e938d6582a436dddc938539e72dd1320625c54"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

inherit cmake pkgconfig

S = "${WORKDIR}/git"
B = "${S}"

do_install:append() {
    ln -s ${bindir}/jsonpath ${D}${bindir}/jsonfilter
}
