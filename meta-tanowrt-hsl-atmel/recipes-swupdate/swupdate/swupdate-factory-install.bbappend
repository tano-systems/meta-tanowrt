#
# SPDX-License-Identifier: MIT
#
# Copyright (c) 2021-2022 Tano Systems LLC. All rights reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
PR:append:evb-ksz-nand = ".atmel0"
PR:append:at91-sama5d2-xplained-emmc = ".atmel0"
PR:append:at91-sama5d3-xplained-nand = ".atmel0"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/:"

COMPATIBLE_MACHINE:append = "|evb-ksz-nand|at91-sama5d2-xplained-emmc|at91-sama5d3-xplained-nand"
