#
# SPDX-License-Identifier: MIT
#
# XXD utility
# A hexdump utility by Juergen Weigert, v1.11 by Vadim Vygonets
#
# This file Copyright (C) 2018 Tano Systems
# Anton Kikin <a.kikin@tano-systems.com>
#
PR = "tano0"

SUMMARY = "XXD utility"
SECTION = "console/utils"
LICENSE = "vim"
LIC_FILES_CHKSUM = "file://../runtime/doc/uganda.txt;md5=eea32ac1424bba14096736a494ae9045"

SRC_URI = "git://github.com/vim/vim.git"
SRCREV = "ec68a99464055029c01082762517e97245ddae0c"

S = "${WORKDIR}/git/src"

inherit native

do_configure[noexec] = "1"

do_compile() {
	${MAKE} -C ${S}/xxd clean xxd
}

do_install() {
	install -d ${D}${base_bindir}
	install -m 0755 ${S}/xxd/xxd ${D}${base_bindir}/xxd
}
