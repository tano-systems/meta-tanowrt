#
# SPDX-License-Identifier: MIT
# Copyright (c) 2020 Tano Systems LLC. All rights reserved.
#
inherit kernel-config

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

PR:append = "${@'.rpi0' if d.getVar('WIREGUARD_PATCH_RPI_4_19') == '1' else ''}"
SRC_URI:append = "${@' file://0001-Fix-compilation-for-RPi-kernel-4.19.patch;pnum=2 ' if d.getVar('WIREGUARD_PATCH_RPI_4_19') == '1' else ''}"
