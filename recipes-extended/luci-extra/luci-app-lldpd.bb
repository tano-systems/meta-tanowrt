#
# LuCI support for LLDP daemon
#
# Copyright (c) 2018, Tano Systems. All Rights Reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
PR = "tano9"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=aed2cf5a7c273a7c2dcdbd491a3a8416"

GIT_BRANCH   = "master"
GIT_SRCREV   = "f30ad6f2d93f2b6c13ccd4aa133cfec895221f45"
GIT_PROTOCOL = "https"

SRC_URI = "git://github.com/tano-systems/luci-app-lldpd.git;branch=${GIT_BRANCH};protocol=${GIT_PROTOCOL}"
SRCREV = "${GIT_SRCREV}"

RDEPENDS_${PN} += "lldpd"

S = "${WORKDIR}/git"

inherit openwrt-luci-app
inherit openwrt-luci-i18n
