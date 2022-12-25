#
# SPDX-License-Identifier: MIT
#
# Copyright (c) 2021 Tano Systems LLC. All rights reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
PR:append:am335x-bbb = ".ti0"
PR:append:am335x-icev2 = ".ti0"
PR:append:am574x-idk = ".ti0"

COMPATIBLE_MACHINE:append = "|am335x-bbb|am335x-icev2|am574x-idk"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/:"
