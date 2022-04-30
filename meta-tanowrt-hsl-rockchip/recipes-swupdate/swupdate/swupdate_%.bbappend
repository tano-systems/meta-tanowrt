#
# SPDX-License-Identifier: MIT
#
# Copyright (c) 2022 Tano Systems LLC. All rights reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
PR_append_boardcon-em356x = ".rk0"
PR_append_rock-pi-s = ".rk0"

COMPATIBLE_MACHINE_append = "|boardcon-em356x|rock-pi-s"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/:"

SRC_URI += "\
	file://swupdate_platform.in \
"

do_install_append() {
	install -d ${D}${nonarch_libdir}/swupdate
	install -m 0644 ${WORKDIR}/swupdate_platform ${D}${nonarch_libdir}/swupdate/
}
