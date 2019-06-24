#
# Rapid reverse DNS rpcd module
#
# This file Copyright (c) 2019, Tano Systems. All Rights Reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
PR = "tano0"
DESCRIPTION = "Rapid reverse DNS rpcd module"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

DEPENDS += "libubox libubus rpcd"
RDEPENDS_${PN} += "rpcd"

inherit cmake
inherit openwrt-luci
require recipes-extended/luci/luci.inc

SRC_URI = "${LUCI_GIT_URI};branch=${LUCI_GIT_BRANCH};protocol=${LUCI_GIT_PROTOCOL};subpath=libs/rpcd-mod-rrdns/src;destsuffix=git/"
SRCREV = "${LUCI_GIT_SRCREV}"
S = "${WORKDIR}/git"

do_install() {
	install -dm 0755 ${D}${libdir}/rpcd
	install -m 0755 ${B}/rrdns.so ${D}${libdir}/rpcd/rrdns.so
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
