#
# SPDX-License-Identifier: MIT
#
# Copyright (c) 2021-2022 Tano Systems LLC. All rights reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
PR:append:evb-ksz-sd = ".atmel0"
PR:append:evb-ksz-nand = ".atmel0"
PR:append:at91-sama5d2-xplained = ".atmel0"
PR:append:at91-sama5d3-xplained = ".atmel0"

COMPATIBLE_MACHINE:append = "|evb-ksz|at91-sama5d2-xplained|at91-sama5d3-xplained"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/:"
