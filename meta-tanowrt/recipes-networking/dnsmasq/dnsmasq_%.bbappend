#
# SPDX-License-Identifier: MIT
# This file Copyright (C) 2018-2019 Anton Kikin <a.kikin@tano-systems.com>
#
PR:append = ".tano0.${INC_PR}"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}_${PV}/files:${THISDIR}/${PN}_${PV}/patches:"

require ${PN}-openwrt.inc
