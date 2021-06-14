#
# SPDX-License-Identifier: MIT
#
# CSSTidy parser and optimiser
#
# This file Copyright (C) 2020-2021 Tano Systems LLC
# Anton Kikin <a.kikin@tano-systems.com>
#
PR = "tano1"

DESCRIPTION = "CSSTidy parser and optimiser"
HOMEPAGE = "https://github.com/openwrt/luci"
LICENSE = "LGPL-2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=f0c98afda62608f457e8327081b5d915"
SECTION = "tools"

SRC_URI = "git://github.com/jow-/csstidy-cpp.git"
SRCREV = "707feaec556c40c999514a598b1a1ea5b50826c6"

S = "${WORKDIR}/git"

FILES_${PN} = "\
	${bindir}/csstidy"

do_install() {
	install -d ${D}${bindir}
	install -m 0755 ${S}/csstidy/csstidy ${D}${bindir}/csstidy
}

BBCLASSEXTEND = "native"
