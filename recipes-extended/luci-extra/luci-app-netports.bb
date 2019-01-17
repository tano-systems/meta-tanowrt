#
# Network ports status LuCI application
#
# Copyright (c) 2018, Tano Systems. All Rights Reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
PV = "1.0.0-git${SRCPV}"
PR = "tano5"

SUMMARY = "Network ports status LuCI application"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=aed2cf5a7c273a7c2dcdbd491a3a8416"

GIT_BRANCH   = "master"
GIT_SRCREV   = "58885edfb23a416d78595c21b5b639ee5069e763"
GIT_PROTOCOL = "https"
SRC_URI = "git://github.com/tano-systems/luci-app-netports.git;branch=${GIT_BRANCH};protocol=${GIT_PROTOCOL}"

SRCREV = "${GIT_SRCREV}"

CONFFILES_${PN} = "${sysconfdir}/config/luci_netports"

S = "${WORKDIR}/git"

inherit openwrt-luci-app
inherit openwrt-luci-i18n
inherit luasrcdiet
