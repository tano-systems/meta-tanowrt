#
# The ARP Scanner (arp-scan) is Copyright (C) 2005-2019 Roy Hills
#
# SPDX-License-Identifier: MIT
# Copyright (c) 2021 Tano Systems LLC. All rights reserved.
# Authors: Anton Kikin <a.kikin@tano-systems.com>
#

PR = "tano0"
PV = "1.9.7+git${SRCPV}"

DESCRIPTION = "This package contains an ARP scanner utility"
SUMMARY = "The ARP scanner"
SECTION = "net"
DEPENDS = "libpcap"
LICENSE = "GPL-3.0-only"
HOMEPAGE = "https://github.com/royhills/arp-scan"
LIC_FILES_CHKSUM = "\
	file://COPYING;md5=1ebbd3e34237af26da5dc08a4e440464 \
"

SRC_URI = "git://github.com/royhills/arp-scan.git;branch=master;protocol=https"
SRCREV = "7aaa3a67a885dfa507ce27bc700b12697f32b6c4"
S = "${WORKDIR}/git"

inherit autotools

PACKAGES += "${PN}-data"

FILES:${PN} = "/usr/bin/"

DESCRIPTION:${PN}-data = "This package contains an data files for ARP scanner utility"
SUMMARY:${PN}-data = "The ARP scanner data files"
FILES:${PN}-data = "/usr/share/arp-scan"
