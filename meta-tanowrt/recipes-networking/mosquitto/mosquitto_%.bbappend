#
# SPDX-License-Identifier: MIT
# Copyright (c) 2020 Tano Systems LLC. All rights reserved.
#
# TanoWrt Mosquitto customization recipe
#

PR_append = ".tano0"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI += "file://mosquitto.init \
            file://mosquitto.config \
           "

PACKAGECONFIG ??= "ssl"

do_install_append() {
	install -d ${D}${sysconfdir}/config/
	install -m 0644 ${WORKDIR}/mosquitto.config ${D}${sysconfdir}/config/mosquitto

	install -d ${D}${sysconfdir}/init.d/
	install -m 0755 ${WORKDIR}/mosquitto.init ${D}${sysconfdir}/init.d/mosquitto
}

FILES_${PN} += "${sysconfdir}/config/mosquitto"
CONFFILES_${PN} += "${sysconfdir}/config/mosquitto"
