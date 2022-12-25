#
# SPDX-License-Identifier: MIT
# Copyright (c) 2019-2021 Tano Systems LLC. All rights reserved.
#
require lldpd.inc

PV = "1.0.13+git${SRCPV}"
PR = "tano0.${INC_PR}"
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

EXTRA_AUTORECONF:append = " --exclude=gnu-configize"

S = "${WORKDIR}/git"
SRC_URI = "git://github.com/vincentbernat/lldpd.git;branch=master;protocol=https"
SRCREV = "e0e375cec9f6ec3dc47b9f41eedab2fb9d415c2e"

SRC_URI += "\
	file://0002-linux-Do-not-set-PACKET_IGNORE_OUTGOING-socket-optio.patch \
"
