#
# The JavaScript Minifier
#
# This file Copyright (C) 2019 Tano Systems
# Anton Kikin <a.kikin@tano-systems.com>
#
PR = "tano0"

DESCRIPTION = "The JavaScript Minifier"
HOMEPAGE = "https://github.com/openwrt/luci"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://jsmin.c;beginline=4;endline=24;md5=648b51db3c42cb6e2405d79aae547c8d"
SECTION = "base"

require recipes-extended/luci/luci.inc

LUCI_DO_POST_INSTALL_ACTIONS = "0"

SRC_URI = "${LUCI_GIT_URI};branch=${LUCI_GIT_BRANCH};protocol=${LUCI_GIT_PROTOCOL};subpath=modules/luci-base/src;destsuffix=git/"
SRCREV  = "${LUCI_GIT_SRCREV}"

S = "${WORKDIR}/git/"

do_configure[noexec] = "1"

FILES_${PN} = "\
	${base_bindir}/jsmin"

do_compile() {
	oe_runmake -C ${S}/ clean jsmin
}

do_install() {
	install -d ${D}${base_bindir}
	install -m 0755 ${S}/jsmin ${D}${base_bindir}/jsmin
}

BBCLASSEXTEND = "native"
