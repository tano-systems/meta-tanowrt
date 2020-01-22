#
# LuCI Statistics Application
#
# This file Copyright (c) 2018, Tano Systems. All Rights Reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
PR = "tano11"

DEPENDS += "uci-native"

SUMMARY = "LuCI Statistics Application"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

inherit openwrt-luci-app
inherit openwrt-luci-i18n
inherit openwrt-services
inherit luasrcdiet

SRC_URI = "${LUCI_GIT_URI};branch=${LUCI_GIT_BRANCH};protocol=${LUCI_GIT_PROTOCOL};subpath=applications/luci-app-statistics;destsuffix=git/"
SRCREV = "${LUCI_GIT_SRCREV}"
S = "${WORKDIR}/git"

RDEPENDS_${PN} += "rrdtool collectd luci-lib-iptparser luci-compat"

# Files
SRC_URI += "\
	file://luci_statistics.init \
"

OPENWRT_SERVICE_PACKAGES = "luci-app-statistics"
OPENWRT_SERVICE_SCRIPTS_luci-app-statistics += "luci_statistics"
OPENWRT_SERVICE_STATE_luci-app-statistics-luci_statistics ?= "enabled"

CONFFILES_${PN} = "${sysconfdir}/config/luci_statistics"

COLLECTD_ENABLE_IWINFO ?= "${@bb.utils.contains('COMBINED_FEATURES', 'wifi', '1', '0', d)}"

do_install_append() {
	chmod +x ${D}/usr/bin/stat-genconfig

	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/luci_statistics.init ${D}${sysconfdir}/init.d/luci_statistics

	uci -c ${D}${sysconfdir}/config set luci_statistics.collectd_iwinfo.enable='${COLLECTD_ENABLE_IWINFO}'
	uci -c ${D}${sysconfdir}/config commit luci_statistics
}
