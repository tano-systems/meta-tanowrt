# Copyright (c) 2013-2017 LG Electronics, Inc.
# Copyright (c) 2018 Anton Kikin <a.kikin@tano-systems.com>

SUMMARY = "Booting sequence and CPU,I/O usage monitor"
DESCRIPTION = "Monitors where the system spends its time at start, creating a graph of all processes, disk utilization, and wait time."
AUTHOR = "Wonhong Kwon <wonhong.kwon@lge.com>"
HOMEPAGE = "https://github.com/xrmx/bootchart"
LICENSE = "GPL-3.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=44ac4678311254db62edf8fd39cb8124"

PR = "tano4"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

inherit autotools-brokensep
inherit systemd
inherit python3native

SYSTEMD_SERVICE_${PN} = "bootchart2.service bootchart2-done.service bootchart2-done.timer"

RDEPENDS_${PN} += " lsb-release "
RCONFLICTS_${PN} = "bootchart"

SRCREV = "42509aa0c9c20baa631062496b281f67b31abbd0"
PV = "0.14.8+git${SRCPV}"

SRC_URI = "git://github.com/xrmx/bootchart"

# Patches
SRC_URI += "\
    file://fix-wrong-ppid-tracking-bug.patch \
    file://update-cmds-of-initctls-to-upstart-event-name.patch \
    file://change-a-color-of-upstart-event-name.patch \
    file://fix-bootstartd-index-out-of-bounds.patch \
    file://0001-Add-overlayfs-support.patch \
"

# Files
SRC_URI += "\
    file://bootchartd \
    file://bootchartd.conf \
"

S = "${WORKDIR}/git"

FILES_${PN} = " \
	${sysconfdir} \
	${base_libdir} \
	${base_sbindir} \
"

FILES_${PN}-doc += "${datadir}"

do_install () {
    export PYTHON_DIR=python3.5
    export PY_LIBDIR="${libdir}/${PYTHON_DIR}"
    export BINDIR="${bindir}"
    export DESTDIR="${D}"
    export LIBDIR="${base_libdir}"

    oe_runmake install

    # Use python 3 instead of python 2
    sed -i -e '1s,#!.*python.*,#!${bindir}/python3,' ${D}${bindir}/pybootchartgui

    install -dm 0755 ${D}${base_sbindir}
    install -dm 0755 ${D}${sysconfdir}
    install -m 0755 ${WORKDIR}/bootchartd      ${D}${base_sbindir}/bootchartd
    install -m 0644 ${WORKDIR}/bootchartd.conf ${D}${sysconfdir}/bootchartd.conf
}

PACKAGES += "${PN}-pybootchartgui"

RDEPENDS_${PN}-pybootchartgui = "python3-pycairo python3-compression python3-image python3-textutils python3-shell python3-codecs python3-misc"
FILES_${PN}-pybootchartgui += "${PYTHON_SITEPACKAGES_DIR}/pybootchartgui ${bindir}/pybootchartgui"
