#
# SPDX-License-Identifier: MIT
# Copyright (c) 2020 Tano Systems LLC. All rights reserved.
#

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/files:${THISDIR}/${PN}/patches:"
PR:append = ".tano0"
SRC_URI += "file://500-world-regd-5GHz.patch"
