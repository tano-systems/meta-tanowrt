#
# SPDX-License-Identifier: MIT
# Copyright (c) 2022 Tano Systems LLC. All rights reserved.
#

PR:append = ".tano0"
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

do_compile[depends] += "virtual/kernel:do_shared_workdir"

SRC_URI += "\
	file://0001-Fix-compilation-for-kernels-below-5.4.patch;pnum=2 \
"
