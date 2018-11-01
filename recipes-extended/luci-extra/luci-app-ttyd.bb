#
# LuCI Support for ttyd
#
# This file Copyright (c) 2018, Tano Systems. All Rights Reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
PR = "tano6"

SUMMARY = "LuCI Support for ttyd"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=77aab877b104825ae877cbff4e362c04"

SRC_URI = "git://github.com/tano-systems/luci-app-ttyd.git;protocol=https"
SRCREV = "3d86bb51436081d1a58ddd92116d1dc09bc70387"
PV = "git${SRCPV}"

RDEPENDS_${PN} += "ttyd (>= 1.4.2)"

inherit openwrt-luci-app
inherit openwrt-luci-i18n
inherit luasrcdiet

S = "${WORKDIR}/git"
