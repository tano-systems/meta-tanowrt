#
# LuCI support for watchdog configuration
#
# Copyright (c) 2019, Tano Systems. All Rights Reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
PV = "0.9.0+git${SRCPV}"
PR = "tano0"

SUMMARY = "LuCI support for watchdog configuration"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1fd756df4d1f5f2e8c17e9d2d4eaa503"

GIT_BRANCH   = "master"
GIT_SRCREV   = "997d5aca1530b0d072c12b663d39f05fa4995e36"
GIT_PROTOCOL = "https"
SRC_URI      = "git://github.com/tano-systems/luci-app-watchdog.git;branch=${GIT_BRANCH};protocol=${GIT_PROTOCOL}"

SRCREV = "${GIT_SRCREV}"

#RDEPENDS_${PN} += "busybox-watchdog"

S = "${WORKDIR}/git"

inherit openwrt-luci-app
inherit openwrt-luci-i18n
inherit luasrcdiet
