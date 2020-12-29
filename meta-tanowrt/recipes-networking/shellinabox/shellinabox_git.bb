#
# SPDX-License-Identifier: MIT
#
# Shell In A Box implements a web server that 
# can export arbitrary command line tools to 
# a web based terminal emulator. This emulator 
# is accessible to any JavaScript and CSS 
# enabled web browser and does not require any additional browser plugins.
# https://github.com/shellinabox/shellinabox
#
# This file Copyright (c) 2018 Tano Systems LLC. All rights reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
PR = "tano2"
PV = "2.21+git${SRCPV}"

DESCRIPTION = "Shell In A Box implements a web server that \
can export arbitrary command line tools to \
a web based terminal emulator."

HOMEPAGE = "https://github.com/shellinabox/shellinabox"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=a193d25fdef283ddce530f6d67852fa5"

inherit autotools-brokensep
inherit pkgconfig

DEPENDS += "zlib"

GIT_PROTOCOL = "https"
SRC_URI = "git://github.com/shellinabox/shellinabox.git;protocol=${GIT_PROTOCOL}"
SRCREV = "4f0ecc31ac6f985e0dd3f5a52cbfc0e9251f6361"

# Files
SRC_URI += "\
	file://shellinabox.init \
	file://shellinabox.config \
	file://shellinabox.css \
	file://shellinabox.keep \
"

# Patches
SRC_URI += "\
	file://0001-Add-cert-file-option.patch \
"

PACKAGECONFIG ??= "ssl"
PACKAGECONFIG[ssl] = "--enable-ssl,--disable-ssl,openssl"

RDEPENDS_${PN} += "\
	${@bb.utils.contains('PACKAGECONFIG', 'ssl', 'openssl-bin', '', d)} \
"

EXTRA_OECONF += " --disable-runtime-loading"

inherit tanowrt-services

TANOWRT_SERVICE_PACKAGES = "shellinabox"
TANOWRT_SERVICE_SCRIPTS_shellinabox += "shellinabox"
TANOWRT_SERVICE_STATE_shellinabox-shellinabox ?= "enabled"

do_install_append() {
	install -d -m 0755 ${D}${sysconfdir}/config
	install -d -m 0755 ${D}${sysconfdir}/init.d
	install -d -m 0755 ${D}${sysconfdir}/shellinabox
	install -d -m 0755 ${D}${sysconfdir}/shellinabox/ssl

	install -m 0755 ${WORKDIR}/shellinabox.init ${D}${sysconfdir}/init.d/shellinabox
	install -m 0644 ${WORKDIR}/shellinabox.config ${D}${sysconfdir}/config/shellinabox

	install -m 0644 ${B}/shellinabox/black-on-white.css ${D}${sysconfdir}/shellinabox/
	install -m 0644 ${B}/shellinabox/white-on-black.css ${D}${sysconfdir}/shellinabox/
	install -m 0644 ${WORKDIR}/shellinabox.css ${D}${sysconfdir}/shellinabox/

	install -d -m 0755 ${D}${base_libdir}/upgrade/keep.d
	install -m 0644 ${WORKDIR}/shellinabox.keep ${D}${base_libdir}/upgrade/keep.d/shellinabox
}

FILES_${PN} += "${base_libdir}/upgrade/keep.d"

S = "${WORKDIR}/git"

CONFFILES_${PN}_append = "\
	${sysconfdir}/config/shellinabox \
	${sysconfdir}/etc/shellinabox/ssl/certificate.pem \
"
