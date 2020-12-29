#
# SPDX-License-Identifier: MIT
# Copyright (c) 2020 Tano Systems LLC. All rights reserved.
#
PR_append = ".rpi1"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

RDEPENDS_${PN}_append_rpi3 = " util-linux-partx"

SRC_URI_append_rpi3 = "\
	file://rootfs/etc/diag.sh \
	file://rootfs/etc/board.d/02_network \
	file://rootfs/lib/preinit/05_set_preinit_iface_brcm2708 \
	file://rootfs/lib/preinit/79_move_config \
"

do_install_append_rpi3() {
	install -d ${D}${sysconfdir}
	install -m 0755 ${WORKDIR}/rootfs/etc/diag.sh ${D}${sysconfdir}/

	install -d ${D}${sysconfdir}/board.d
	install -m 0755 ${WORKDIR}/rootfs/etc/board.d/02_network ${D}${sysconfdir}/board.d/

	install -d ${D}${base_libdir}/preinit
	install -m 0755 ${WORKDIR}/rootfs/lib/preinit/05_set_preinit_iface_brcm2708 ${D}${base_libdir}/preinit/
	install -m 0755 ${WORKDIR}/rootfs/lib/preinit/79_move_config ${D}${base_libdir}/preinit/
}
