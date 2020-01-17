# This file Copyright (C) 2020 Tano Systems LLC

PR_append_am335x-icev2 = ".ti0"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI_append_am335x-icev2 = "\
	file://rootfs/lib/preinit/01_display_lcd_message \
"

do_install_append_am335x-icev2() {
	install -d ${D}${base_libdir}/preinit
	install -m 0755 ${WORKDIR}/rootfs/lib/preinit/01_display_lcd_message ${D}${base_libdir}/preinit/
}
