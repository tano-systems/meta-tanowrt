#
# LuCI support for LLDP daemon
# OpenWrt/LEDE 18.06.x
#
# Copyright (c) 2018, Tano Systems. All Rights Reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
PV = "1.1.1"
PR = "tano8"

SUMMARY = "LuCI support for LLDP daemon"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=aed2cf5a7c273a7c2dcdbd491a3a8416"

GIT_BRANCH   = "master"
GIT_SRCREV   = "e6d09e55833ee6a20e46c45dff01afbe50d6676c"
GIT_PROTOCOL = "https"

SRC_URI = "git://github.com/tano-systems/luci-app-lldpd.git;branch=${GIT_BRANCH};protocol=${GIT_PROTOCOL}"
SRCREV = "${GIT_SRCREV}"

RDEPENDS_${PN} += "lldpd"

S = "${WORKDIR}/git"

inherit openwrt-luci-app
inherit openwrt-luci-i18n
inherit luasrcdiet
