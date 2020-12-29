#
# SPDX-License-Identifier: MIT
#
# LuCI Status Pages
#
# This file Copyright (c) 2019-2020 Tano Systems LLC. All rights reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
PR = "tano4"

SUMMARY = "LuCI Status Pages"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

RDEPENDS_${PN} += "luci-base libiwinfo-lua rpcd-mod-rrdns luci-bwc"

inherit allarch
inherit tanowrt-luci-mod

SRC_URI = "${LUCI_GIT_URI};branch=${LUCI_GIT_BRANCH};protocol=${LUCI_GIT_PROTOCOL};subpath=modules/luci-mod-status;destsuffix=git/"
SRCREV = "${LUCI_GIT_SRCREV}"
S = "${WORKDIR}/git"

RRECOMMENDS_${PN} += "luci-app-tn-logview"
