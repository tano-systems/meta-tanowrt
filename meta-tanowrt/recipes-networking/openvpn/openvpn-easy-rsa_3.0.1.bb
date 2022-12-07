#
# SPDX-License-Identifier: MIT
#
# CLI utility to build and manage a PKI CA
# OpenWrt recipe
#
# Copyright (c) 2018 Tano Systems LLC. All rights reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
PR = "tano0.${INC_PR}"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=bc50580af64334feaf532250b8d12631"

SRC_URI = "\
	https://github.com/OpenVPN/easy-rsa/releases/download/${PV}/EasyRSA-${PV}.zip \
"

SRC_URI[md5sum] = "7e465cec5f143990b9b40c2d6735be31"
SRC_URI[sha256sum] = "4ac05aac894a71490e57def4a476915440e1c557393d31c440c3ae5130e9c624"

S = "${WORKDIR}/EasyRSA-${PV}"

require openvpn-easy-rsa.inc
