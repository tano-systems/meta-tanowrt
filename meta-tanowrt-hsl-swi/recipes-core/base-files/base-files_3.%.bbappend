#
# SPDX-License-Identifier: MIT
# Copyright (c) 2020 Tano Systems LLC. All rights reserved.
#
PR:append = ".swi0"
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI:append = "\
	file://sysctl.d/40-swi.conf \
	file://preinit/81_setup_iface_mac \
	file://eth0_address \
"

do_install:append () {
	install -d ${D}${sysconfdir}/sysctl.d
	install -m 0644 ${WORKDIR}/sysctl.d/40-swi.conf ${D}${sysconfdir}/sysctl.d/
	install -m 0644 ${WORKDIR}/eth0_address ${D}${sysconfdir}/

	install -d ${D}${TANOWRT_PATH_PREINIT}
	install -m 0644 ${WORKDIR}/preinit/81_setup_iface_mac ${D}${TANOWRT_PATH_PREINIT}/
}

CONFFILES:${PN}:append = "\
	${sysconfdir}/eth0_address \
"
