#
# LuCI support for MSTP daemon
#
# Copyright (c) 2018, Tano Systems. All Rights Reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
PV = "1.0.0"
PR = "tano4"

SUMMARY = "LuCI support for MSTP daemon"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=aed2cf5a7c273a7c2dcdbd491a3a8416"

GIT_BRANCH   = "master"
GIT_SRCREV   = "fbb534b86f302240477bfcac78dd769e5173c308"
GIT_PROTOCOL = "https"
SRC_URI = "git://github.com/tano-systems/luci-app-mstpd.git;branch=${GIT_BRANCH};protocol=${GIT_PROTOCOL}"

SRCREV = "${GIT_SRCREV}"

RDEPENDS_${PN} += "mstpd"

S = "${WORKDIR}/git"

inherit openwrt-luci-app
inherit openwrt-luci-i18n
inherit luasrcdiet
