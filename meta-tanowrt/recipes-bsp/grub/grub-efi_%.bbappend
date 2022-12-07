#
# SPDX-License-Identifier: MIT
# Copyright (c) 2020-2022 Tano Systems LLC. All rights reserved.
#

PR:append = ".tano9"
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/:${THISDIR}/${PN}/patches:"

# Patches for 2.04 (2.06~rc1) GNU version
SRC_URI += "\
	file://0001-gpt-start-new-GPT-module.patch \
	file://0002-gpt-rename-misnamed-header-location-fields.patch \
	file://0003-gpt-record-size-of-of-the-entries-table.patch \
	file://0004-gpt-consolidate-crc32-computation-code.patch \
	file://0005-gpt-add-new-repair-function-to-sync-up-primary-and-b.patch \
	file://0006-gpt-add-write-function-and-gptrepair-command.patch \
	file://0007-gpt-add-a-new-generic-GUID-type.patch \
	file://0008-gpt-new-gptprio.next-command-for-selecting-priority-.patch \
	file://0009-gpt-split-out-checksum-recomputation.patch \
	file://0010-gpt-move-gpt-guid-printing-function-to-common-librar.patch \
	file://0011-gpt-switch-partition-names-to-a-16-bit-type.patch \
	file://0012-gpt-add-search-by-partition-label-and-uuid-commands.patch \
	file://0013-gpt-clean-up-little-endian-crc32-computation.patch \
	file://0014-gpt-minor-cleanup.patch \
	file://0015-gpt-add-search-by-disk-uuid-command.patch \
	file://0016-gpt-fix-used-types-in-other-modules.patch \
"

PACKAGE_ARCH = "${MACHINE_ARCH}"

EFI_STARTUP_DEVICE ?= "fs0"

GRUB_BUILDIN = "boot linux fat squash4 ext2 serial part_msdos part_gpt normal \
                efi_gop iso9660 configfile search loadenv test echo probe"

do_install:append:class-target() {
	# We do not need this in ${libdir}/grub
	rm -rf ${D}/${prefix}/

	# Install startup.nsh to /boot
	install -d ${D}${EFI_PREFIX}
	echo "${EFI_STARTUP_DEVICE}:${@d.getVar('EFIDIR', True).replace('/', '\\')}\\${EFI_BOOT_IMAGE}" \
		> ${D}${EFI_PREFIX}/startup.nsh
}

FILES:${PN}:remove = "${libdir}/grub/${GRUB_TARGET}-efi"
FILES:${PN} += "${EFI_PREFIX}/startup.nsh"

do_preconfigure() {
	# Set version to value with revision (PR) part
	sed -i -e \
		"s/\(AC_INIT(\[GRUB\],\[\).*\(\],\[bug-grub@gnu.org\])\)/\1${PV}-${PR}\2/" \
		${S}/configure.ac
}

addtask preconfigure after do_patch before do_configure

do_deploy:append() {
	# Deploy file with version info
	echo "${PV}-${PR}" > ${DEPLOYDIR}/${GRUB_IMAGE_PREFIX}${GRUB_IMAGE}.version
}

do_deploy:append:class-target() {
	install -m 644 ${D}${EFI_PREFIX}/startup.nsh \
		${DEPLOYDIR}/${GRUB_IMAGE_PREFIX}startup.nsh
}
