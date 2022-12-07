#
# SPDX-License-Identifier: MIT
#
# LuCI Shell Command Module
#
# This file Copyright (c) 2018, 2020 Tano Systems. All Rights Reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
PR = "tano7"

SUMMARY = "LuCI Shell Command Module"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

RDEPENDS:${PN} += "luci-compat"

inherit allarch
inherit tanowrt-luci-app
inherit tanowrt-luci-i18n

SRC_URI = "${LUCI_GIT_URI};branch=${LUCI_GIT_BRANCH};protocol=${LUCI_GIT_PROTOCOL};subpath=applications/luci-app-commands;destsuffix=git/"
SRCREV = "${LUCI_GIT_SRCREV}"
S = "${WORKDIR}/git"

PACKAGECONFIG ??= "predefined"
PACKAGECONFIG[predefined] = ""

SRC_URI += "${@bb.utils.contains('PACKAGECONFIG', 'predefined', 'file://luci-app-commands-predefined', '', d)}"

do_install:append() {
	if [ "${@bb.utils.contains('PACKAGECONFIG', 'predefined', '1', '0', d)}" = "1" ]; then
		# Add pre-defined commands to LuCI config
		install -d ${D}${sysconfdir}/uci-defaults
		install -m 0755 ${WORKDIR}/luci-app-commands-predefined ${D}${sysconfdir}/uci-defaults/90_luci-app-commands-predefined
	fi
}
