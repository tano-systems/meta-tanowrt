#
# LuCI native po2lmo utility
#
# This file Copyright (C) 2018 Tano Systems
# Anton Kikin <a.kikin@tano-systems.com>
#
PR = "tano1"

inherit native

DESCRIPTION = "OpenWrt LuCI po2lmo utility"
HOMEPAGE = "https://github.com/openwrt/luci"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://po2lmo.c;beginline=1;endline=17;md5=7f0fc61456071c6f845e350f9be6d9c4"
SECTION = "base"

require luci.inc

SRC_URI = "${LUCI_GIT_URI};branch=${LUCI_GIT_BRANCH};protocol=${LUCI_GIT_PROTOCOL};subpath=modules/luci-base/src;destsuffix=git/"
SRCREV  = "${LUCI_GIT_SRCREV}" 

S = "${WORKDIR}/git/"

do_configure[noexec] = "1"

FILES_${PN} = "\
	${base_bindir}/po2lmo"

do_compile() {
	${MAKE} -C ${S}/ clean po2lmo
}

do_install() {
	install -d ${D}${base_bindir}
	install -m 0755 ${S}/po2lmo ${D}${base_bindir}/po2lmo
}
