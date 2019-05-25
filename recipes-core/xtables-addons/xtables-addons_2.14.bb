# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>
PV = "2.14"
PR = "tano3.${INC_PR}"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI = "\
	git://git.code.sf.net/p/xtables-addons/xtables-addons;branch=master \
	file://100-add-rtsp-conntrack.patch \
	file://200-add-lua-packetscript.patch \
	file://201-fix-lua-packetscript.patch \
	file://202-add-lua-autoconf.patch \
	file://0001-Unset-LDFLAGS-for-kernel-modules.patch \
	file://fix-to-build-linux-v4.12-and-earlier.patch \
"

SRCREV = "0e9037b0007eaaf0ae612a1481bd03b15ecb8cb8"
S = "${WORKDIR}/git"

require xtables-addons.inc
