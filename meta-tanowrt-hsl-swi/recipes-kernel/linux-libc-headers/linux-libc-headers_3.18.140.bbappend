#
# SPDX-License-Identifier: MIT
# Copyright (c) 2021-2022 Tano Systems LLC. All rights reserved.
#
PR:append = ".swi1"
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

LICENSE = "GPL-2.0-only"

SRC_URI += "\
	file://0001-libc-compat.h-prevent-redefinition-of-in6_addr-socka.patch \
"
