#
# SPDX-License-Identifier: MIT
#
# LuCI rpcd module
#
# This file Copyright (c) 2019, 2022 Tano Systems LLC. All rights reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
PR = "tano1"
DESCRIPTION = "LuCI rpcd module"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

DEPENDS += "libubox libubus rpcd libnl"
RDEPENDS_${PN} += "rpcd"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

inherit cmake
inherit tanowrt-luci

LUCI_DO_POST_INSTALL_ACTIONS = "0"

SRC_URI = "${LUCI_GIT_URI};branch=${LUCI_GIT_BRANCH};protocol=${LUCI_GIT_PROTOCOL};subpath=libs/rpcd-mod-luci/src;destsuffix=git/"
SRCREV = "${LUCI_GIT_SRCREV}"
S = "${WORKDIR}/git"

SRC_URI += "file://0100-fix-libnl-include-directories.patch;pnum=4"

do_unpack[vardeps] += "libdir"

do_configure_prepend() {
	sed -i -e "s:/usr/lib/:${libdir}/:g" ${S}/luci.c
}

do_install() {
	install -dm 0755 ${D}${libdir}/rpcd
	install -m 0755 ${B}/luci.so ${D}${libdir}/rpcd/luci.so
}

FILES_${PN} += "${libdir}/rpcd"

pkg_postinst_${PN}_append() {
#!/bin/sh
IPKG_INSTROOT=$D
if [ -z "${IPKG_INSTROOT}" ]; then
	# Run only on target (reload rpcd modules)
	killall -HUP rpcd 2>/dev/null
fi
}
