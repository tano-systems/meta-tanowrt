#
# Net-SNMP LuCI interface
#
# This file Copyright (c) 2018, Tano Systems. All Rights Reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
PR = "tano3"

SUMMARY = "Net-SNMP LuCI interface"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

RDEPENDS_${PN} += "net-snmp-server-snmpd"
RCONFLICTS_${PN} = "luci-app-tn-snmpd"

inherit openwrt-luci-app
inherit openwrt-luci-i18n
inherit luasrcdiet

SRC_URI = "${LUCI_GIT_URI};branch=${LUCI_GIT_BRANCH};protocol=${LUCI_GIT_PROTOCOL};subpath=applications/luci-app-snmpd;destsuffix=git/"
SRCREV = "${LUCI_GIT_SRCREV}"
S = "${WORKDIR}/git"
