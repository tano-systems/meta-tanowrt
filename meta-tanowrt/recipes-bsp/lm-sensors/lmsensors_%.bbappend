#
# SPDX-License-Identifier: MIT
# Copyright (c) 2020 Tano Systems LLC. All rights reserved.
#

PR:append = ".tano0"

do_install:append() {
	# Some applications (e.g. luci-app-statistics) expects sensors in /usr/sbin
	ln -s ${bindir}/sensors ${D}${sbindir}/sensors
}

FILES:${PN}-sensors += "${sbindir}/sensors"
CONFFILES:${PN}-libsensors = "${sysconfdir}/sensors3.conf"
