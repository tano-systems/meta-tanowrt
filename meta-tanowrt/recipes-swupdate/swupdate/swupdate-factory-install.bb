#
# SPDX-License-Identifier: MIT
#
# Copyright (c) 2021-2022 Tano Systems LLC. All rights reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
PV = "1.2.0"
PR = "tano0"

SUMMARY = "SWUPDATE factory installation script"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/:"

SWU_INSTALL_EXTRA_RDEPENDS ?= ""
SWU_INSTALL_EXTRA_RRECOMMENDS ?= ""

inherit features_check
REQUIRED_MACHINE_FEATURES = "swupdate swupdate-factory"

COMPATIBLE_MACHINE = "qemupc"

PACKAGE_ARCH = "${MACHINE_ARCH}"

RDEPENDS_${PN} += "swupdate ${SWU_INSTALL_EXTRA_RDEPENDS}"
RRECOMMENDS_${PN} += "${SWU_INSTALL_EXTRA_RRECOMMENDS}"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

SRC_URI += "\
	file://03_swu_install_preinit_started \
	file://98_swu_install_preinit_done \
	file://swu_install.json \
	file://swu_install.board \
	file://swu_install.sh \
"

do_install() {
	install -d ${D}${TANOWRT_PATH_PREINIT}
	install -m 0644 ${WORKDIR}/03_swu_install_preinit_started \
	                ${D}${TANOWRT_PATH_PREINIT}/
	install -m 0644 ${WORKDIR}/98_swu_install_preinit_done \
	                ${D}${TANOWRT_PATH_PREINIT}/

	install -d ${D}${nonarch_libdir}/swupdate/install
	install -m 0755 ${WORKDIR}/swu_install.sh ${D}${nonarch_libdir}/swupdate/install/
	install -m 0755 ${WORKDIR}/swu_install.board ${D}${nonarch_libdir}/swupdate/install/
	install -m 0644 ${WORKDIR}/swu_install.json ${D}${nonarch_libdir}/swupdate/install/
}

FILES_${PN} += "\
	${TANOWRT_PATH_PREINIT} \
	${nonarch_libdir}/swupdate/install \
"

#
# Simple build-time JSON validator
#
DEPENDS += "jq-native"

JSON_VALIDATE = "swu_install.json"

do_json_validate() {
	for json in ${JSON_VALIDATE}; do
		local RC
		if ! jq . "${WORKDIR}/${json}" > /dev/null; then
			bbfatal "Validation for JSON file '${json}' failed"
		fi
	done
}

do_json_validate[depends] += "jq-native:do_populate_sysroot"
addtask json_validate after do_unpack before do_rootfs
