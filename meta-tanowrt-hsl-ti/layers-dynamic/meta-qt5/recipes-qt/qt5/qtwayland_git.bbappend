#
# SPDX-License-Identifier: MIT
# Copyright (c) 2020 Tano Systems LLC. All rights reserved.
#
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
PR_append = ".ti0"

SRC_URI += " \
    file://0001-plugins-decorations-bradient-display-window-icon-onl.patch \
    file://0001-Client-really-use-OpenGL-ES-2-API-for-decoration-bli.patch \
"
