#
# SPDX-License-Identifier: MIT
#
# Support for 3G
#
# This file Copyright (c) 2018, 2020 Tano Systems. All Rights Reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
PR = "tano4"

SUMMARY = "LuCI support for 3G"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

RDEPENDS:${PN} += "comgt"

inherit allarch
inherit tanowrt-luci-proto

SRC_URI = "${LUCI_GIT_URI};branch=${LUCI_GIT_BRANCH};protocol=${LUCI_GIT_PROTOCOL};subpath=protocols/luci-proto-3g;destsuffix=git/"
SRCREV = "${LUCI_GIT_SRCREV}"
S = "${WORKDIR}/git"
