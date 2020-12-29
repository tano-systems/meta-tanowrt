#
# SPDX-License-Identifier: MIT
# This file Copyright (C) 2020 Anton Kikin <a.kikin@tano-systems.com>
#
# Console-based network traffic monitor
#
PV = "2.6"
PR = "tano0"

DESCRIPTION = "Console-based network traffic monitor"
SECTION = "net"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

DEPENDS += "sqlite3 gd"

SRC_URI = "https://humdi.net/vnstat/vnstat-${PV}.tar.gz"
SRC_URI[md5sum] = "fe2928a81243cc8a532a357f97221736"
SRC_URI[sha256sum] = "89276e0a7281943edb554b874078278ad947dc312938a2451e03eb80679f7ff7"

# Files
SRC_URI += "\
	file://vnstat.init \
	file://vnstat.config \
"

S = "${WORKDIR}/vnstat-${PV}"

inherit autotools-brokensep
inherit pkgconfig
inherit tanowrt-services

TANOWRT_SERVICE_PACKAGES = "vnstat2"
TANOWRT_SERVICE_SCRIPTS_vnstat2 += "vnstat"
TANOWRT_SERVICE_STATE_vnstat2-vnstat ?= "enabled"

EXTRA_OECONF = " \
	--disable-extra-paths \
"

do_install_append() {
	install -dm 0755 ${D}${sysconfdir}/config
	install -dm 0755 ${D}${sysconfdir}/init.d

	install -m 0644 ${WORKDIR}/vnstat.config ${D}${sysconfdir}/config/vnstat
	install -m 0755 ${WORKDIR}/vnstat.init ${D}${sysconfdir}/init.d/vnstat
}

CONFFILES_${PN}_append = "\
	${sysconfdir}/vnstat.conf \
	${sysconfdir}/config/vnstat \
"
