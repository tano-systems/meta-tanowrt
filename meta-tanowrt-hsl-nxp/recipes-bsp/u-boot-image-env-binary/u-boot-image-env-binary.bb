#
# SPDX-License-Identifier: MIT
# Copyright (c) 2020 Tano Systems LLC. All rights reserved.
#
PR = "tano1"
SUMMARY = "U-boot environment binary"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

DEPENDS += "u-boot-tools-native"

inherit deploy siteinfo

SRC_URI = "file://u-boot-env.txt"

do_install() {
	install -d ${D}/boot
	cat ${WORKDIR}/u-boot-env.txt | mkenvimage -s ${UBOOT_IMAGE_ENV_SIZE} -o ${D}/boot/${UBOOT_IMAGE_ENV_BINARY}
}

do_deploy() {
	install -m 0644 ${D}/boot/${UBOOT_IMAGE_ENV_BINARY} ${DEPLOYDIR}/${UBOOT_IMAGE_ENV_BINARY}
}

addtask deploy after do_install

PACKAGES += "${PN}-image"
FILES_${PN}-image += "/boot"

PACKAGE_ARCH = "${MACHINE_ARCH}"
