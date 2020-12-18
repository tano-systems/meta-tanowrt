# Copyright (C) 2020 Anton Kikin <a.kikin@tano-systems.com>
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

PR_append_ls1028ardb = ".nxp0"

# Patches
SRC_URI_append_ls1028ardb = "\
	file://0001-jail-Fix-exec_jail-hangs-on-NXP-LS1028A-RDB.patch \
"
