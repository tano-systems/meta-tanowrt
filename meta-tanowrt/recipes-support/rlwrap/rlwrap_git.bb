#
# SPDX-License-Identifier: MIT
#
# rlwrap is a 'readline wrapper', a small utility that uses the
# GNU Readline library to allow the editing of keyboard input for any command.
#
# This file Copyright (c) 2023, Anton Kikin <a.kikin@tano-systems.com>
#
HOMEPAGE = "https://github.com/hanslub42/rlwrap"
SUMMARY = "GNU Readline wrapper"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI = "git://github.com/hanslub42/rlwrap.git;protocol=https;branch=master"
SRCREV = "8c387299d1a3fa25d33966dbcababbae973d0963"
PV = "0.46.1+git${SRCPV}"
PR = "tano0"

DEPENDS += "readline"

S = "${WORKDIR}/git"

inherit autotools-brokensep pkgconfig

PACKAGES =+ "${PN}-filters"
RDEPENDS:${PN}-filters += "rlwrap"
FILES:${PN}-filters = "${datadir}/rlwrap/filters/*"
