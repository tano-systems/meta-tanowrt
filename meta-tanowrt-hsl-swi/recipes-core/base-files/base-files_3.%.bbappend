#
# SPDX-License-Identifier: MIT
# Copyright (c) 2020 Tano Systems LLC. All rights reserved.
#
PR_append = ".swi0"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI_append = "\
	file://sysctl.d/40-swi.conf \
	file://preinit/81_setup_iface_mac \
	file://eth0_address \
"

do_install_append () {
	install -d ${D}${sysconfdir}/sysctl.d
	install -m 0644 ${WORKDIR}/sysctl.d/40-swi.conf ${D}${sysconfdir}/sysctl.d/
	install -m 0644 ${WORKDIR}/eth0_address ${D}${sysconfdir}/

	install -d ${D}${base_libdir}/preinit
	install -m 0644 ${WORKDIR}/preinit/81_setup_iface_mac ${D}${base_libdir}/preinit/
}

CONFFILES_${PN}_append = "\
	${sysconfdir}/eth0_address \
"
