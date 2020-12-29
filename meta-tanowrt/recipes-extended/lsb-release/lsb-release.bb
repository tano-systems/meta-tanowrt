#
# SPDX-License-Identifier: MIT
# Copyright (c) 2018 Tano Systems LLC. All rights reserved.
#
SUMMARY = "lsb_release script implementation for OpenWrt"
SECTION = "console/utils"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://README;md5=12da544b1a3a5a1795a21160b49471cf"
PV = "1.4"
PR = "tano0"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI = "\
	file://lsb_release \
	file://README \
"

do_compile[noexec] = "1"
do_configure[noexec] = "1"

S = "${WORKDIR}"

do_install() {
	install -dm 0755 ${D}${bindir}
	install -m 0755 ${WORKDIR}/lsb_release ${D}${bindir}/lsb_release
}

FILES_${PN} = "${bindir}/lsb_release"

RCONFLICTS_${PN} = "lsb"
