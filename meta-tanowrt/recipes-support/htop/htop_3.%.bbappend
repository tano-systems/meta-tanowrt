#
# SPDX-License-Identifier: MIT
# Copyright (c) 2018-2023 Tano Systems LLC. All rights reserved.
#
PR:append = ".tano0"
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

PV = "3.2.2-dev+git${SRCPV}"
PR:append = ".tano0"
SRCREV = "e3481a9846ef01bb27c169b71eafeee704e10c68"

PACKAGECONFIG:remove = "delayacct sensors setuid"

PACKAGECONFIG[setuid] = ""
PACKAGECONFIG[sensors] = "--enable-sensors,--disable-sensors,lmsensors,lmsensors-libsensors"

do_install:append() {
	rm -rf ${D}${datadir}
}
