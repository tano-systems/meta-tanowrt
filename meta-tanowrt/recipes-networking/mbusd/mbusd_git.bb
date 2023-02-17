#
# SPDX-License-Identifier: MIT
#
# This file Copyright (C) 2019, 2022-2023 Tano Systems LLC
# Author: Anton Kikin <a.kikin@tano-systems.com>
#

PR = "tano0"
PV = "0.5.1+git${SRCPV}"

SUMMARY = "Open-source Modbus TCP to Modbus RTU (RS-232/485) gateway"
DESCRIPTION = "mbusd is open-source Modbus TCP to Modbus RTU (RS-232/485) gateway. \
It presents a network of RTU slaves as single TCP slave."
HOMEPAGE = "https://github.com/3cky/mbusd"
SECTION = "base"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=75746e54fd84b66abbbba18c1b21b63b"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI = "\
	git://github.com/3cky/mbusd;branch=master;protocol=https \
	file://mbusd.init \
	file://mbusd.config \
"

SRCREV = "4e5c7e3781e3e7a99a617e41aa54a4ac5df5fd99"

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
