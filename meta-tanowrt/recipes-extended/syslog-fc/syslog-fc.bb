#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2019-2020 Tano Systems LLC. All rights reserved.
# Authors: Anton Kikin <a.kikin@tano-systems.com>
#
# Syslog File Converter
#
PR = "tano0"
PV = "0.9.5+git${SRCPV}"

SUMMARY = "Syslog File Converter"
HOMEPAGE = "https://github.com/namedun/syslog_fc"
LICENSE = "WTFPL"
LIC_FILES_CHKSUM = "file://COPYING;md5=df8b20e1bbf83b9469c9e86895dc5e72"
SECTION = "base"

SRC_URI = "git://github.com/namedun/syslog_fc;branch=master;protocol=https"
SRCREV = "873ae0a6224e9b2829507966545be2e438f3a9d4"

S = "${WORKDIR}/git"

inherit cmake
