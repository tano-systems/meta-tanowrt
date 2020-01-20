#
# LuCI support for VSFTP server
#
# Copyright (c) 2018-2020, Tano Systems. All Rights Reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
PV = "1.0.0+git${SRCPV}"
PR = "tano11"

SUMMARY = "LuCI support for VSFTP server"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=aed2cf5a7c273a7c2dcdbd491a3a8416"

GIT_BRANCH   = "master"
GIT_SRCREV   = "a194b399dbcfbe6c3b4f44273c60af9eeaa00634"
GIT_PROTOCOL = "https"
SRC_URI = "git://github.com/tano-systems/luci-app-tn-vsftpd.git;branch=${GIT_BRANCH};protocol=${GIT_PROTOCOL}"

SRCREV = "${GIT_SRCREV}"

RDEPENDS_${PN} += "vsftpd luci-compat"

S = "${WORKDIR}/git"

inherit openwrt-luci-app
inherit openwrt-luci-i18n
inherit luasrcdiet
