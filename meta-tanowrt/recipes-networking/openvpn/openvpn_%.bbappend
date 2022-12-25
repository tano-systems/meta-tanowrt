#
# SPDX-License-Identifier: MIT
# Copyright (c) 2018 Tano Systems LLC. All rights reserved.
#
PR:append = ".tano6.${INC_PR}"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"
require ${PN}-openwrt.inc
