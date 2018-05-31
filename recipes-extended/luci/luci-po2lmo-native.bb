#
# LuCI native po2lmo utility
#
# This file Copyright (C) 2018 Tano Systems
# Anton Kikin <a.kikin@tano-systems.com>
#
PR = "tano0"

inherit native

DEPENDS += "perl"

DESCRIPTION = "OpenWrt LuCI po2lmo utility"
HOMEPAGE = "https://github.com/openwrt/luci"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=2b42edef8fa55315f34f2370b4715ca9"
SECTION = "base"

SRC_URI = "git://github.com/openwrt/luci.git;branch=lede-17.01"
require luci.inc

S = "${WORKDIR}/git/"

do_configure[noexec] = "1"

FILES_${PN} = "\
	${base_bindir}/po2lmo"

do_compile() {
	${MAKE} -C ${S}/modules/luci-base/src/ clean po2lmo
}

do_install() {
	install -d ${D}${base_bindir}
	install -m 0755 ${S}/modules/luci-base/src/po2lmo ${D}${base_bindir}/po2lmo
}
