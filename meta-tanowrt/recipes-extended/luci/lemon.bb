#
# SPDX-License-Identifier: MIT
#
# LEMON LALR(1) parser generator
# (from LuCI sources at modules/luci-base/src/contrib)
#
# This file Copyright (C) 2020 Tano Systems
# Anton Kikin <a.kikin@tano-systems.com>
#
PR = "tano0"

DESCRIPTION = "LEMON LALR(1) parser generator"
HOMEPAGE = "https://github.com/openwrt/luci"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://lemon.c;beginline=1;endline=8;md5=c7551a78fa3fdecd96d1ad6761d205ee"
SECTION = "base"

require recipes-extended/luci/luci.inc

SRC_URI = "${LUCI_GIT_URI};branch=${LUCI_GIT_BRANCH};protocol=${LUCI_GIT_PROTOCOL};subpath=modules/luci-base/src/contrib;destsuffix=git/"
SRCREV  = "${LUCI_GIT_SRCREV}"

S = "${WORKDIR}/git"

FILES:${PN} = "\
	${base_bindir}/lemon"

do_configure() {
	rm -rf ${S}/lemon
}

do_compile() {
	${CC} ${CFLAGS} ${LDFLAGS} -o ${S}/lemon ${S}/lemon.c
}

do_install() {
	install -d ${D}${base_bindir}
	install -m 0755 ${S}/lemon ${D}${base_bindir}/lemon
}

BBCLASSEXTEND = "native"
