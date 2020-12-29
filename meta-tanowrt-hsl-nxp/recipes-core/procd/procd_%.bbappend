#
# SPDX-License-Identifier: MIT
# Copyright (c) 2020 Tano Systems LLC. All rights reserved.
#
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

PR_append_ls1028ardb = ".nxp0"

# Patches
SRC_URI_append_ls1028ardb = "\
	file://0001-jail-Fix-exec_jail-hangs-on-NXP-LS1028A-RDB.patch \
"
