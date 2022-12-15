#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2020 Tano Systems LLC. All rights reserved.
# Authors: Anton Kikin <a.kikin@tano-systems.com>
#
inherit qoriq_build_64bit_kernel
inherit siteinfo

PR:append = ".0"

COMPATIBLE_MACHINE = "(qoriq)"

DEPENDS:append = " libgcc"
KERNEL_CC:append = " ${TOOLCHAIN_OPTIONS}"
KERNEL_LD:append = " ${TOOLCHAIN_OPTIONS}"
KERNEL_EXTRA_ARGS += "LOADADDR=${UBOOT_ENTRYPOINT}"

SCMVERSION ?= "y"
LOCALVERSION = ""

KERNEL_IMAGETYPE ?= "Image"

KERNEL_MACHINE_INCLUDE:ls1028ardb ?= "recipes-kernel/linux/linux-tano-openil-ls1028ardb-${LINUX_VERSION_SHORT}.inc"
include ${KERNEL_MACHINE_INCLUDE}

LINUX_VERSION_EXTENSION = "-tano-openil-${LINUX_KERNEL_TYPE}"
