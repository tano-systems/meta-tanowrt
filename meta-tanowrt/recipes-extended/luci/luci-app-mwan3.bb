#
# SPDX-License-Identifier: MIT
#
# LuCI support for the MWAN3 multiwan hotplug script
#
# This file Copyright (c) 2018, 2020 Tano Systems. All Rights Reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
PR = "tano4"

SUMMARY = "LuCI support for the MWAN3 multiwan hotplug script"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

RDEPENDS:${PN} += "mwan3 luci-lib-nixio luci-compat"

inherit allarch
inherit tanowrt-luci-app
inherit tanowrt-luci-i18n

SRC_URI = "${LUCI_GIT_URI};branch=${LUCI_GIT_BRANCH};protocol=${LUCI_GIT_PROTOCOL};subpath=applications/luci-app-mwan3;destsuffix=git/"
SRCREV = "${LUCI_GIT_SRCREV}"
S = "${WORKDIR}/git"
