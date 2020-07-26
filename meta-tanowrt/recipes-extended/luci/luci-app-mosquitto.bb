#
# LuCI Support for Mosquitto
#
# This file Copyright (c) 2019-2020 Tano Systems. All Rights Reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
PR = "tano3"

RDEPENDS_${PN} += "mosquitto luci-mod-admin-full luci-compat"

SUMMARY = "LuCI Support for Mosquitto"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

inherit allarch
inherit tanowrt-luci-app
inherit tanowrt-luci-i18n

SRC_URI = "${LUCI_GIT_URI};branch=${LUCI_GIT_BRANCH};protocol=${LUCI_GIT_PROTOCOL};subpath=applications/luci-app-mosquitto;destsuffix=git/"
SRCREV = "${LUCI_GIT_SRCREV}"
S = "${WORKDIR}/git"
