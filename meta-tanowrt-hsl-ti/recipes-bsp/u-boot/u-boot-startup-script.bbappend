#
# SPDX-License-Identifier: MIT
# SPDX-FileCopyrightText: 2021 Tano Systems LLC
#
# Copyright (c) 2021 Tano Systems LLC. All rights reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
PR_append_am335x-bbb = ".ti2"
PR_append_am335x-icev2 = ".ti2"
PR_append_am574x-idk = ".ti1"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

COMPATIBLE_MACHINE = "am335x-bbb|am335x-icev2|am574x-idk"
