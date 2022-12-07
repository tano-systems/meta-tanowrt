#
# SPDX-License-Identifier: MIT
#
# XXD utility
# A hexdump utility by Juergen Weigert, v1.11 by Vadim Vygonets
#
# This file Copyright (C) 2018, 2022 Tano Systems LLC
# Anton Kikin <a.kikin@tano-systems.com>
#
PR = "tano2"

SUMMARY = "XXD utility"
SECTION = "console/utils"
LICENSE = "Vim"
LIC_FILES_CHKSUM = "file://../runtime/doc/uganda.txt;md5=eea32ac1424bba14096736a494ae9045"

SRC_URI = "git://github.com/vim/vim.git;branch=master;protocol=https"
SRCREV = "ec68a99464055029c01082762517e97245ddae0c"

S = "${WORKDIR}/git/src"

do_configure() {
	oe_runmake -C ${S}/xxd clean
}

do_compile() {
	oe_runmake -C ${S}/xxd xxd
}

do_install() {
	install -d ${D}${base_bindir}
	install -m 0755 ${S}/xxd/xxd ${D}${base_bindir}/xxd
}

BBCLASSEXTEND = "native nativesdk"
