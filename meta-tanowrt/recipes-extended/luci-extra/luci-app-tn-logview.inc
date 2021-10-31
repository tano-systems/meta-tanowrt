#
# SPDX-License-Identifier: MIT
#
# Copyright (c) 2020-2021 Tano Systems LLC. All rights reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#

inherit allarch
inherit tanowrt-luci-app
inherit tanowrt-luci-i18n

SUMMARY = "Log viewer LuCI application"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=35d2a22ecae06415d0af96aa3af51793"

GIT_URI      = "git://github.com/tano-systems/luci-app-tn-logview.git"
GIT_BRANCH   = "master"
GIT_PROTOCOL = "https"
GIT_SRCREV   = "ac998316d3af0a06829e3d4e3689d4379c8d0301"

SRC_URI = "${GIT_URI};branch=${GIT_BRANCH};protocol=${GIT_PROTOCOL}"
SRCREV = "${GIT_SRCREV}"

S = "${WORKDIR}/git"

PV = "0.9.0+git${SRCPV}"
PR = "tano12"

LUCI_PKG_EXECUTABLE += "${D}/usr/libexec/luci-logview/*"
