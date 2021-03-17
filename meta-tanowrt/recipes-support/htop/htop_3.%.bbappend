#
# SPDX-License-Identifier: MIT
# Copyright (c) 2018-2021 Tano Systems LLC. All rights reserved.
#
PR_append = ".tano1"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

PV = "3.0.6-dev+git${SRCPV}"

SRCREV = "d9f2eacbc5b3fccf63b24944ce9a30d762baea3c"

PACKAGECONFIG_remove = "delayacct sensors setuid"

PACKAGECONFIG[setuid] = ""
PACKAGECONFIG[sensors] = "--enable-sensors,--disable-sensors,lmsensors,lmsensors-libsensors"

do_install_append() {
	rm -rf ${D}${datadir}
}
