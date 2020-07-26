#
# LuCI support for watchdog configuration
#
# Copyright (c) 2019-2020, Tano Systems. All Rights Reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
PV = "0.9.0+git${SRCPV}"
PR = "tano8"

SUMMARY = "LuCI support for watchdog configuration"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1fd756df4d1f5f2e8c17e9d2d4eaa503"

GIT_BRANCH   = "master"
GIT_SRCREV   = "a3b83b0203f3bbf00549b35311c725e3e4f1b49b"
GIT_PROTOCOL = "https"
SRC_URI      = "git://github.com/tano-systems/luci-app-tn-watchdog.git;branch=${GIT_BRANCH};protocol=${GIT_PROTOCOL}"

SRCREV = "${GIT_SRCREV}"

S = "${WORKDIR}/git"

inherit allarch
inherit tanowrt-luci-app
inherit tanowrt-luci-i18n
