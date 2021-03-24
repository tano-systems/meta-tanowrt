#
# SPDX-License-Identifier: MIT
#
# Distributed, real-time, performance and health monitoring for systems and applications.
#
# This file Copyright (c) 2019, Anton Kikin <a.kikin@tano-systems.com>
#
HOMEPAGE = "https://github.com/tgraf/bmon"
SUMMARY = "Bandwidth Monitor"
LICENSE = "BSD & MIT"
LIC_FILES_CHKSUM = "file://LICENSE.BSD;md5=5c262c13b60ebefe3060aed37d334ab6 \
                    file://LICENSE.MIT;md5=544799d0b492f119fa04641d1b8868ed"

DEPENDS += "ncurses libconfuse libnl"

SRC_URI = "git://github.com/tgraf/bmon.git"
SRCREV = "def45f59a830cd1c07f2cd0a56f7262e18f3a8a8"
PV = "4.0+git${SRCPV}"
PR = "tano0"

S = "${WORKDIR}/git"

inherit pkgconfig autotools
