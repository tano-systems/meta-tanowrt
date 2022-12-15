#
# SPDX-License-Identifier: MIT
# Copyright (c) 2020 Tano Systems LLC. All rights reserved.
#
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

PR:append:ls1028ardb = ".nxp0"

# Patches
SRC_URI:append:ls1028ardb = "\
	file://0001-jail-Fix-exec_jail-hangs-on-NXP-LS1028A-RDB.patch \
"
