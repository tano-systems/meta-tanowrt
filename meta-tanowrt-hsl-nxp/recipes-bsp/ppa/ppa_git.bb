#
# SPDX-License-Identifier: MIT
# Copyright (c) 2020 Tano Systems LLC. All rights reserved.
#
SUMMARY = "PPA firmware for NXP layerscape platforms"
DESCRIPTION = "PPA firmware for NXP layerscape platforms"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://license.txt;md5=2ecf925c01a48f61c88f78c30fe2ee3b"

inherit deploy siteinfo

DEPENDS += "u-boot-tools-native dtc-native"

PR = "tano0"
PV = "LSDK-18.09"
SRC_URI = "git://source.codeaurora.org/external/qoriq/qoriq-components/ppa-generic;nobranch=1"

# Tag: LSDK-18.09
SRCREV = "90e13c9e148972f75f5f2e7f7a904dabf1586049"

S = "${WORKDIR}/git"
B = "${S}"

export CROSS_COMPILE="${TARGET_PREFIX}"
export ARCH="arm64"

PPA_PLATFORM = "${MACHINE}"
PPA_PLATFORM:ls1028ardb = "ls1028"

do_install() {
	cd ${S}/ppa/ && ./build rdb-fit all
}

do_deploy() {
	cp -f ${S}/ppa/soc-${PPA_PLATFORM}/build/obj/ppa.itb ${DEPLOYDIR}/
}

addtask deploy after do_install

PACKAGES += "${PN}-image"
FILES:${PN}-image += "/boot"

COMPATIBLE_MACHINE = "(qoriq)"
PACKAGE_ARCH = "${MACHINE_ARCH}"
