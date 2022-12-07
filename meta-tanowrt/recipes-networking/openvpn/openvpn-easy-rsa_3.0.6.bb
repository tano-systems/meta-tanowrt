#
# SPDX-License-Identifier: MIT
#
# CLI utility to build and manage a PKI CA
# OpenWrt recipe
#
# Copyright (c) 2019 Tano Systems LLC. All rights reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
PR = "tano0.${INC_PR}"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING.md;md5=58146dc36278c2de5b89534d1ad8f06c"

SRC_URI = "\
	https://github.com/OpenVPN/easy-rsa/releases/download/v${PV}/EasyRSA-unix-v${PV}.tgz \
	file://101-static_EASYRSA.patch \
"

SRC_URI[md5sum] = "14f9b8b278581ecc44bd58e71ab8049c"
SRC_URI[sha256sum] = "cb29aed2d27824e59dbaad547f11dcab380a53c9fe05681249e804af436f1396"

S = "${WORKDIR}/EasyRSA-v${PV}"

require openvpn-easy-rsa.inc
