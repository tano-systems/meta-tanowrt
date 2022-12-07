#
# SPDX-License-Identifier: MIT
# Copyright (c) 2019-2020 Tano Systems LLC. All rights reserved.
#
DESCRIPTION = "High performance data logging and graphing system for time series data."
HOMEPAGE = "http://oss.oetiker.ch/rrdtool/"
SECTION = "utils"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=44fee82a1d2ed0676cf35478283e0aa0"

DEPENDS = "libpng zlib perl-native"

PR = "tano2"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI = "git://github.com/tano-systems/rrdtool-1.x.git;protocol=https;destsuffix=${BPN}-${PV};branch=master"
SRCREV = "40ff39e03e1f22d07d6a026f7747a42fcfe40bb0"
S = "${WORKDIR}/${BPN}-${PV}"

# Patches
SRC_URI += "\
	file://001-no_ordering_cd_joke.patch \
	file://002-no_timezone.patch \
	file://020-x86-float-cast.patch \
	file://030-pod2man-stderr.patch \
	file://900-fix-compiler-warnings.patch \
"

inherit autotools

EXTRA_OECONF = "\
	--enable-shared=yes \
	--enable-static=yes \
	--with-gnu-ld \
	--enable-local-zlib \
	ac_cv_path_PERL=no \
	rd_cv_ieee_works=yes \
	shrext_cmds='.so' \
"

do_install_append() {
	rm -rf ${D}/usr/bin/trytime
	rm -rf ${D}/usr/contrib
	rm -rf ${D}/usr/doc
	rm -rf ${D}/usr/examples
	rm -rf ${D}/usr/html
}
