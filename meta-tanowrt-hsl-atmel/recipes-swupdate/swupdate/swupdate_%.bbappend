#
# SPDX-License-Identifier: MIT
#
# Copyright (c) 2021-2022 Tano Systems LLC. All rights reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
PR_append_evb-ksz-sd = ".atmel0"
PR_append_evb-ksz-nand = ".atmel0"
PR_append_at91-sama5d2-xplained = ".atmel0"

COMPATIBLE_MACHINE_append = "|evb-ksz|at91-sama5d2-xplained"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/:"
