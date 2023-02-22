#
# SPDX-License-Identifier: MIT
# This file Copyright (c) 2023 Tano Systems LLC. All rights reserved.
#
SUMMARY = "IPMI FRU Information generator / editor"
DESCRIPTION = "Universal, full-featured IPMI FRU Information \
generator / editor library and command line tool, written in \
full compliance with IPMI FRU Information Storage Definition \
v1.0, rev. 1.3."
HOMEPAGE = "https://github.com/ipmitool/frugen"
SECTION = "tools"

LICENSE = "Apache-2.0 | GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://README.md;beginline=3;endline=11;md5=f637ad9cfe4cc1885e0b822fbfb8c95a"

PV = "1.3.13+git${SRCPV}"

DEPENDS += "json-c"

inherit cmake pkgconfig

SRC_URI = "git://github.com/ipmitool/frugen.git;branch=master;protocol=https"
SRCREV = "679d8dd9ad8511d3d22888a3b568f2eafceeaa11"
S = "${WORKDIR}/git"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/patches:"

# Patches
SRC_URI += "\
	file://0001-build-Fix-linking-frugen-with-shared-library.patch \
	file://0002-Fix-compilation-warnings.patch \
"

do_install() {
	install -d ${D}${bindir}
	install -m 0755 ${B}/frugen ${D}${bindir}/frugen

	install -d ${D}${includedir}
	install -m 0644 ${S}/fru.h ${D}${includedir}/

	install -d ${D}${libdir}
	install -m 0755 ${B}/libfru.so ${D}${libdir}/
}

SOLIBS = ".so"
FILES_SOLIBSDEV = ""

PACKAGE_BEFORE_PN = "frugen"
FILES:frugen = "${bindir}/frugen"
RDEPENDS:frugen += "libfru"

BBCLASSEXTEND = "native nativesdk"
