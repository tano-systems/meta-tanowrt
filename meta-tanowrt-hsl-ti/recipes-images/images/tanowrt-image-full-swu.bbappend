#
# SPDX-License-Identifier: MIT
# Copyright (c) 2020-2021 Tano Systems LLC. All rights reserved.
#
PR:append = ".ti0"

COMPATIBLE_MACHINE = "am335x-bbb|am335x-icev2|am574x-idk"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/:"
