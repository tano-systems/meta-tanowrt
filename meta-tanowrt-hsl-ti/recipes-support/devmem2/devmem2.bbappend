#
# SPDX-License-Identifier: MIT
# Copyright (c) 2019-2020 Tano Systems LLC. All rights reserved.
#
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += " \
file://0001-devmem2-support-different-page-sizes-at-run-time.patch \
"
