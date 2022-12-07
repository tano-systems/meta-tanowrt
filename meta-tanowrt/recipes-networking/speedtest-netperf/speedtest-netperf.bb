#
# SPDX-License-Identifier: MIT
#
# This file Copyright (C) 2020 Tano Systems LLC
# Author: Anton Kikin <a.kikin@tano-systems.com>
#
# Script to measure the performance of your network and router
#
PV = "1.0.0"
PR = "tano0"

DESCRIPTION = "Script to measure the performance of your network and router"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=801f80980d171dd6425610833a22dbe6"
SECTION = "net"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

inherit allarch

RDEPENDS:${PN} += "netperf"

SRC_URI = "\
	file://speedtest-netperf.sh \
	file://LICENSE \
"

S = "${WORKDIR}"

do_compile[noexec] = "1"
do_configure[noexec] = "1"

do_install() {
	install -d ${D}${bindir}
	install ${WORKDIR}/speedtest-netperf.sh ${D}${bindir}/speedtest-netperf.sh
}

FILES:${PN} += "${bindir}/"
