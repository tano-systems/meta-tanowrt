#
# LuCI support for SNMP daemon
#
# This file Copyright (c) 2018-2020, Tano Systems. All Rights Reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
PR = "tano6"
PV = "0.9.0+git${SRCPV}"

SUMMARY = "LuCI support for SNMP daemon"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1fd756df4d1f5f2e8c17e9d2d4eaa503"

RDEPENDS_${PN} += "net-snmp-server-snmpd luci-compat"
RCONFLICTS_${PN} = "luci-app-snmpd"

inherit openwrt-luci-app
inherit openwrt-luci-i18n

GIT_BRANCH   = "master"
GIT_SRCREV   = "07d1f9124f7f31bf1206bed19f27883bbf56ba27"
GIT_PROTOCOL = "https"
SRC_URI = "git://github.com/tano-systems/luci-app-tn-snmpd.git;branch=${GIT_BRANCH};protocol=${GIT_PROTOCOL}"

SRCREV = "${GIT_SRCREV}"

S = "${WORKDIR}/git"

LUCI_APP_TN_SNMPD_HIDE_FOOTER ?= "1"

do_install_append() {
	install -d ${D}${sysconfdir}/uci-defaults

	UCIDEFFILE=${D}${sysconfdir}/uci-defaults/80_luci_app_tn_snmpd_footer

	echo "#!/bin/sh" > ${UCIDEFFILE}
	echo "uci -q batch <<-EOF >/dev/null" >> ${UCIDEFFILE}
	echo "    set luci.app_tn_snmpd=internal" >> ${UCIDEFFILE}
	echo "    set luci.app_tn_snmpd.hide_footer=${LUCI_APP_TN_SNMPD_HIDE_FOOTER}" >> ${UCIDEFFILE}
	echo "    commit luci" >> ${UCIDEFFILE}
	echo "EOF" >> ${UCIDEFFILE}
	echo "exit 0" >> ${UCIDEFFILE}
}
