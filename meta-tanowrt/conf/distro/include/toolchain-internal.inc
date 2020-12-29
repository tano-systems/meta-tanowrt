#
# SPDX-License-Identifier: MIT
# Copyright (c) 2018-2020 Tano Systems LLC. All rights reserved.
#

# Original file from meta-arago-distro

# No special handling is required for locating the internal toolchain, just re-use existing vars
TOOLCHAIN_PATH ?= "${STAGING_BINDIR_TOOLCHAIN}"
TOOLCHAIN_SYSPATH ?= "${TOOLCHAIN_PATH}/${TARGET_SYS}"
EXTERNAL_TOOLCHAIN = "${TOOLCHAIN_PATH}"
