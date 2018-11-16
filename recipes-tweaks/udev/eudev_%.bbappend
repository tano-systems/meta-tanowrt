# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>

# Released under the MIT license (see COPYING.MIT for the terms)

PR_append = ".tano6"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI += "file://udev.procd"

inherit openwrt-services
inherit useradd

RRECOMMENDS_${PN}_remove = "eudev-hwdb"

USERADD_PACKAGES = "${PN}"
GROUPADD_PARAM_${PN} = "\
    -f -r tty; \
    -f -r dialout; \
    -f -r kmem; \
    -f -r input; \
    -f -r video; \
    -f -r lp; \
    -f -r disk; \
    -f -r cdrom; \
    -f -r tape; \
    -f -r floppy"

OPENWRT_SERVICE_PACKAGES = "eudev"
OPENWRT_SERVICE_SCRIPTS_eudev += "udev"
OPENWRT_SERVICE_STATE_eudev-udev ?= "enabled"

do_install_append() {
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/udev.procd ${D}${sysconfdir}/init.d/udev

    # Remove sysvinit script
    rm -f ${D}/${sysconfdir}/init.d/udev-cache
}

