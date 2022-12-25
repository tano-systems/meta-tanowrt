#
# SPDX-License-Identifier: MIT
# Copyright (c) 2022 Tano Systems LLC. All rights reserved.
#
PR:append = ".tano1"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"
SRC_URI += "git://github.com/rhinstaller/efibootmgr.git;protocol=https;branch=main"

SRC_URI:remove = "git://github.com/rhinstaller/efibootmgr.git;protocol=https;branch=master"
