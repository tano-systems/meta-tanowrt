#
# SPDX-License-Identifier: MIT
#
# LuCI po2lmo utility
#
# This file Copyright (C) 2018-2021 Tano Systems LLC
# Anton Kikin <a.kikin@tano-systems.com>
#
PR = "tano2"

DESCRIPTION = "OpenWrt LuCI po2lmo utility"
HOMEPAGE = "https://github.com/openwrt/luci"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://po2lmo.c;beginline=1;endline=17;md5=7f0fc61456071c6f845e350f9be6d9c4"
SECTION = "base"

DEPENDS += "lemon-native"

require recipes-extended/luci/luci.inc

SRC_URI = "${LUCI_GIT_URI};branch=${LUCI_GIT_BRANCH};protocol=${LUCI_GIT_PROTOCOL};subpath=modules/luci-base/src;destsuffix=git/"
SRCREV  = "${LUCI_GIT_SRCREV}"

S = "${WORKDIR}/git"

FILES:${PN} = "\
	${base_bindir}/po2lmo"

do_configure() {
	oe_runmake -C ${S}/ clean
}

do_compile() {
	oe_runmake -C ${S}/ po2lmo
}

do_compile[depends] += "lemon-native:do_populate_sysroot"

do_install() {
	install -d ${D}${base_bindir}
	install -m 0755 ${S}/po2lmo ${D}${base_bindir}/po2lmo
}

BBCLASSEXTEND = "native"
