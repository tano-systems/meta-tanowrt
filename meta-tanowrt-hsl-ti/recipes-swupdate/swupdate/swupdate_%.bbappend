#
# SPDX-License-Identifier: MIT
#
# Copyright (c) 2021 Tano Systems LLC. All rights reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
PR_append_am335x-bbb = ".ti0"
PR_append_am335x-icev2 = ".ti0"
PR_append_am574x-idk = ".ti0"

COMPATIBLE_MACHINE_append = "|am335x-bbb|am335x-icev2|am574x-idk"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/:"
