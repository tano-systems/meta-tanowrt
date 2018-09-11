#
# LuCI Statistics Application
#
# This file Copyright (c) 2018, Tano Systems. All Rights Reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
PR = "tano1"

SUMMARY = "LuCI Statistics Application"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

require recipes-extended/luci/luci.inc

inherit openwrt-luci-app
inherit openwrt-luci-i18n
inherit openwrt-services

SRC_URI = "${LUCI_GIT_URI};branch=${LUCI_GIT_BRANCH};protocol=${LUCI_GIT_PROTOCOL};subpath=applications/luci-app-statistics;destsuffix=git/"
SRCREV = "${LUCI_GIT_SRCREV}"
S = "${WORKDIR}/git"

RDEPENDS_${PN} += "rrdtool collectd"

OPENWRT_SERVICE_PACKAGES = "${PN}"
OPENWRT_SERVICE_SCRIPTS_${PN} = "luci_statistics"
OPENWRT_SERVICE_STATE_${PN}-luci_statistics ?= "enabled"

CONFFILES_${PN} = "${sysconfdir}/config/luci_statistics"

do_install_append() {
	chmod +x ${D}/usr/bin/stat-genconfig
}

SRC_URI += "\
	file://0001-add-image_height-option-to-config-file.patch \
	file://0002-allow-to-configure-plot-width-and-height.patch \
	file://0003-fix-Russian-translation.patch \
"
