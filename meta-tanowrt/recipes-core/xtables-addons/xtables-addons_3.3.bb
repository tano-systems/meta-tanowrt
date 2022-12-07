#
# SPDX-License-Identifier: MIT
# Copyright (c) 2019-2020 Tano Systems LLC. All rights reserved.
#

PV = "3.3"
PR = "tano2.${INC_PR}"
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI = "\
	${SOURCEFORGE_MIRROR}/project/xtables-addons/Xtables-addons/${BPN}-${PV}.tar.xz \
	file://001-fix-kernel-version-detection.patch \
	file://100-add-rtsp-conntrack.patch \
	file://200-add-lua-packetscript.patch \
	file://201-fix-lua-packetscript.patch \
	file://202-add-lua-autoconf.patch \
	file://400-fix-IFF_LOWER_UP-musl.patch \
	file://fix-to-build-linux-v4.15-and-later.patch \
	file://0001-Unset-LDFLAGS-for-kernel-modules.patch \
"

SRC_URI[md5sum] = "e99ea681b7b3866a581390e1b3ea185e"
SRC_URI[sha256sum] = "efa62c7df6cd3b82d7195105bf6fe177b605f91f3522e4114d2f4e0ad54320d6"

S = "${WORKDIR}/xtables-addons-${PV}"

require xtables-addons.inc
