#
# SPDX-License-Identifier: MIT
# Copyright (c) 2021-2022 Tano Systems LLC. All rights reserved.
#
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/files:"

PR:append:evb-ksz9477 = ".atmel1"
PR:append:evb-ksz9563 = ".atmel1"
PR:append:at91-sama5d2-xplained = ".atmel0"
PR:append:at91-sama5d3-xplained = ".atmel0"
