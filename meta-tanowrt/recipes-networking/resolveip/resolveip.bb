#
# This file Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>
#
PR = "tano0"

DESCRIPTION = "Simple DNS resolver with configurable timeout"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"
SECTION = "utils"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI = "file://resolveip.c"

S = "${WORKDIR}"

CFLAGS += "-Wall"
FILES_${PN} += "/usr/bin/"

do_compile() {
	${CC} ${CFLAGS} ${LDFLAGS} -o ${B}/resolveip ${S}/resolveip.c
}

do_install() {
	install -dm 0755 ${D}/usr/bin
	install -m 0755 ${B}/resolveip ${D}/usr/bin/resolveip
}
