#
# SPDX-License-Identifier: MIT
# Copyright (c) 2019-2021 Tano Systems LLC. All rights reserved.
#
require lldpd.inc

PV = "1.0.12+git${SRCPV}"
PR = "tano0.${INC_PR}"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

EXTRA_AUTORECONF_append = " --exclude=gnu-configize"

S = "${WORKDIR}/git"
SRC_URI = "git://github.com/vincentbernat/lldpd.git"
SRCREV = "2129b015777e215399ac8fe622a678694b57565e"

SRC_URI += "\
	file://0002-linux-Do-not-set-PACKET_IGNORE_OUTGOING-socket-optio.patch \
"
