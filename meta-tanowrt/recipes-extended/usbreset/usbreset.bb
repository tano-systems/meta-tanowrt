#
# SPDX-License-Identifier: MIT
#
# This file Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>
#
PR = "tano0"

DESCRIPTION = "Utility to send a USB port reset to a USB device"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=801f80980d171dd6425610833a22dbe6"
SECTION = "utils"
DEPENDS = "libusb"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI = "file://usbreset.c file://LICENSE"

S = "${WORKDIR}"

CFLAGS += "-Wall"
FILES:${PN} += "/usr/bin/"

do_compile() {
	${CC} ${CFLAGS} ${LDFLAGS} -o ${B}/usbreset ${S}/usbreset.c
}

do_install() {
	install -dm 0755 ${D}/usr/bin
	install -m 0755 ${B}/usbreset ${D}/usr/bin/usbreset
}
