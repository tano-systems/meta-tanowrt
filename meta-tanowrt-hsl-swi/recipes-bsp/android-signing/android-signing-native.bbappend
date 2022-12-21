#
# SPDX-License-Identifier: MIT
# Copyright (c) 2022 Tano Systems LLC. All rights reserved.
#
PR:append = ".swi0"
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI:remove = "git://github.com/SierraWireless/android-signing.git;protocol=https"
SRC_URI:append = " git://github.com/SierraWireless/android-signing.git;protocol=https;branch=master"
