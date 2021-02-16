#
# SPDX-License-Identifier: MIT
#
# Copyright (c) 2021 Tano Systems LLC. All rights reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
PR_append_am335x-bbb-emmc = ".ti0"
PR_append_am574x-idk-emmc = ".ti0"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/:"

COMPATIBLE_MACHINE_append = "|am335x-bbb-emmc|am574x-idk-emmc"
