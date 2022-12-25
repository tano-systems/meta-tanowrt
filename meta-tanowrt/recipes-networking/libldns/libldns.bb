#
# SPDX-License-Identifier: MIT
# Copyright (c) 2018-2020, 2022 Tano Systems LLC. All rights reserved.
#
PV = "1.8.3"
PR = "tano0"

SUMMARY = "A library to simplify DNS programming"
DESCRIPTION = "\
The goal of ldns is to simplify DNS programming, it supports recent RFCs like \
the DNSSEC documents, and allows developers to easily create software \
conforming to current RFCs, and experimental software for current Internet \
Drafts."

SECTION = "net/misc"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"
DEPENDS = "libtool openssl"

SRC_URI = "\
	http://www.nlnetlabs.nl/downloads/ldns/ldns-${PV}.tar.gz \
"

# Patches
SRC_URI += "\
	file://0001-libldns.pc-Clean-Libs.private.patch \
"

SRC_URI[sha256sum] = "c3f72dd1036b2907e3a56e6acf9dfb2e551256b3c1bbd9787942deeeb70e7860"
S = "${WORKDIR}/ldns-${PV}"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=34330f15b2b4abbbaaa7623f79a6a019"

inherit pkgconfig autotools

EXTRA_OECONF += "\
	--disable-dsa \
	--disable-gost \
	--enable-ecdsa \
	--with-drill \
	--with-ssl=${STAGING_DIR_TARGET}/usr \
"

PACKAGES += "drill"

SUMMARY:drill = "DNS(SEC) information tool"
DESCRIPTION:drill = "\
drill is a tool to designed to get all sorts of information out of the DNS. It \
is specificly designed to be used with DNSSEC."

RDEPENDS:drill += "${PN}"
ALLOW_EMPTY:drill = "1"
