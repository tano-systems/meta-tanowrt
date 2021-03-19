#
# SPDX-License-Identifier: MIT
#
# Copyright (c) 2021 Tano Systems LLC. All rights reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
PR_append_ls1028ardb = ".nxp0"

COMPATIBLE_MACHINE_append = "|ls1028ardb-sd|ls1028ardb-emmc"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/:"
