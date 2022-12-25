#
# SPDX-License-Identifier: MIT
# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>
#

PV = "2.14"
PR = "tano4.${INC_PR}"
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/patches-2.14:${THISDIR}/${PN}/files:"

SRC_URI = "\
	git://git.code.sf.net/p/xtables-addons/xtables-addons;branch=master \
	file://100-add-rtsp-conntrack.patch \
	file://200-add-lua-packetscript.patch \
	file://201-fix-lua-packetscript.patch \
	file://202-add-lua-autoconf.patch \
	file://0001-Unset-LDFLAGS-for-kernel-modules.patch \
"

SRCREV = "0e9037b0007eaaf0ae612a1481bd03b15ecb8cb8"
S = "${WORKDIR}/git"

require xtables-addons.inc
