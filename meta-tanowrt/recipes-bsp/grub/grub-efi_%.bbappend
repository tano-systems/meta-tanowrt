#
# SPDX-License-Identifier: MIT
# Copyright (c) 2020-2021 Tano Systems LLC. All rights reserved.
#

PR_append = ".tano1"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/:"

PACKAGE_ARCH = "${MACHINE_ARCH}"

GRUB_BUILDIN = "boot linux fat squash4 ext2 serial part_msdos part_gpt normal \
                efi_gop iso9660 configfile search loadenv"
