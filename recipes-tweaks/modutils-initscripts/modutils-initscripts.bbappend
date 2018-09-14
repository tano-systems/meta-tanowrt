# Copyright (C) 2018 Daniel Dickinson <cshored@thecshore.com>
# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>

# Released under the MIT license (see COPYING.MIT for the terms)

PR_append = ".tano1"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI += "\
	file://modutils.init \
	"

inherit openwrt openwrt-services

OPENWRT_SERVICE_PACKAGES = "modutils-initscripts"
OPENWRT_SERVICE_SCRIPTS_modutils-initscripts += "modutils"
OPENWRT_SERVICE_STATE_modutils-initscripts-modutils ?= "enabled"

do_install_append() {
	install -dm 0755 ${D}/etc/init.d
	install -Dm 0755 ${S}/modutils.init ${D}/etc/init.d/modutils
}
