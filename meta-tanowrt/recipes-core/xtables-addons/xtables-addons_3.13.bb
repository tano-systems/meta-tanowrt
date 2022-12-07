#
# SPDX-License-Identifier: MIT
# Copyright (c) 2021 Tano Systems LLC. All rights reserved.
#

PV = "3.13"
PR = "tano0.${INC_PR}"
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/patches-3.13:${THISDIR}/${PN}/files:"

SRC_URI = "\
	https://inai.de/files/xtables-addons/${BPN}-${PV}.tar.xz \
	file://001-fix-kernel-version-detection.patch \
	file://100-add-rtsp-conntrack.patch \
	file://200-add-lua-packetscript.patch \
	file://201-fix-lua-packetscript.patch \
	file://901-Unset-LDFLAGS-for-kernel-modules.patch \
	file://902-Fix-building-LUA-modules.patch \
"

SRC_URI[sha256sum] = "893c0c4ea09759cda1ab7e68f1281d125e59270f7b59e446204ce686c6a76d65"

S = "${WORKDIR}/xtables-addons-${PV}"

require xtables-addons.inc
