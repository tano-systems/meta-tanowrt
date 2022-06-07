#
# SPDX-License-Identifier: MIT
# Copyright (c) 2019-2020, 2022 Tano Systems LLC. All rights reserved.
#
PR_append = ".tano3"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

inherit tanowrt-services
inherit uci-config

PACKAGECONFIG ??= ""
PACKAGECONFIG[rngtest] = ",,"

HWRNG_DEV ?= "/dev/hwrng"
HWRNG_ENABLE ?= "1"

SRC_URI += "\
	file://rngd.init \
	file://rngd.config \
"

do_install_append() {
	rm -f ${D}${sysconfdir}/init.d/rng-tools
	rm -rf ${D}${sysconfdir}/default

	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/rngd.init ${D}${sysconfdir}/init.d/rngd

	install -d ${D}${sysconfdir}/config
	install -m 0644 ${WORKDIR}/rngd.config ${D}${sysconfdir}/config/rngd

	if [ "${@bb.utils.contains('PACKAGECONFIG', 'rngtest', '1', '0', d)}" = "0" ]; then
		rm -rf ${D}${bindir}
	fi
}

FILES_${PN} += "${bindir}"
CONFFILES_${PN} = "${sysconfdir}/config/rngd"

TANOWRT_SERVICE_PACKAGES = "rng-tools"
TANOWRT_SERVICE_SCRIPTS_rng-tools += "rngd"
TANOWRT_SERVICE_STATE_rng-tools-rngd ?= "enabled"

do_uci_config_append() {
	${UCI} set rngd.@rngd[0].in_device="${HWRNG_DEV}"
	${UCI} set rngd.@rngd[0].enabled="${HWRNG_ENABLE}"
	${UCI} commit rngd
}
