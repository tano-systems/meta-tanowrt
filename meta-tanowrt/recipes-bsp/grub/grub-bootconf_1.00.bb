#
# SPDX-License-Identifier: MIT
# Copyright (c) 2020-2022 Tano Systems LLC. All rights reserved.
#

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
SUMMARY = "grub.cfg for use in EFI systems"

PR = "tano7"

PROVIDES += "virtual/grub-bootconf"
RPROVIDES_${PN} += "virtual/grub-bootconf"
PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit vars-expander
EXPAND_VARIABLES:append = "${TANOWRT_PARTUUID_VARS}"

require conf/image-uefi.conf

S = "${WORKDIR}"

SRC_URI += "\
	file://grub.cfg.in \
	file://grubenv \
"

inherit deploy

do_install() {
	install -d ${D}${EFI_FILES_PATH}
	install -m 0644 ${WORKDIR}/grub.cfg ${D}${EFI_FILES_PATH}/grub.cfg
	install -m 0644 ${WORKDIR}/grubenv ${D}${EFI_FILES_PATH}/grubenv
}

do_deploy[cleandirs] += "${DEPLOYDIR}"

GRUB_IMAGE_PREFIX ?= "grub-efi-"

do_deploy() {
	install -d ${DEPLOYDIR}/grub
	install ${WORKDIR}/grub.cfg ${DEPLOYDIR}/grub.cfg
	install ${WORKDIR}/grubenv ${DEPLOYDIR}/${GRUB_IMAGE_PREFIX}grubenv
}

addtask deploy after do_install

FILES_${PN} = "\
	${EFI_FILES_PATH}/grub.cfg \
	${EFI_FILES_PATH}/grubenv \
"
