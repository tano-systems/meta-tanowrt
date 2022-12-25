#
# SPDX-License-Identifier: MIT
#
# Copyright (c) 2021-2022 Tano Systems LLC. All rights reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
PR:append:mitx = ".baikal2"

COMPATIBLE_MACHINE:append = "|mitx"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/:"
