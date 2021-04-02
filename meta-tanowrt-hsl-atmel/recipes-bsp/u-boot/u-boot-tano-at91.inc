#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2021 Tano Systems LLC. All rights reserved.
# Authors: Anton Kikin <a.kikin@tano-systems.com>
#

INCPR = "0"

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
"

SRC_URI_append_evb-ksz9477 = "\
	file://0004-at91-sama5d3_xplained-Fix-macbX-nodes-to-match-EVB-K.patch \
"

inherit u-boot-defconfig-copy
inherit u-boot-envimage
