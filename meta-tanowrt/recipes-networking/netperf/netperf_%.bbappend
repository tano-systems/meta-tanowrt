#
# SPDX-License-Identifier: MIT
# Copyright (c) 2020 Tano Systems LLC. All rights reserved.
#
PR_append = ".tano0"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI += "file://netserver.init"

inherit tanowrt-services

TANOWRT_SERVICE_PACKAGES = "netperf"
TANOWRT_SERVICE_SCRIPTS_netperf += "netserver"
TANOWRT_SERVICE_STATE_netperf-netserver ?= "disabled"

do_install_append() {
	# Remove sysvinit script
	rm -f ${D}${sysconfdir}/init.d/netperf

	# Install procd init script
	install -m 0755 ${WORKDIR}/netserver.init ${D}${sysconfdir}/init.d/netserver
}
