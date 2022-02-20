#
# SPDX-License-Identifier: MIT
#
# Copyright (c) 2021-2022 Tano Systems LLC. All rights reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
PR_append_mitx = ".baikal1"

COMPATIBLE_MACHINE_append = "|mitx"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/:"

inherit vars-expander
EXPAND_VARIABLES:append = "${TANOWRT_PARTUUID_VARS}"

SRC_URI += " \
	file://preinit/51_swupdate_gen_dev_symlinks.in \
"

do_install_append() {
	# Install preinit scripts
	install -m 0644 ${WORKDIR}/51_swupdate_gen_dev_symlinks \
	                ${D}${base_libdir}/preinit/
}
