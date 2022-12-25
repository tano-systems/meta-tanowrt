#
# SPDX-License-Identifier: MIT
# Copyright (c) 2018-2022 Tano Systems LLC. All rights reserved.
#
PR:append = ".tano0"
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

PACKAGECONFIG:remove = "delayacct sensors setuid"

PACKAGECONFIG[setuid] = ""
PACKAGECONFIG[sensors] = "--enable-sensors,--disable-sensors,lmsensors,lmsensors-libsensors"

SRC_URI += "\
	file://0002-Linux-Add-process-scheduling-policy-name-column-SCHE.patch \
"

do_install:append() {
	rm -rf ${D}${datadir}
}
