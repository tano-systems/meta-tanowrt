#
# SPDX-License-Identifier: MIT
# Copyright (c) 2021 Tano Systems LLC. All rights reserved.
#

PR:append = ".baikal0"
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/files:"

PREINIT_FILES = "file://preinit/79_mount_boot"

SRC_URI:append = "\
	${PREINIT_FILES} \
"

do_install:append() {
	if [ -n "{PREINIT_FILES}" ]; then
		install -d ${D}${base_libdir}/preinit
		for f in ${PREINIT_FILES}; do
			FILEPATH="${f#file://}"
			install -m 0644 ${WORKDIR}/${FILEPATH} ${D}${TANOWRT_PATH_PREINIT}/
		done
	fi
}
