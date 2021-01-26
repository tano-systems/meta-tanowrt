#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2021 Tano Systems LLC. All rights reserved.
# Authors: Anton Kikin <a.kikin@tano-systems.com>
#
# Linux command line PWM tool for buzzers
#
PR = "tano0"
PV = "1.0.0"

SUMMARY = "Linux command line PWM tool for buzzers"
HOMEPAGE = "https://github.com/tano-systems/pwm-tool"
LICENSE = "WTFPL"
LIC_FILES_CHKSUM = "file://COPYING;md5=df8b20e1bbf83b9469c9e86895dc5e72"
SECTION = "console/utils"

SRC_URI = "git://github.com/tano-systems/pwm-tool;branch=master"
SRCREV = "83fd27a618daec421494a91f356d8c59dd30cdd0"
S = "${WORKDIR}/git"

inherit cmake
