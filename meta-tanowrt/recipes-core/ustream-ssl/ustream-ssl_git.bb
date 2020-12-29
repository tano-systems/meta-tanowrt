#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Copyright (C) 2018-2020 Anton Kikin <a.kikin@tano-systems.com>
#

PR = "tano7"
SUMMARY = "Small stream SSL library"
HOMEPAGE = "http://git.openwrt.org/?p=project/ustream-ssl.git;a=summary"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://ustream-ssl.h;beginline=1;endline=17;md5=f633104677420342f142ab4835e04031"
SECTION = "base"
DEPENDS = "libubox openssl"

SRC_URI = "git://${GIT_OPENWRT_ORG}/project/ustream-ssl.git \
          "

# 08.12.2020
# Add initial GitLab CI support
SRCREV = "68d09243b6fd4473004b27ff6483352e76e6af1a"

S = "${WORKDIR}/git"

inherit cmake pkgconfig

do_install_append() {
	install -d ${D}${includedir}/libubox
	install -m 0644 ${S}/*.h ${D}${includedir}/libubox

	install -dm 0755 ${D}${base_libdir}
	mv ${D}${libdir}/libustream-ssl.so ${D}${base_libdir}/libustream-ssl.so
	rmdir --ignore-fail-on-non-empty ${D}${libdir}
}

FILES_${PN} += "${base_libdir}/*"
FILES_SOLIBSDEV = ""

RDEPENDS_${PN} += "libcrypto libssl"
