#
# SPDX-License-Identifier: MIT
#
# Apinger LuCI application
#
# Copyright (c) 2022 Tano Systems LLC. All rights reserved.
# Aleksandr Kobelev <kobelev.an8@mail.ru>
#
PR = "tano0"

RDEPENDS:${PN} += "apinger"

inherit allarch
inherit tanowrt-luci-app
inherit tanowrt-luci-i18n

SUMMARY = "Apinger LuCI application"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/GPL-2.0-only;md5=801f80980d171dd6425610833a22dbe6"

SRC_URI = "${LUCI_GIT_URI};branch=${LUCI_GIT_BRANCH};protocol=${LUCI_GIT_PROTOCOL};subpath=applications/luci-app-apinger"
SRCREV = "${LUCI_GIT_SRCREV}"

S = "${WORKDIR}/git"
