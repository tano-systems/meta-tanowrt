#
# SPDX-License-Identifier: MIT
#
# Copyright (c) 2021 Tano Systems LLC. All rights reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
PR:append:ls1028ardb-emmc = ".nxp0"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/:"

COMPATIBLE_MACHINE:append = "|ls1028ardb-emmc"
