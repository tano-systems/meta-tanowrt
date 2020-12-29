#
# SPDX-License-Identifier: MIT
# Copyright (c) 2018-2020 Tano Systems LLC. All rights reserved.
#
# nl80211 based CLI configuration utility for wireless devices
#
SUMMARY = "nl80211 based CLI configuration utility for wireless devices"
DESCRIPTION = "iw is a new nl80211 based CLI configuration utility for \
wireless devices. It supports almost all new drivers that have been added \
to the kernel recently. "
HOMEPAGE = "http://wireless.kernel.org/en/users/Documentation/iw"
SECTION = "base"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=878618a5c4af25e9b93ef0be1a93f774"

PR = "tano0"

# Compile always with gcc
TOOLCHAIN = "gcc"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"
DEPENDS = "libnl"

SRC_URI = "http://www.kernel.org/pub/software/network/iw/${BP}.tar.xz"

# Patches
SRC_URI += "\
	file://001-nl80211_h_sync.patch \
	file://120-antenna_gain.patch \
	file://130-survey-bss-rx-time.patch \
	file://200-reduce_size.patch \
"

SRC_URI[sha256sum] = "b59c4bacb0b6675cc09f2f30ebdceb91174cb65443d0a062fba2c09fad772db4"

inherit pkgconfig

CFLAGS += "\
	-DIW_FULL \
	-DCONFIG_LIBNL20 \
	-D_GNU_SOURCE \
	-flto \
"

EXTRA_OEMAKE = "\
	'IW_FULL=1' \
	'PREFIX=${prefix}' \
	'SBINDIR=${sbindir}' \
	'MANDIR=${mandir}' \
"

B = "${S}"

do_install() {
	oe_runmake 'DESTDIR=${D}' install
}
