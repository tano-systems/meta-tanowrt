#
# SPDX-License-Identifier: MIT
# Copyright (c) 2018-2020 Tano Systems LLC. All rights reserved.
#

PR:append = ".tano4.${INC_PR}"

RCONFLICTS:${PN} = "openssh-sshd openssh"
RDEPENDS:${PN}-dev = ""

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/files:${THISDIR}/${PN}/patches:"
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}_${PV}/files:${THISDIR}/${PN}_${PV}/patches:"

require ${PN}-openwrt.inc
