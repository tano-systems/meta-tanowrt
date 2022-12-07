#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
#
PR = "tano0"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

DESCRIPTION = "This library is minimum, easy-to-use, C implementation for xml file parsing."
HOMEPAGE = "http://www.libroxml.net"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://License.txt;md5=81cba52d2de829c8be3d167618e6b8b6"
SECTION = "libs"
DEPENDS = ""

SRCREV = "1bc90ac506b50a06ee613b139405ff47ecc83c0a"
SRC_URI = "git://github.com/blunderer/libroxml.git;branch=master;protocol=https \
           file://0001-remove-inline-from-read-close-buf-and-file-functions.patch \
         "

inherit cmake pkgconfig
EXTRA_OECMAKE += "-DBUILD_TESTING=OFF"
S = "${WORKDIR}/git"
