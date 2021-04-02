#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2021 Tano Systems LLC. All rights reserved.
# Authors: Anton Kikin <a.kikin@tano-systems.com>
#

PR_append = ".0"

COMPATIBLE_MACHINE = "(sama5)"

KERNEL_VERSION_SANITY_SKIP = "1"

FILESEXTRAPATHS_prepend = "${TANOWRT_HSL_BASE}/recipes-kernel/linux/features:"

KERNEL_MACHINE_INCLUDE_evb-ksz9477 ?= "recipes-kernel/linux/linux-tano-at91-evb-ksz9477.inc"
KERNEL_MACHINE_INCLUDE_evb-ksz9563 ?= "recipes-kernel/linux/linux-tano-at91-evb-ksz9563.inc"
KERNEL_MACHINE_INCLUDE ?= "recipes-kernel/linux/linux-tano-at91-${MACHINE}.inc"
include ${KERNEL_MACHINE_INCLUDE}

KERNEL_MODULE_AUTOLOAD += "atmel_usba_udc g_serial"

LINUX_VERSION_EXTENSION = "-tano-at91-${LINUX_KERNEL_TYPE}"

KERNEL_DEVICETREE_COPY = "${@kernel_dtb2dts('KERNEL_DEVICETREE', d)}"
