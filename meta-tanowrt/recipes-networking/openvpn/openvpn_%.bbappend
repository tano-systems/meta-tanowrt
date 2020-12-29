#
# SPDX-License-Identifier: MIT
# Copyright (c) 2018 Tano Systems LLC. All rights reserved.
#
PR_append = ".tano6.${INC_PR}"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"
require ${PN}-openwrt.inc
