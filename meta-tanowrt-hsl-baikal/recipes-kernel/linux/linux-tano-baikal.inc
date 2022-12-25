#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2021 Tano Systems LLC. All rights reserved.
# Authors: Anton Kikin <a.kikin@tano-systems.com>
#

inherit kernel
inherit siteinfo

PR:append = ".0"

COMPATIBLE_MACHINE = "(baikal-m)"

#DEPENDS:append = " libgcc"
#KERNEL_CC:append = " ${TOOLCHAIN_OPTIONS}"
#KERNEL_LD:append = " ${TOOLCHAIN_OPTIONS}"
#KERNEL_EXTRA_ARGS += "LOADADDR=${UBOOT_ENTRYPOINT}"

SCMVERSION ?= "n"
LOCALVERSION = ""

KERNEL_MACHINE_INCLUDE:mitx ?= "recipes-kernel/linux/linux-tano-baikal-mitx-${LINUX_VERSION_SHORT}.inc"
KERNEL_MACHINE_INCLUDE ?= "recipes-kernel/linux/linux-tano-baikal-${MACHINE}.inc"
include ${KERNEL_MACHINE_INCLUDE}

LINUX_VERSION_EXTENSION = "-tano-baikal-${LINUX_KERNEL_TYPE}"
