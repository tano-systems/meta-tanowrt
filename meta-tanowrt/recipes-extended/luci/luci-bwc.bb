#
# SPDX-License-Identifier: MIT
#
# Very simple bandwidth collector cache for LuCI realtime graphs
#
# This file Copyright (C) 2019 Tano Systems
# Anton Kikin <a.kikin@tano-systems.com>
#
PR = "tano1"

DESCRIPTION = "Very simple bandwidth collector cache for LuCI realtime graphs"
HOMEPAGE = "https://github.com/openwrt/luci"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"
SECTION = "base"

DEPENDS += "iwinfo"

inherit tanowrt-luci

SRC_URI = "${LUCI_GIT_URI};branch=${LUCI_GIT_BRANCH};protocol=${LUCI_GIT_PROTOCOL};subpath=modules/luci-mod-status/src;destsuffix=git/"
SRCREV  = "${LUCI_GIT_SRCREV}"

S = "${WORKDIR}/git"

FILES_${PN} = "\
	${bindir}/luci-bwc"

do_configure() {
	oe_runmake -C ${S}/ clean
}

do_compile() {
	oe_runmake -C ${S}/ luci-bwc
}

do_install() {
	install -d ${D}${bindir}
	install -m 0755 ${S}/luci-bwc ${D}${bindir}/luci-bwc
}
