#
# LuCI support for LLDP daemon
#
# Copyright (c) 2018, Tano Systems. All Rights Reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
PR = "tano8"

LICENSE = "CLOSED"

GIT_BRANCH = "develop"
GIT_SRCREV = "${AUTOREV}"
GIT_PROTOCOL="https"

SRC_URI = "git://github.com/tano-systems/luci-app-lldpd.git;branch=${GIT_BRANCH};protocol=${GIT_PROTOCOL}"
SRCREV = "${GIT_SRCREV}"

RDEPENDS_${PN} += "lldpd"

S = "${WORKDIR}/git"

inherit openwrt-luci-app
inherit openwrt-luci-i18n
