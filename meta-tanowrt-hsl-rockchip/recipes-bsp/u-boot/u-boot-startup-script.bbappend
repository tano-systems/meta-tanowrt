#
# SPDX-License-Identifier: MIT
# SPDX-FileCopyrightText: 2022 Tano Systems LLC
#
# Copyright (c) 2022 Tano Systems LLC. All rights reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
PR_append = ".rk0"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

COMPATIBLE_MACHINE = "boardcon-em3566|boardcon-em3568|rock-pi-s"

inherit vars-expander
EXPAND_VARIABLES_append = "${TANOWRT_PARTUUID_VARS}"

SRC_URI_remove = "file://defs.m4"
SRC_URI_append = " file://defs.m4.in "
