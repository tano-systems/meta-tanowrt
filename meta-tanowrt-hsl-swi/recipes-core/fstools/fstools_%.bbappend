#
# SPDX-License-Identifier: MIT
# Copyright (c) 2020 Tano Systems LLC. All rights reserved.
#
PR_append = ".swi1"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "\
	file://0001-mount_root-Switch-to-ram-overlay-by-default.patch \
	file://0002-block-Deny-access-to-unacessible-mtd-devices.patch \
"
