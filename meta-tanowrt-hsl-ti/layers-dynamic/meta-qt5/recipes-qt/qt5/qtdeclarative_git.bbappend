#
# SPDX-License-Identifier: MIT
# Copyright (c) 2020 Tano Systems LLC. All rights reserved.
#
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"
PR:append = ".ti0"

CXXFLAGS:append:omap-a15 = " -DQ_OS_BLACKBERRY"

SRC_URI += " \
    file://0001-touchinteraction.qml-Add-exit-button.patch \
"

