#
# SPDX-License-Identifier: MIT
#
# LuCI support for LLDP daemon
#
# Copyright (c) 2018-2021 Tano Systems LLC. All rights reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
PV = "2.0.2"
PR = "tano0"

SUMMARY = "LuCI support for LLDP daemon"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=aed2cf5a7c273a7c2dcdbd491a3a8416"

GIT_BRANCH   = "master"
GIT_SRCREV   = "f539803438b4ec279a8d6a6f946d554064efd2b5"
GIT_PROTOCOL = "https"

SRC_URI = "git://github.com/tano-systems/luci-app-tn-lldpd.git;branch=${GIT_BRANCH};protocol=${GIT_PROTOCOL}"
SRCREV = "${GIT_SRCREV}"

LUCI_DO_MINIFY_CSS = "1"

RDEPENDS:${PN} += "lldpd"

S = "${WORKDIR}/git"

inherit allarch
inherit tanowrt-luci-app
inherit tanowrt-luci-i18n

LUCI_APP_TN_LLDPD_HIDE_FOOTER ?= "1"

do_install:append() {
	install -d ${D}${sysconfdir}/uci-defaults

	UCIDEFFILE=${D}${sysconfdir}/uci-defaults/80_luci_app_tn_lldpd_footer

	echo "#!/bin/sh" > ${UCIDEFFILE}
	echo "uci -q batch <<-EOF >/dev/null" >> ${UCIDEFFILE}
	echo "    set luci.app_tn_lldpd=internal" >> ${UCIDEFFILE}
	echo "    set luci.app_tn_lldpd.hide_footer=${LUCI_APP_TN_LLDPD_HIDE_FOOTER}" >> ${UCIDEFFILE}
	echo "    commit luci" >> ${UCIDEFFILE}
	echo "EOF" >> ${UCIDEFFILE}
	echo "exit 0" >> ${UCIDEFFILE}
}
