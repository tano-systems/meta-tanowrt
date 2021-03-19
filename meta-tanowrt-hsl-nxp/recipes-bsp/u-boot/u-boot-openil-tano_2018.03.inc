#
# SPDX-License-Identifier: MIT
# Copyright (c) 2020-2021 Tano Systems LLC. All rights reserved.
#

UBOOT_LOCALVERSION_append = "-${PR}"

# Always write localversion to .scmversion
do_compile_prepend() {
	echo ${UBOOT_LOCALVERSION} > ${B}/.scmversion
	echo ${UBOOT_LOCALVERSION} > ${S}/.scmversion
}

inherit u-boot-env-2017
inherit u-boot-envimage
inherit u-boot-defconfig-copy

SRC_URI_append_ls1028ardb = "\
	file://0001-ls1028a-Setup-boot_device-environment-variable.patch \
	file://0002-ls1028a-Change-environment-offsets-and-enable-redund.patch \
	file://0003-ls1028a-Allow-to-defined-CONFIG_BOOTCOMMAND-in-defco.patch \
	file://0004-ls1028a-Reduce-default-environment.patch \
"
