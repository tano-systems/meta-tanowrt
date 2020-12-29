#
# SPDX-License-Identifier: MIT
# Copyright (c) 2020 Tano Systems LLC. All rights reserved.
#
require qrencode.inc

PV = "4.1.1"
PR = "tano0"

SRC_URI = "git://github.com/fukuchi/libqrencode.git;branch=master"
SRCREV = "715e29fd4cd71b6e452ae0f4e36d917b43122ce8"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"
