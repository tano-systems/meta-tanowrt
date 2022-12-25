#
# SPDX-License-Identifier: MIT
# Copyright (c) 2020 Tano Systems LLC. All rights reserved.
#
PR:append:am335x-icev2 = ".ti0"
PR:append:am335x-bbb = ".ti0"
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

install_inittab() {
	install -d ${D}${sysconfdir}
	install -m 0644 ${WORKDIR}/inittab ${D}${sysconfdir}/inittab
}

do_install:am335x-icev2() {
	install_inittab
}

do_install:am335x-bbb() {
	install_inittab
}
