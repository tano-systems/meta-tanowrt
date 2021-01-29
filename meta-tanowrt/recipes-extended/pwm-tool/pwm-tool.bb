#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2021 Tano Systems LLC. All rights reserved.
# Authors: Anton Kikin <a.kikin@tano-systems.com>
#
# Linux command line PWM tool for buzzers
#
PR = "tano0"
PV = "1.0.1"

SUMMARY = "Linux command line PWM tool for buzzers"
HOMEPAGE = "https://github.com/tano-systems/pwm-tool"
LICENSE = "WTFPL"
LIC_FILES_CHKSUM = "file://COPYING;md5=df8b20e1bbf83b9469c9e86895dc5e72"
SECTION = "console/utils"

SRC_URI = "git://github.com/tano-systems/pwm-tool;branch=master"
SRCREV = "0cc46a05f4db32a31e075ecccd862fe2d91087df"
S = "${WORKDIR}/git"

inherit cmake
