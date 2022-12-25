#
# SPDX-License-Identifier: MIT
#
# Copyright (c) 2021 Tano Systems LLC. All rights reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
PR:append:am335x-bbb-emmc = ".ti0"
PR:append:am574x-idk-emmc = ".ti0"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/:"

COMPATIBLE_MACHINE:append = "|am335x-bbb-emmc|am574x-idk-emmc"
