#
# SPDX-License-Identifier: MIT
# Copyright (c) 2020 Tano Systems LLC. All rights reserved.
#

PV = "3.9"
PR = "tano0.${INC_PR}"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches-3.9:${THISDIR}/${PN}/files:"

SRC_URI = "\
	${SOURCEFORGE_MIRROR}/project/xtables-addons/Xtables-addons/${BPN}-${PV}.tar.xz \
	file://001-fix-kernel-version-detection.patch \
	file://002-restore-support-for-Linux-4.14.patch \
	file://100-add-rtsp-conntrack.patch \
	file://200-add-lua-packetscript.patch \
	file://201-fix-lua-packetscript.patch \
	file://901-Unset-LDFLAGS-for-kernel-modules.patch \
	file://902-Fix-building-LUA-modules.patch \
"

SRC_URI[md5sum] = "8b548e7dbd5fee845cd08a687ae2073c"
SRC_URI[sha256sum] = "064dd68937d98e6cfcbdf51ef459310d9810c17ab31b21285bc7a76cdcef7c49"

S = "${WORKDIR}/xtables-addons-${PV}"

require xtables-addons.inc
