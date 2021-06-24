#
# SPDX-License-Identifier: MIT
# Copyright (C) 2021 Tano Systems LLC. All Rights Reserved.
#

PR_append = ".1"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

# Patches
SRC_URI_append = "\
	file://0001-Switch-to-cgroup-v1.patch \
"
