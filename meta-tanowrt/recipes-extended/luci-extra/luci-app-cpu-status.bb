#
# SPDX-License-Identifier: MIT
#
# CPU status LuCI application
#
# Copyright (c) 2022 Tano Systems LLC. All rights reserved.
# Aleksandr Kobelev <kobelev.an8@mail.ru>
#
PV = "0.0.0+git${SRCPV}"
PR = "tano0"

inherit allarch
inherit tanowrt-luci-app
inherit tanowrt-luci-i18n

SUMMARY = "CPU status LuCI application"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=55d572fe511aa3a92159cdfd11b77edd"

GIT_BRANCH   = "master"
GIT_SRCREV   = "e0830ba0c37e766feb5339d1dc0c06a359a33ac8"
GIT_PROTOCOL = "https"
SRC_URI = "git://github.com/tano-systems/luci-app-cpu-status.git;branch=${GIT_BRANCH};protocol=${GIT_PROTOCOL}"

SRCREV = "${GIT_SRCREV}"


S = "${WORKDIR}/git"
