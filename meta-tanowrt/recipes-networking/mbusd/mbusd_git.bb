#
# SPDX-License-Identifier: MIT
#
# This file Copyright (C) 2019, 2022 Tano Systems LLC
# Author: Anton Kikin <a.kikin@tano-systems.com>
#

PR = "tano3"
PV = "0.4.1+git${SRCPV}"

SUMMARY = "Open-source Modbus TCP to Modbus RTU (RS-232/485) gateway"
DESCRIPTION = "mbusd is open-source Modbus TCP to Modbus RTU (RS-232/485) gateway. \
It presents a network of RTU slaves as single TCP slave."
HOMEPAGE = "https://github.com/3cky/mbusd"
SECTION = "base"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c8064f1419006cd3e5026f78f15c7a28"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI = "\
	git://github.com/3cky/mbusd;branch=master;protocol=https \
	file://mbusd.init \
	file://mbusd.config \
"

SRCREV = "88916fe82daeadeff59de03b9f1647603f67be42"

S = "${WORKDIR}/git"

inherit pkgconfig cmake tanowrt-services
TANOWRT_SERVICE_PACKAGES = "mbusd"
TANOWRT_SERVICE_SCRIPTS_mbusd += "mbusd"
TANOWRT_SERVICE_STATE_mbusd-mbusd ?= "enabled"

do_install:append() {
	# Install procd init script
	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/mbusd.init ${D}${sysconfdir}/init.d/mbusd

	# Install UCI configuration example
	install -d ${D}${sysconfdir}/config
	install -m 0644 ${WORKDIR}/mbusd.config ${D}${sysconfdir}/config/mbusd

	# Remove original example configuration
	rm -rf ${D}${sysconfdir}/mbusd
}

FILES:${PN} = "\
	${sysconfdir} \
	${bindir} \
"
