#
# LuCI Support for ttyd
#
# This file Copyright (c) 2018-2020, Tano Systems. All Rights Reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
PR = "tano14"

SUMMARY = "LuCI Support for ttyd"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=77aab877b104825ae877cbff4e362c04"

RCONFLICTS_${PN} = "luci-app-ttyd"

SRC_URI = "git://github.com/tano-systems/luci-app-tn-ttyd.git;protocol=https"
SRCREV = "7bbd62b661e7a25dc8e53712e2cdaf0b5c6abd35"
PV = "git${SRCPV}"

RDEPENDS_${PN} += "ttyd (>= 1.5.2) luci-compat"

inherit openwrt-luci-app
inherit openwrt-luci-i18n
inherit luasrcdiet

S = "${WORKDIR}/git"
