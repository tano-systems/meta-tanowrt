#
# Legacy opkg interface class
#
# This file Copyright (c) 2019-2020 Tano Systems. All Rights Reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
PR = "tano1"

SUMMARY = "Legacy opkg interface class"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

DEPENDS += "lua5.1-native lua5.1"

inherit allarch
inherit tanowrt-luci-lib
inherit tanowrt-lua

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI = "${LUCI_GIT_URI};branch=${LUCI_GIT_BRANCH};protocol=${LUCI_GIT_PROTOCOL};subpath=libs/luci-lib-ipkg;destsuffix=git/"
SRC_URI += "file://cmake.patch;pnum=3"
SRCREV = "${LUCI_GIT_SRCREV}"
S = "${WORKDIR}/git"
