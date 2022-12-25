#
# SPDX-License-Identifier: MIT
# Copyright (c) 2020 Tano Systems LLC. All rights reserved.
#

PR:append = ".tano0"
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI += "file://sensors.upgrade"

do_install:append() {
	install -d ${D}/lib/upgrade/keep.d
	install -m 0644 ${WORKDIR}/sensors.upgrade ${D}/lib/upgrade/keep.d/sensors
}

FILES:${PN}-libsensors += "/lib/upgrade/keep.d/sensors"
CONFFILES:${PN}-libsensors = "${sysconfdir}/sensors.d/"
