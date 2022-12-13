#
# SPDX-License-Identifier: MIT
# Copyright (c) 2020 Tano Systems LLC. All rights reserved.
#
PR:append = ".rpi1"
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

RDEPENDS:${PN}:append:rpi3 = " util-linux-partx"

SRC_URI:append:rpi3 = "\
	file://rootfs/etc/diag.sh \
	file://rootfs/etc/board.d/02_network \
	file://rootfs/lib/preinit/05_set_preinit_iface_brcm2708 \
	file://rootfs/lib/preinit/79_move_config \
"

do_install:append:rpi3() {
	install -d ${D}${sysconfdir}
	install -m 0755 ${WORKDIR}/rootfs/etc/diag.sh ${D}${sysconfdir}/

	install -d ${D}${sysconfdir}/board.d
	install -m 0755 ${WORKDIR}/rootfs/etc/board.d/02_network ${D}${sysconfdir}/board.d/

	install -d ${D}${TANOWRT_PATH_PREINIT}
	install -m 0755 ${WORKDIR}/rootfs/lib/preinit/05_set_preinit_iface_brcm2708 ${D}${TANOWRT_PATH_PREINIT}/
	install -m 0755 ${WORKDIR}/rootfs/lib/preinit/79_move_config ${D}${TANOWRT_PATH_PREINIT}/
}
