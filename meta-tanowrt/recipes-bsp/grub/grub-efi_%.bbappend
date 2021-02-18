#
# SPDX-License-Identifier: MIT
# Copyright (c) 2020-2021 Tano Systems LLC. All rights reserved.
#

PR_append = ".tano2"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/:"

PACKAGE_ARCH = "${MACHINE_ARCH}"

GRUB_BUILDIN = "boot linux fat squash4 ext2 serial part_msdos part_gpt normal \
                efi_gop iso9660 configfile search loadenv"

do_install_append_class-target() {
	# We do not need this in ${libdir}/grub
	rm -rf ${D}/${prefix}/
}

FILES_${PN}_remove = "${libdir}/grub/${GRUB_TARGET}-efi"
