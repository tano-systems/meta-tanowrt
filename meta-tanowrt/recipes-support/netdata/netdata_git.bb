#
# SPDX-License-Identifier: MIT
#
# Distributed, real-time, performance and health monitoring for systems and applications.
#
# This file Copyright (c) 2019, Anton Kikin <a.kikin@tano-systems.com>
#
HOMEPAGE = "https://github.com/netdata/netdata/"
SUMMARY = "Real-time performance monitoring"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://LICENSE;md5=fc9b848046ef54b5eaee6071947abd24"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

DEPENDS += "zlib libmnl"
RDEPENDS_${PN} = "bash zlib"

SRC_URI = "git://github.com/netdata/netdata.git;protocol=https;branch=master"
SRCREV = "fc8e3bbd451cff1b9dbfee8f213c6e0a5813b5f4"
PV = "1.15.0+git${SRCPV}"
PR = "tano3"

S = "${WORKDIR}/git"

# Patches
SRC_URI += "\
	file://001-disable-plugins-by-default.patch \
	file://002-force-python3.patch \
"

# Files
SRC_URI += "\
	file://netdata.init \
	file://netdata.conf \
"

S = "${WORKDIR}/git"

DEPENDS += "zlib util-linux"

inherit pkgconfig autotools-brokensep useradd

LDFLAGS += "-pthread"

# User specific
USERADD_PACKAGES = "${PN}"
GROUPADD_PARAM_${PN} = "--system netdata"

inherit tanowrt-services

TANOWRT_SERVICE_PACKAGES = "netdata"
TANOWRT_SERVICE_SCRIPTS_netdata += "netdata"
TANOWRT_SERVICE_STATE_netdata-netdata ?= "enabled"

do_install_append() {
	install -dm 0755 ${D}${sysconfdir}/netdata/custom-plugins.d

	# Install config
	install -dm 0755 ${D}${sysconfdir}/netdata
	install -m 0644 ${WORKDIR}/netdata.conf ${D}${sysconfdir}/netdata/netdata.conf

	# Install procd init script
	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/netdata.init ${D}${sysconfdir}/init.d/netdata

	# Remove python modules
	rm -rf ${D}${libexecdir}/netdata/plugins.d/python.d.plugin
	rm -rf ${D}${libexecdir}/netdata/python.d
	rm -rf ${D}${libdir}/netdata/conf.d/python.d
	rm -f  ${D}${libdir}/netdata/conf.d/python.d.conf

	# Remove node.js modules
	rm -rf ${D}${libexecdir}/netdata/plugins.d/node.d.plugin
	rm -rf ${D}${libexecdir}/netdata/node.d
	rm -f  ${D}${libdir}/netdata/conf.d/node.d.conf

	# Remove demo pages
	rm -f ${D}${datadir}/netdata/web/demo*html
	rm -f ${D}${datadir}/netdata/web/dashboard.html
	rm -f ${D}${datadir}/netdata/web/infographic.html
	rm -f ${D}${datadir}/netdata/web/tv.html
	rm -f ${D}${datadir}/netdata/web/fonts/*.svg
	rm -f ${D}${datadir}/netdata/web/fonts/*.ttf
	rm -f ${D}${datadir}/netdata/web/fonts/*.woff
	rm -f ${D}${datadir}/netdata/web/images/*.png
	rm -f ${D}${datadir}/netdata/web/images/*.gif
	rm -f ${D}${datadir}/netdata/web/images/*.ico

	# Remove /var
	rm -rf ${D}/var
}

FILES_${PN}-dbg += "${libexecdir}/netdata/plugins.d/.debug"

CONFFILES_${PN}_append = "\
	${sysconfdir}/netdata \
"
