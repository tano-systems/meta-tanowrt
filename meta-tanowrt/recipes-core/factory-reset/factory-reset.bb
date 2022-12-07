#
# SPDX-License-Identifier: MIT
#
# Copyright (c) 2021-2022 Tano Systems LLC. All rights reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
PV = "1.0.0"
PR = "tano1"

SUMMARY = "Factory reset script"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/:"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

SRC_URI += "\
	file://factory-reset.sh \
"

do_install() {
	install -d ${D}${nonarch_base_libdir}/factory-reset.d
	install -d ${D}${base_sbindir}
	install -m 0755 ${WORKDIR}/factory-reset.sh ${D}${base_sbindir}/factory-reset
}

FILES:${PN} += "\
	${nonarch_base_libdir}/factory-reset.d \
	${base_sbindir} \
"
