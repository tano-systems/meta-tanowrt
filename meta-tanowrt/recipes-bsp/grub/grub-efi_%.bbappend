#
# SPDX-License-Identifier: MIT
# Copyright (c) 2020-2021 Tano Systems LLC. All rights reserved.
#

PR_append = ".tano6"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/:"

PACKAGE_ARCH = "${MACHINE_ARCH}"

GRUB_BUILDIN = "boot linux fat squash4 ext2 serial part_msdos part_gpt normal \
                efi_gop iso9660 configfile search loadenv test echo"

do_install_append_class-target() {
	# We do not need this in ${libdir}/grub
	rm -rf ${D}/${prefix}/

	# Install startup.nsh to /boot
	install -d ${D}${EFI_PREFIX}
	echo "fs0:${@d.getVar('EFIDIR', True).replace('/', '\\')}\\${EFI_BOOT_IMAGE}" \
		> ${D}${EFI_PREFIX}/startup.nsh
}

FILES_${PN}_remove = "${libdir}/grub/${GRUB_TARGET}-efi"
FILES_${PN} += "${EFI_PREFIX}/startup.nsh"

do_preconfigure() {
	# Set version to value with revision (PR) part
	sed -i -e \
		"s/\(AC_INIT(\[GRUB\],\[\).*\(\],\[bug-grub@gnu.org\])\)/\1${PV}-${PR}\2/" \
		${S}/configure.ac
}

addtask preconfigure after do_patch before do_configure

do_deploy_append() {
	# Deploy file with version info
	echo "${PV}-${PR}" > ${DEPLOYDIR}/${GRUB_IMAGE_PREFIX}${GRUB_IMAGE}.version
}

do_deploy_append_class-target() {
	install -m 644 ${D}${EFI_PREFIX}/startup.nsh \
		${DEPLOYDIR}/${GRUB_IMAGE_PREFIX}startup.nsh
}
