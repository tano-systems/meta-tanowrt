#
# Copyright (C) 2020, Tano Systems, All rights reserved
# Authors: Anton Kikin <a.kikin@tano-systems.com>
#
inherit qoriq_build_64bit_kernel
inherit siteinfo
inherit fsl-kernel-localversion

PR_append = ".0"

COMPATIBLE_MACHINE = "(qoriq)"

DEPENDS_append = " libgcc"
KERNEL_CC_append = " ${TOOLCHAIN_OPTIONS}"
KERNEL_LD_append = " ${TOOLCHAIN_OPTIONS}"
KERNEL_EXTRA_ARGS += "LOADADDR=${UBOOT_ENTRYPOINT}"

SCMVERSION ?= "y"
LOCALVERSION = ""

KERNEL_IMAGETYPE ?= "Image"

MACHINE_INCLUDE = "empty.inc"
MACHINE_INCLUDE_nxp-ls1028ardb = "recipes-kernel/linux/linux-tano-openil-nxp-ls1028ardb-${LINUX_VERSION_SHORT}.inc"
require ${MACHINE_INCLUDE}

LINUX_VERSION_EXTENSION = "-tano-openil-${LINUX_KERNEL_TYPE}"
