#
# SPDX-License-Identifier: MIT
#
# Copyright (c) 2021 Tano Systems LLC. All rights reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
PR_append_evb-ksz-sd = ".atmel0"
PR_append_evb-ksz-nand = ".atmel0"

COMPATIBLE_MACHINE_append = "|evb-ksz"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/:"
