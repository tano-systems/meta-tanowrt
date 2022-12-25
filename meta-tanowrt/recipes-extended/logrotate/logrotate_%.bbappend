#
# SPDX-License-Identifier: MIT
# Copyright (c) 2021 Tano Systems LLC. All rights reserved.
#
PR:append = ".tano0"
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI += "\
	file://logrotate.conf \
	file://logrotate.cron \
"

do_install:append() {
	rm -rf ${D}/var
	rm  -f ${D}${sysconfdir}/logrotate.d/wtmp
	rm  -f ${D}${sysconfdir}/logrotate.d/btmp

	install -d ${D}${sysconfdir}
	install -m 0644 ${WORKDIR}/logrotate.conf \
		${D}${sysconfdir}/logrotate.conf

	install -d ${D}${sysconfdir}/cron.daily
	install -m 0755 ${WORKDIR}/logrotate.cron \
		${D}${sysconfdir}/cron.daily/logrotate
}
