#
# SPDX-License-Identifier: MIT
# Copyright (c) 2022 Tano Systems LLC. All rights reserved.
#
PR_append = ".tano0"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
SRC_URI += "git://github.com/rhinstaller/efibootmgr.git;protocol=https;branch=main"
SRC_URI += "file://0001-src-make-compatible-with-efivar-38.patch"

SRC_URI_remove = "git://github.com/rhinstaller/efibootmgr.git;protocol=https;branch=master"
