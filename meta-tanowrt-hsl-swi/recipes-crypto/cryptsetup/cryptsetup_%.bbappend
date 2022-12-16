#
# SPDX-License-Identifier: MIT
# Copyright (c) 2022 Tano Systems LLC. All rights reserved.
#

PR:append = ".swi0"
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI:append:class-native = " file://0001-lib-Disable-direct-io.patch"
