#
# SPDX-License-Identifier: MIT
# Copyright (c) 2019-2020 Tano Systems LLC. All rights reserved.
#
require qrencode.inc

PV = "4.0.2"
PR = "tano0.${INC_PR}"

SRC_URI = "git://github.com/fukuchi/libqrencode.git;branch=4.0;protocol=https"
SRCREV = "59ee597f913fcfda7a010a6e106fbee2595f68e4"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"
SRC_URI += "file://001-add-inline-svg.patch"
