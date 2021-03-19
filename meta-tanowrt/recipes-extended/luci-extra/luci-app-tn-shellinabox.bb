#
# SPDX-License-Identifier: MIT
#
# LuCI Support for shellinabox
#
# This file Copyright (c) 2018-2020 Tano Systems LLC. All rights reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
PR = "tano9"

SUMMARY = "LuCI Support for shellinabox"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=77aab877b104825ae877cbff4e362c04"

SRC_URI = "git://github.com/tano-systems/luci-app-tn-shellinabox.git;protocol=https"
SRCREV = "c1272c528a83c758cf4342f5819c47f16c4ee609"
PV = "git${SRCPV}"

RDEPENDS_${PN} += "shellinabox"

inherit allarch
inherit tanowrt-luci-app
inherit tanowrt-luci-i18n

S = "${WORKDIR}/git"
