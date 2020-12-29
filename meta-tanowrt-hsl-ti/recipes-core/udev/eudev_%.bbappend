#
# SPDX-License-Identifier: MIT
# Copyright (c) 2020 Tano Systems LLC. All rights reserved.
#
PR_append_ti33x = ".ti2"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/files:"

RRECOMMENDS_${PN}_remove = "eudev-hwdb"

do_install_append_ti33x() {
	rm -f ${D}${sysconfdir}/udev/rules.d/firmware.rules
}
