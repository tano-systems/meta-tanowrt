#
# LuCI support for SNMP daemon
#
# This file Copyright (c) 2018-2019, Tano Systems. All Rights Reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
PR = "tano0"
PV = "0.9.0"

SUMMARY = "LuCI support for SNMP daemon"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1fd756df4d1f5f2e8c17e9d2d4eaa503"

RDEPENDS_${PN} += "net-snmp-server-snmpd"
RCONFLICTS_${PN} = "luci-app-snmpd"

inherit openwrt-luci-app
inherit openwrt-luci-i18n
inherit luasrcdiet

GIT_BRANCH   = "master"
GIT_SRCREV   = "7f59b508af1e3dcb45a1e2e734d1e96202dbfabd"
GIT_PROTOCOL = "https"
SRC_URI = "git://github.com/tano-systems/luci-app-snmpd-tn.git;branch=${GIT_BRANCH};protocol=${GIT_PROTOCOL}"

SRCREV = "${GIT_SRCREV}"

S = "${WORKDIR}/git"

