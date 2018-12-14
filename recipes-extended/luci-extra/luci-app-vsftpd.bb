#
# LuCI support for VSFTP server
#
# Copyright (c) 2018, Tano Systems. All Rights Reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
PV = "1.0.0"
PR = "tano5"

SUMMARY = "LuCI support for VSFTP server"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=aed2cf5a7c273a7c2dcdbd491a3a8416"

GIT_BRANCH   = "master"
GIT_SRCREV   = "9e9714484963938f3d5b8920b14cb72002e0169d"
GIT_PROTOCOL = "https"
SRC_URI = "git://github.com/tano-systems/luci-app-vsftpd.git;branch=${GIT_BRANCH};protocol=${GIT_PROTOCOL}"

SRCREV = "${GIT_SRCREV}"

RDEPENDS_${PN} += "vsftpd"

S = "${WORKDIR}/git"

inherit openwrt-luci-app
inherit openwrt-luci-i18n
inherit luasrcdiet
