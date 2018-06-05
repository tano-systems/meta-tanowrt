#
# LuCI Support for ttyd
#
# This file Copyright (c) 2018, Tano Systems. All Rights Reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
PR = "tano0"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=4a9b415801f3f426a95d1da1f527882d"

RDEPENDS_${PN} += "ttyd (>= 1.4.0)"

GIT_TAG      = "1.4.0"
GIT_PROTOCOL = "https"

SRC_URI = "git://github.com/tsl0922/ttyd.git;tag=${GIT_TAG};protocol=${GIT_PROTOCOL}"

S = "${WORKDIR}/git"

LUCI_I18N_BASE = "${S}/openwrt/luci-app-terminal"
LUCI_APP_BASE = "${S}/openwrt/luci-app-terminal"

inherit openwrt-luci-app
inherit openwrt-luci-i18n
inherit openwrt-services

OPENWRT_SERVICE_PACKAGES = "${PN}"
OPENWRT_SERVICE_SCRIPTS_${PN} = "ttyd"
OPENWRT_SERVICE_STATE_${PN}-ttyd = "enabled"
