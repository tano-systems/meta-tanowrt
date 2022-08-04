#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2021-2022 Tano Systems LLC. All rights reserved.
# Authors: Anton Kikin <a.kikin@tano-systems.com>
#

INCPR = "2"

S = "${WORKDIR}/git"
B = "${WORKDIR}/build"
do_configure[cleandirs] = "${B}"

require ${COREBASE}/meta/recipes-bsp/u-boot/u-boot.inc

UBOOT_LOCALVERSION = "-git${SRCPV}-${PR}"

# Always write localversion to .scmversion
do_compile_prepend() {
	echo ${UBOOT_LOCALVERSION} > ${B}/.scmversion
	echo ${UBOOT_LOCALVERSION} > ${S}/.scmversion
}

SRC_URI += "\
	file://0001-Makefile-Clear-EXTRAVERSION.patch \
	file://0002-at91-Allow-to-specify-CONFIG_BOOTCOMMAND-in-defconfi.patch \
	file://0003-at91-sama5d3_xplained-Enable-watchdog.patch \
	file://0005-macb-Set-hwaddr-from-ethaddr-environment-variable.patch \
	file://0007-at91-Add-CONFIG_SYS_MMC_ENV_DEV-to-Kconfig.patch \
"

SRC_URI_append_evb-ksz9477 = "\
	file://0004-at91-sama5d3_xplained-Fix-macbX-nodes-to-match-EVB-K.patch \
"

SRC_URI_append_at91-sama5d2-xplained = "\
	file://0006-sama5d2_xplained-Allow-to-specify-CONFIG_BOOTCOMMAND.patch \
"

inherit u-boot-defconfig-copy
inherit u-boot-envimage
