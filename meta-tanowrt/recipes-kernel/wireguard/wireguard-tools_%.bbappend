#
# SPDX-License-Identifier: MIT
# Copyright (c) 2020, 2022 Tano Systems LLC. All rights reserved.
#
PR:append = ".tano6"
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI += "\
	file://wireguard_watchdog \
	file://wireguard.sh \
"

do_configure[depends] += "virtual/kernel:do_shared_workdir"

do_install:append () {
	install -dm 0755 ${D}${bindir}
	install -m 0755 ${WORKDIR}/wireguard_watchdog ${D}${bindir}/

	install -dm 0755 ${D}${nonarch_base_libdir}/netifd/proto
	install -m 0755 ${WORKDIR}/wireguard.sh ${D}${nonarch_base_libdir}/netifd/proto/
}

FILES:${PN} += " \
	${sysconfdir} \
	${bindir} \
	${nonarch_base_libdir} \
"

DEPENDS += "${@oe.utils.conditional('TANOWRT_WIREGUARD_IN_KERNEL', '1', '', 'wireguard-module', d)}"
RDEPENDS:${PN} += "ubus jsonpath kernel-module-wireguard"
RDEPENDS:${PN}:remove = "wireguard-module"
