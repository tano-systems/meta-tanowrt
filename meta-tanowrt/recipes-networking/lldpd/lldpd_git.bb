#
# SPDX-License-Identifier: MIT
# Copyright (c) 2019-2021 Tano Systems LLC. All rights reserved.
#
require lldpd.inc

PV = "1.0.9+git${SRCPV}"
PR = "tano1.${INC_PR}"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

EXTRA_AUTORECONF_append = " --exclude=gnu-configize"

S = "${WORKDIR}/git"
SRC_URI = "git://github.com/vincentbernat/lldpd.git"
SRCREV = "e4a3cdbdf32e22172a67253dae001becd82e4f60"

SRC_URI += "\
	file://0002-linux-Do-not-set-PACKET_IGNORE_OUTGOING-socket-optio.patch \
"
