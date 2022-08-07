#
# SPDX-License-Identifier: MIT
# SPDX-FileCopyrightText: 2021 Tano Systems LLC
#
# Copyright (c) 2021-2022 Tano Systems LLC. All rights reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
PR_append_evb-ksz9477 = ".atmel1"
PR_append_at91-sama5d2-xplained = ".atmel1"
PR_append_at91-sama5d3-xplained = ".atmel1"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

COMPATIBLE_MACHINE = "evb-ksz9477|evb-ksz9563|at91-sama5d2-xplained|at91-sama5d3-xplained"
