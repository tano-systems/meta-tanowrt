#
# SPDX-License-Identifier: MIT
#
# LuCI Support for ttyd
#
# This file Copyright (c) 2018-2020 Tano Systems LLC. All rights reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
PR = "tano15"

SUMMARY = "LuCI Support for ttyd"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=77aab877b104825ae877cbff4e362c04"

RCONFLICTS_${PN} = "luci-app-ttyd"

SRC_URI = "git://github.com/tano-systems/luci-app-tn-ttyd.git;protocol=https"
SRCREV = "50fa5197569ece3ce1c1bc505164d067eb9f5914"
PV = "git${SRCPV}"

RDEPENDS_${PN} += "ttyd (>= 1.5.2) luci-compat"

inherit allarch
inherit tanowrt-luci-app
inherit tanowrt-luci-i18n

S = "${WORKDIR}/git"
