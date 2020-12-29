#
# SPDX-License-Identifier: MIT
#
# The JavaScript Minifier
#
# This file Copyright (C) 2019 Tano Systems
# Anton Kikin <a.kikin@tano-systems.com>
#
PR = "tano2"

DESCRIPTION = "The JavaScript Minifier"
HOMEPAGE = "https://github.com/openwrt/luci"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://jsmin.c;beginline=4;endline=24;md5=4c8157a1c93fdaa847b4d5ccc33c7c63"
SECTION = "base"

require recipes-extended/luci/luci.inc

LUCI_DO_POST_INSTALL_ACTIONS = "0"

SRC_URI = "${LUCI_GIT_URI};branch=${LUCI_GIT_BRANCH};protocol=${LUCI_GIT_PROTOCOL};subpath=modules/luci-base/src;destsuffix=git/"
SRCREV  = "${LUCI_GIT_SRCREV}"

S = "${WORKDIR}/git/"

FILES_${PN} = "\
	${base_bindir}/jsmin"

do_configure() {
	oe_runmake -C ${S}/ clean
}

do_compile() {
	oe_runmake -C ${S}/ jsmin
}

do_install() {
	install -d ${D}${base_bindir}
	install -m 0755 ${S}/jsmin ${D}${base_bindir}/jsmin
}

BBCLASSEXTEND = "native"
