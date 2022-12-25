#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2018 Daniel Dickinson <cshored@thecshore.com>
# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>
#

PR:append = ".tano2"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI += "\
	file://modutils.init \
	"

inherit tanowrt-services

TANOWRT_SERVICE_PACKAGES = "modutils-initscripts"
TANOWRT_SERVICE_SCRIPTS_modutils-initscripts += "modutils"
TANOWRT_SERVICE_STATE_modutils-initscripts-modutils ?= "enabled"

do_install:append() {
	install -dm 0755 ${D}/etc/init.d
	install -Dm 0755 ${S}/modutils.init ${D}/etc/init.d/modutils
}
