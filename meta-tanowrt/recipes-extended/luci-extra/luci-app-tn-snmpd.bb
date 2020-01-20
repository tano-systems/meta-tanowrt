#
# LuCI support for SNMP daemon
#
# This file Copyright (c) 2018-2020, Tano Systems. All Rights Reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
PR = "tano4"
PV = "0.9.0+git${SRCPV}"

SUMMARY = "LuCI support for SNMP daemon"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1fd756df4d1f5f2e8c17e9d2d4eaa503"

RDEPENDS_${PN} += "net-snmp-server-snmpd luci-compat"
RCONFLICTS_${PN} = "luci-app-snmpd"

inherit openwrt-luci-app
inherit openwrt-luci-i18n
inherit luasrcdiet

GIT_BRANCH   = "master"
GIT_SRCREV   = "1db7115c2b7b74072e47e20b796e0ceb69170713"
GIT_PROTOCOL = "https"
SRC_URI = "git://github.com/tano-systems/luci-app-tn-snmpd.git;branch=${GIT_BRANCH};protocol=${GIT_PROTOCOL}"

SRCREV = "${GIT_SRCREV}"

S = "${WORKDIR}/git"

