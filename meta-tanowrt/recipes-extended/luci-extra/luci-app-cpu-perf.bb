#
# SPDX-License-Identifier: MIT
#
# CPU performance LuCI application
#
# Copyright (c) 2022 Tano Systems LLC. All rights reserved.
# Aleksandr Kobelev <kobelev.an8@mail.ru>
#
PV = "0.0.0+git${SRCPV}"
PR = "tano0"

inherit allarch
inherit tanowrt-luci-app
inherit tanowrt-luci-i18n

SUMMARY = "CPU performance LuCI application"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b5548495bc4b6bd914d78d5409801041"

GIT_BRANCH   = "master"
GIT_SRCREV   = "87e4c3c6f4f9d22d84657bfb220a03ba06d92331"
GIT_PROTOCOL = "https"
SRC_URI = "git://github.com/tano-systems/luci-app-cpu-perf.git;branch=${GIT_BRANCH};protocol=${GIT_PROTOCOL}"

SRCREV = "${GIT_SRCREV}"


S = "${WORKDIR}/git"
