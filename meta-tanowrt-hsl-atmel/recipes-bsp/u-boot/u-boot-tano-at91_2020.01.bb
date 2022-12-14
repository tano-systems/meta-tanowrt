#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2021-2022 Tano Systems LLC. All rights reserved.
# Authors: Anton Kikin <a.kikin@tano-systems.com>
#
# Based on original u-boot-at91_2020.01.bb recipe from meta-atmel layer
#

LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://README;beginline=1;endline=22;md5=9915e8cb100eb5dbb366010a0f10296c"

SRCREV = "af59b26c2241cb191ed0f57e4369ea718aa1dfac"
SRCREV_sama7g5 = "8d2e69b3c0d924a68d2ae2c3a4cfa9a2c2a3171b"

PV = "v2020.01-at91+git${SRCPV}"
PR = "tano0.${INCPR}"

DEPENDS += "bison-native flex-native"

COMPATIBLE_MACHINE = "evb-ksz9477|evb-ksz9563|at91-sama5d2-xplained|at91-sama5d3-xplained"

UBRANCH = "u-boot-2020.01-at91"
UBRANCH_sama7g5 = "sama7g5_early"

SRC_URI = "git://github.com/linux4sam/u-boot-at91.git;protocol=https;branch=${UBRANCH}"

S = "${WORKDIR}/git"

PACKAGE_ARCH = "${MACHINE_ARCH}"

require u-boot-tano-at91.inc
