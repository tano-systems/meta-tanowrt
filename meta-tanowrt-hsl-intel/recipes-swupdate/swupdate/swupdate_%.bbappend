#
# SPDX-License-Identifier: MIT
#
# Copyright (c) 2022 Tano Systems LLC. All rights reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
PR:append = ".intel0"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/:"

COMPATIBLE_MACHINE:append = "|intel-x86-common"
