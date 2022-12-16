#
# SPDX-License-Identifier: MIT
#
# Copyright (c) 2022 Tano Systems LLC. All rights reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
PR:append:boardcon-em356x = ".rk0"
PR:append:rock-pi-s = ".rk0"

COMPATIBLE_MACHINE:append = "|boardcon-em356x|rock-pi-s"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/:"

SRC_URI += "\
	file://swupdate_platform.in \
"

do_install:append() {
	install -d ${D}${nonarch_libdir}/swupdate
	install -m 0644 ${WORKDIR}/swupdate_platform ${D}${nonarch_libdir}/swupdate/
}
