#
# SPDX-License-Identifier: MIT
# Copyright (c) 2020-2022 Tano Systems LLC. All rights reserved.
#
PR:append = ".atmel0"

COMPATIBLE_MACHINE = "evb-ksz9477|evb-ksz9563|at91-sama5d2-xplained|at91-sama5d3-xplained"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/:"
