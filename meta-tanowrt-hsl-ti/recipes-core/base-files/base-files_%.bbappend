#
# SPDX-License-Identifier: MIT
# Copyright (c) 2020 Tano Systems LLC. All rights reserved.
#
PR:append:am335x-icev2 = ".ti0"
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI:append:am335x-icev2 = "\
	file://rootfs/lib/preinit/01_display_lcd_message \
"

do_install:append:am335x-icev2() {
	install -d ${D}${TANOWRT_PATH_PREINIT}
	install -m 0755 ${WORKDIR}/rootfs/lib/preinit/01_display_lcd_message ${D}${TANOWRT_PATH_PREINIT}/
}
