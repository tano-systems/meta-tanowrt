# Copyright (C) 2019-2020 Anton Kikin <a.kikin@tano-systems.com>
# Released under the MIT license (see COPYING.MIT for the terms)

PR_append = ".swi1"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "\
	file://0001-mount_root-Switch-to-ram-overlay-by-default.patch \
	file://0002-block-Deny-access-to-unacessible-mtd-devices.patch \
"
