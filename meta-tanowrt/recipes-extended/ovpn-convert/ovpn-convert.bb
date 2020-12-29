#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2020 Tano Systems LLC. All rights reserved.
# Authors: Anton Kikin <a.kikin@tano-systems.com>
#
# OpenVPN Configuration Files Converter
#
PR = "tano0"
PV = "0.9.0+git${SRCPV}"

SUMMARY = "OpenVPN Configuration File Converter"
HOMEPAGE = "https://github.com/namedun/ovpn-convert"
LICENSE = "WTFPL"
LIC_FILES_CHKSUM = "file://COPYING;md5=df8b20e1bbf83b9469c9e86895dc5e72"
SECTION = "utils"

SRC_URI = "git://github.com/namedun/ovpn-convert;branch=master;protocol=https"
SRCREV = "06883fe155207a933c6d3715d48560b1e4e3421d"

S = "${WORKDIR}/git"

inherit cmake
inherit gettext

DEPENDS += "json-c"

EXTRA_OECMAKE = "\
	-DCMAKE_BUILD_TYPE=Release \
	-DCMAKE_INSTALL_LOCALEDIR=${datadir}/locale \
"
