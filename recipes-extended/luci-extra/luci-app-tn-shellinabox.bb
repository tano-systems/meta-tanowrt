#
# LuCI Support for shellinabox
#
# This file Copyright (c) 2018-2019, Tano Systems. All Rights Reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
PR = "tano2"

SUMMARY = "LuCI Support for shellinabox"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=77aab877b104825ae877cbff4e362c04"

SRC_URI = "git://github.com/tano-systems/luci-app-tn-shellinabox.git;protocol=https"
SRCREV = "f60508d10a864fa149795cac85d00b037defce96"
PV = "git${SRCPV}"

RDEPENDS_${PN} += "shellinabox"

inherit openwrt-luci-app
inherit openwrt-luci-i18n
inherit luasrcdiet

S = "${WORKDIR}/git"
