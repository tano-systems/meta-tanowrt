#
# SPDX-License-Identifier: MIT
# Copyright (c) 2022 Tano Systems LLC. All rights reserved.
#
PR:append = ".intel0"

COMPATIBLE_MACHINE = "intel-x86-common"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/:"
