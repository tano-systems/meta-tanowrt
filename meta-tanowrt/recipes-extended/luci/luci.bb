# Copyright (C) 2019-2020 Anton Kikin <a.kikin@tano-systems.com>
# Released under the MIT license (see COPYING.MIT for the terms)

PR = "tano1"

inherit allarch

DESCRIPTION = "OpenWrt LuCI web user interface"
HOMEPAGE = "https://github.com/tano-systems/luci"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"
SECTION = "base"

FILES_${PN} = ""
ALLOW_EMPTY_${PN} = "1"

RDEPENDS_${PN} += "luci-mod-admin-full"
