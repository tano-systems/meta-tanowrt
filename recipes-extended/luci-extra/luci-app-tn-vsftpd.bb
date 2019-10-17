#
# LuCI support for VSFTP server
#
# Copyright (c) 2018, Tano Systems. All Rights Reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
PV = "1.0.0+git${SRCPV}"
PR = "tano9"

SUMMARY = "LuCI support for VSFTP server"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=aed2cf5a7c273a7c2dcdbd491a3a8416"

GIT_BRANCH   = "master"
GIT_SRCREV   = "ed6f305bc64d78160a4705bb296314cfe2b80582"
GIT_PROTOCOL = "https"
SRC_URI = "git://github.com/tano-systems/luci-app-tn-vsftpd.git;branch=${GIT_BRANCH};protocol=${GIT_PROTOCOL}"

SRCREV = "${GIT_SRCREV}"

RDEPENDS_${PN} += "vsftpd"

S = "${WORKDIR}/git"

inherit openwrt-luci-app
inherit openwrt-luci-i18n
inherit luasrcdiet
