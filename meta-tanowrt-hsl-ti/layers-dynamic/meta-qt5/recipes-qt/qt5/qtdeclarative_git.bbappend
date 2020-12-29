#
# SPDX-License-Identifier: MIT
# Copyright (c) 2020 Tano Systems LLC. All rights reserved.
#
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
PR_append = ".ti0"

CXXFLAGS_append_omap-a15 = " -DQ_OS_BLACKBERRY"

SRC_URI += " \
    file://0001-touchinteraction.qml-Add-exit-button.patch \
"

