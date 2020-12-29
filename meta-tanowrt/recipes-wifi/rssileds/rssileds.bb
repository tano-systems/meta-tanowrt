#
# SPDX-License-Identifier: MIT
#
# RSSI real-time LED indicator bitbake recipe
# This file Copyright (C) 2020 Anton Kikin <a.kikin@tano-systems.com>
#
PR = "tano1"

DESCRIPTION = "A small process written in C to update the signal-strength indicator LEDs"
SUMMARY = "RSSI real-time LED indicator"
SECTION = "net"
DEPENDS += "libiwinfo"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "\
	file://rssileds.c;beginline=1;endline=25;md5=9d27f096c7c968199a2e9488c711bcb7 \
"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/files:${THISDIR}/${PN}/src:"

SRC_URI = "\
	file://rssileds.c \
	file://rssileds.init \
"

S = "${WORKDIR}"

inherit tanowrt-services

TANOWRT_SERVICE_PACKAGES = "rssileds"
TANOWRT_SERVICE_SCRIPTS_rssileds += "rssileds"
TANOWRT_SERVICE_STATE_rssileds-rssileds ?= "enabled"

do_compile() {
	${CC} ${CFLAGS} ${LDFLAGS} -Wall -c -liwinfo -o ${S}/rssileds ${S}/rssileds.c
}

do_install() {
	install -d ${D}${sysconfdir}/init.d
	install -d ${D}${sbindir}

	install -m 0755 ${WORKDIR}/rssileds.init ${D}${sysconfdir}/init.d/rssileds
	install -m 0755 ${WORKDIR}/rssileds ${D}${sbindir}/rssileds
}
