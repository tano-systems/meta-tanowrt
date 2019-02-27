#
# Network ports status LuCI application
#
# Copyright (c) 2018-2019, Tano Systems. All Rights Reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
PV = "1.0.0-git${SRCPV}"
PR = "tano11"

RDEPENDS_${PN} += "luabitop"

SUMMARY = "Network ports status LuCI application"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=aed2cf5a7c273a7c2dcdbd491a3a8416"

GIT_BRANCH   = "master"
GIT_SRCREV   = "320f1c0ed37593e8665ff81ff8d56688cc14f335"
GIT_PROTOCOL = "https"
SRC_URI = "git://github.com/tano-systems/luci-app-netports.git;branch=${GIT_BRANCH};protocol=${GIT_PROTOCOL}"

SRCREV = "${GIT_SRCREV}"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"
SRC_URI += "\
	file://luci_netports.config \
"

do_install_append() {
	install -dm 0755 ${D}${sysconfdir}/config
	install -m 0644 ${WORKDIR}/luci_netports.config ${D}${sysconfdir}/config/luci_netports
}

CONFFILES_${PN} = "${sysconfdir}/config/luci_netports"
RRECOMMENDS_${PN} += "luci-app-netports-hotplug"

S = "${WORKDIR}/git"

inherit openwrt-luci-app
inherit openwrt-luci-i18n
inherit luasrcdiet
