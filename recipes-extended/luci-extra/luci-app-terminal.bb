#
# LuCI Support for ttyd
#
# This file Copyright (c) 2018, Tano Systems. All Rights Reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
PR = "tano4"

SUMMARY = "LuCI Support for ttyd"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=4a9b415801f3f426a95d1da1f527882d"

GIT_TAG      = "1.4.0"
GIT_PROTOCOL = "https"

SRC_URI = "git://github.com/tsl0922/ttyd.git;tag=${GIT_TAG};protocol=${GIT_PROTOCOL}"

RDEPENDS_${PN} += "ttyd (>= 1.4.0)"

inherit openwrt-luci-app
inherit openwrt-luci-i18n
inherit openwrt-services
inherit luasrcdiet

OPENWRT_SERVICE_PACKAGES = "luci-app-terminal"
OPENWRT_SERVICE_SCRIPTS_luci-app-terminal += "ttyd"
OPENWRT_SERVICE_STATE_luci-app-terminal-ttyd ?= "enabled"

S = "${WORKDIR}/git"

LUCI_PKG_SRC = "${S}/openwrt/luci-app-terminal"

