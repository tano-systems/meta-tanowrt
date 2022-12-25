#
# SPDX-License-Identifier: MIT
#
# Modbus RTU to Modbus TCP gateway LuCI application
#
# Copyright (c) 2020 Tano Systems LLC. All rights reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
PV = "1.0.0+git${SRCPV}"
PR = "tano0"

inherit allarch

SUMMARY = "Modbus RTU to Modbus TCP gateway LuCI application"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ae9f532425f745b67b03c5d2082fadb5"

RDEPENDS:${PN} += "mbusd"

GIT_BRANCH    = "master"
GIT_SRCREV    = "c5c5011bbdb309252634c2a1c8418d49d120a5d5"
GIT_PROTOCOL  = "https"
SRC_URI       = "git://github.com/tano-systems/luci-app-tn-mbusd.git;protocol=${GIT_PROTOCOL};branch=${GIT_BRANCH};name=app"
SRCREV_app    = "${GIT_SRCREV}"
SRCREV_FORMAT = "app"

S = "${WORKDIR}/git"

inherit tanowrt-luci-app
inherit tanowrt-luci-i18n
