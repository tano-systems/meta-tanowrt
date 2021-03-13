#
# SPDX-License-Identifier: MIT
# Copyright (c) 2021 Tano Systems LLC. All rights reserved.
#

PR_append = ".tano2"
SRCREV = "e663439e3d39a4cdbddb7e818245a99c485fa965"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/files:${THISDIR}/${PN}/patches:"

SRC_URI += "\
	file://0001-libuboot_read_config-Return-EBUSY-if-opening-the-env.patch \
"

do_install_append() {
	#
	# For compatibility with legacy applications
	# searching fw_printenv/fw_setenv in /sbin
	#
	install -d ${D}${base_sbindir}
	ln -sf ${bindir}/fw_printenv ${D}${base_sbindir}/fw_printenv
	ln -sf ${bindir}/fw_setenv ${D}${base_sbindir}/fw_setenv
}
