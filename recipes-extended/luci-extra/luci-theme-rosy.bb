#
# LuCI OpenWrt.org Theme (default)
#
# This file Copyright (c) 2018, Tano Systems. All Rights Reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
# luci-theme-rosy
#     Copyright 2018 Rosy Song <rosysong@rosinson.com>
#     Copyright 2018 Yan Lan Shen <yanlan.shen@rosinson.com>
#
PR = "tano1"
DESCRIPTION = "LuCI Rosy Theme"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

inherit openwrt-luci-theme
inherit openwrt-luci-i18n

SRC_URI = "${LUCI_GIT_URI};branch=${LUCI_GIT_BRANCH};protocol=${LUCI_GIT_PROTOCOL};subpath=themes/luci-theme-rosy;destsuffix=git/"
SRCREV = "${LUCI_GIT_SRCREV}"
S = "${WORKDIR}/git"
