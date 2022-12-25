#
# SPDX-License-Identifier: MIT
#
# This file Copyright (C) 2020 Tano Systems LLC
# Anton Kikin <a.kikin@tano-systems.com>
#
PV = "2019.12.17"
PR = "tano0"

DESCRIPTION = "CYW43430/43455 NVRAM"
HOMEPAGE = "https://github.com/openwrt/cypress-nvram.git"
SECTION = "kernel/firmware"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=60d644347832d2dd9534761f6919e2a6"

inherit allarch

SRC_URI = "git://github.com/openwrt/cypress-nvram.git;branch=master;protocol=https"
SRCREV = "e7b78df22f2a0c5f56abb7b5880661611de35e5f"
S = "${WORKDIR}/git"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

PACKAGES = "\
	${PN}-43430-sdio-rpi-zero-w \
	${PN}-43430-sdio-rpi-3b \
	${PN}-43455-sdio-rpi-3b-plus \
	${PN}-43455-sdio-rpi-4b \
"

PROVIDES = "${PACKAGES}"

do_install() {
	install -d ${D}/lib/firmware/brcm
	install -m 0644 ${S}/brcmfmac43430-sdio.raspberrypi,model-zero-w.txt ${D}/lib/firmware/brcm/
	install -m 0644 ${S}/brcmfmac43430-sdio.raspberrypi,3-model-b.txt ${D}/lib/firmware/brcm/
	install -m 0644 ${S}/brcmfmac43455-sdio.raspberrypi,3-model-b-plus.txt ${D}/lib/firmware/brcm/
	install -m 0644 ${S}/brcmfmac43455-sdio.raspberrypi,4-model-b.txt ${D}/lib/firmware/brcm/
}

FILES:${PN}-43430-sdio-rpi-zero-w = "/lib/firmware/brcm/brcmfmac43430-sdio.raspberrypi,model-zero-w.txt"
SUMMARY:${PN}-43430-sdio-rpi-zero-w ="CYW43430 NVRAM for Raspberry Pi Zero W"

FILES:${PN}-43430-sdio-rpi-3b = "/lib/firmware/brcm/brcmfmac43430-sdio.raspberrypi,3-model-b.txt"
SUMMARY:${PN}-43430-sdio-rpi-3b = "CYW43430 NVRAM for Raspberry Pi 3B"

FILES:${PN}-43455-sdio-rpi-3b-plus = "/lib/firmware/brcm/brcmfmac43455-sdio.raspberrypi,3-model-b-plus.txt"
SUMMARY:${PN}-43455-sdio-rpi-3b-plus = "CYW43455 NVRAM for Raspberry Pi 3B+"

FILES:${PN}-43455-sdio-rpi-4b = "/lib/firmware/brcm/brcmfmac43455-sdio.raspberrypi,4-model-b.txt"
SUMMARY:${PN}-43455-sdio-rpi-4b = "CYW43455 NVRAM for Raspberry Pi 4B"
