#
# LuCI Support for ttyd
#
# This file Copyright (c) 2018-2019, Tano Systems. All Rights Reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
PR = "tano10"

SUMMARY = "LuCI Support for ttyd"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=77aab877b104825ae877cbff4e362c04"

SRC_URI = "git://github.com/tano-systems/luci-app-ttyd.git;protocol=https"
SRCREV = "fa8dbcaa5c55a630af02449a746b46483d595f08"
PV = "git${SRCPV}"

RDEPENDS_${PN} += "ttyd (>= 1.5.2)"

inherit openwrt-luci-app
inherit openwrt-luci-i18n
inherit luasrcdiet

S = "${WORKDIR}/git"
