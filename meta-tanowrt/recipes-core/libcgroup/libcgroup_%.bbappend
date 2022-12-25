#
# SPDX-License-Identifier: MIT
#
# This file Copyright (c) 2019, 2022 Tano Systems. All Rights Reserved.
# Author: Anton Kikin <a.kikin@tano-systems.com>
#
PR:append = ".tano0"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

PACKAGES += "${PN}-procd"

RRECOMMENDS:${PN} += "${PN}-procd"
RDEPENDS:${PN}-procd += "${PN}"

inherit tanowrt-services

TANOWRT_SERVICE_PACKAGES = "libcgroup-procd"
TANOWRT_SERVICE_SCRIPTS_libcgroup-procd += "cgroups"
TANOWRT_SERVICE_STATE_libcgroup-procd-cgroups ?= "enabled"

SRC_URI += "\
	file://cgroups.config \
	file://cgroups.init \
"

do_install:append() {
	install -dm 0755 ${D}${sysconfdir}/init.d
	install -dm 0755 ${D}${sysconfdir}/config
#	install -dm 0755 ${D}${sysconfdir}/config/cgroups.d

	install -m 0755 ${WORKDIR}/cgroups.init ${D}${sysconfdir}/init.d/cgroups
	install -m 0644 ${WORKDIR}/cgroups.config ${D}${sysconfdir}/config/cgroups
}

FILES:${PN} = "\
	${libdir} \
	${bindir} \
	${sbindir} \
"

FILES:${PN}-procd = "\
	${sysconfdir}/init.d/cgroups \
	${sysconfdir}/config/cgroups \
"

CONFFILES:${PN}-procd:append = "\
	${sysconfdir}/config/cgroups \
"
