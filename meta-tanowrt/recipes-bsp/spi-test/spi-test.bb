#
# SPDX-License-Identifier: MIT
# Copyright (c) 2020 Tano Systems LLC. All rights reserved.
#

DESCRIPTION = "SPI testing utility (using spidev driver)"
SECTION = "applications"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://spi-test.c;endline=12;md5=5f6a6ca88bef579b2b82d58218f9ee3a"

SRC_URI = "file://spi-test.c \
          "

S = "${WORKDIR}"

TARGET_CC_ARCH += "${LDFLAGS}"

do_compile() {
	${CC} spi-test.c -o spi-test
}

do_install() {
	install -d ${D}${bindir}
	install -m 0755 spi-test ${D}${bindir}
}
