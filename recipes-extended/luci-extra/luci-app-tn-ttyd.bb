#
# LuCI Support for ttyd
#
# This file Copyright (c) 2018-2019, Tano Systems. All Rights Reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
PR = "tano12"

SUMMARY = "LuCI Support for ttyd"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=77aab877b104825ae877cbff4e362c04"

RCONFLICTS_${PN} = "luci-app-ttyd"

SRC_URI = "git://github.com/tano-systems/luci-app-tn-ttyd.git;protocol=https"
SRCREV = "6d1770c391959f957cd71515ad9c6ea95110361f"
PV = "git${SRCPV}"

RDEPENDS_${PN} += "ttyd (>= 1.5.2)"

inherit openwrt-luci-app
inherit openwrt-luci-i18n
inherit luasrcdiet

S = "${WORKDIR}/git"
