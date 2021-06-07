#
# SPDX-License-Identifier: MIT
#
# Copyright (c) 2021 Tano Systems LLC. All rights reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
PR_append_mitx = ".baikal0"

COMPATIBLE_MACHINE_append = "|mitx"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/:"

SRC_URI += " \
	file://preinit/50_swupdate_make_dev_symlinks \
"

do_install_append() {
	# Install preinit scripts
	install -d ${D}${base_libdir}/preinit
	install -m 0644 ${WORKDIR}/preinit/50_swupdate_make_dev_symlinks ${D}${base_libdir}/preinit/
}
