#
# SPDX-License-Identifier: MIT
# Copyright (c) 2020 Tano Systems LLC. All rights reserved.
#
PR:append:ti33x = ".ti2"
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/files:"

RRECOMMENDS:${PN}:remove = "eudev-hwdb"

do_install:append:ti33x() {
	rm -f ${D}${sysconfdir}/udev/rules.d/firmware.rules
}
