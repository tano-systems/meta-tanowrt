#
# SPDX-License-Identifier: MIT
# Copyright (c) 2021 Tano Systems LLC. All rights reserved.
#
PR:append:ls1028ardb = ".nxp0"

COMPATIBLE_MACHINE = "ls1028ardb-sd|ls1028ardb-emmc"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/:"
