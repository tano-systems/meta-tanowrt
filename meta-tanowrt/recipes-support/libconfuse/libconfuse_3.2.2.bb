#
# SPDX-License-Identifier: MIT
# Copyright (c) 2019-2020 Tano Systems LLC. All rights reserved.
#
DESCRIPTION = "Library for parsing configuration files."
HOMEPAGE = "http://www.nongnu.org/confuse/"
SECTION = "libs"
LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://LICENSE;md5=42fa47330d4051cd219f7d99d023de3a"

PV = "3.2.2"

SRC_URI = "git://github.com/martinh/libconfuse.git \
          "

SRCREV = "8dc469dcce4d19379caff2d2cbf68f38af034210"
S = "${WORKDIR}/git"

inherit autotools binconfig pkgconfig lib_package gettext

EXTRA_OECONF = "--enable-shared"

BBCLASSEXTEND = "native"

do_configure_prepend() {
	unset TARGET_CPPFLAGS TARGET_CFLAGS TARGET_CXXFLAGS TARGET_LDFLAGS
	( cd ${S}
	${S}/autogen.sh )
}
