#
# LuCI Support for shellinabox
#
# This file Copyright (c) 2018-2020, Tano Systems. All Rights Reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
PR = "tano7"

SUMMARY = "LuCI Support for shellinabox"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=77aab877b104825ae877cbff4e362c04"

SRC_URI = "git://github.com/tano-systems/luci-app-tn-shellinabox.git;protocol=https"
SRCREV = "7712e6c186745442fcb7255ddc7bd26813897dfc"
PV = "git${SRCPV}"

RDEPENDS_${PN} += "shellinabox"

inherit openwrt-luci-app
inherit openwrt-luci-i18n

S = "${WORKDIR}/git"
