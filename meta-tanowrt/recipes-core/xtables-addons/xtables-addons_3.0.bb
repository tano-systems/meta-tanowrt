#
# SPDX-License-Identifier: MIT
# Copyright (c) 2018-2020 Tano Systems LLC. All rights reserved.
#

PV = "3.0"
PR = "tano5.${INC_PR}"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI = "\
	${SOURCEFORGE_MIRROR}/project/xtables-addons/Xtables-addons/${BPN}-${PV}.tar.xz \
	file://001-fix-kernel-version-detection.patch \
	file://100-add-rtsp-conntrack.patch \
	file://200-add-lua-packetscript.patch \
	file://201-fix-lua-packetscript.patch \
	file://202-add-lua-autoconf.patch \
	file://300-geoip-endian-detection.patch \
	file://400-fix-IFF_LOWER_UP-musl.patch \
	file://fix-to-build-linux-v4.15-and-later.patch \
	file://0001-Unset-LDFLAGS-for-kernel-modules.patch \
"

SRC_URI[md5sum] = "b37ed4d9c28cdcd5558c55934be8d051"
SRC_URI[sha256sum] = "95580b851c79c0bbc484e0d0ea23f53e5c7f439ad73d509e426598565392690d"

S = "${WORKDIR}/xtables-addons-${PV}"

require xtables-addons.inc
