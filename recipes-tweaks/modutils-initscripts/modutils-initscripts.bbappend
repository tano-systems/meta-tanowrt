# Copyright (C) 2018 Daniel Dickinson <cshored@thecshore.com>
# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>

# Released under the MIT license (see COPYING.MIT for the terms)

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI += "\
	file://modutils.init \
	"

inherit openwrt openwrt-services

OPENWRT_SERVICE_PACKAGES = "${PN}"
OPENWRT_SERVICE_SCRIPTS_${PN} = "modutils"
OPENWRT_SERVICE_STATE_${PN}-modutils = "enabled"

do_install_append() {
	install -dm 0755 ${D}/etc/init.d
	install -Dm 0755 ${S}/modutils.init ${D}/etc/init.d/modutils
}
