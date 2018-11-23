#
# LuCI Support for ttyd
#
# This file Copyright (c) 2018, Tano Systems. All Rights Reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
PR = "tano8"

SUMMARY = "LuCI Support for ttyd"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=77aab877b104825ae877cbff4e362c04"

SRC_URI = "git://github.com/tano-systems/luci-app-ttyd.git;protocol=https"
SRCREV = "34f2641223596e94a023ccbb43d6f5578c972e14"
PV = "git${SRCPV}"

RDEPENDS_${PN} += "ttyd (>= 1.4.2)"

inherit openwrt-luci-app
inherit openwrt-luci-i18n
inherit luasrcdiet

S = "${WORKDIR}/git"
