#
# SPDX-License-Identifier: MIT
# Copyright (c) 2021-2022 Tano Systems LLC. All rights reserved.
#
PR:append:evb-ksz = ".atmel0"
PR:append:at91-sama5d2-xplained = ".atmel0"
PR:append:at91-sama5d3-xplained = ".atmel0"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/files:"
