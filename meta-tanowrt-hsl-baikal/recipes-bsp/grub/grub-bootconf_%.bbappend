#
# SPDX-License-Identifier: MIT
# Copyright (c) 2021-2022 Tano Systems LLC. All rights reserved.
#

PR_append = ".baikal2"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

inherit vars-expander
EXPAND_VARIABLES:append = "${TANOWRT_PARTUUID_VARS}"

SRC_URI:remove = "file://grub.cfg"
SRC_URI:append = " file://grub.cfg.in"
