#
# SPDX-License-Identifier: MIT
# Copyright (c) 2020 Tano Systems LLC. All rights reserved.
#

PR = "tano1"
PV = "1.3.0"

SUMMARY = "A simple API to use logging features in Lua"
DESCRIPTION = "lualogging core module"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYRIGHT;md5=8cea27c56ce851a51e816051857e2ca5"
DEPENDS = "lua5.1"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

inherit tanowrt-lua

RDEPENDS_${PN} += "lua5.1"

SRC_URI = "git://github.com/Neopallium/lualogging.git;branch=master;protocol=https"
SRCREV = "c85130120406b9641e96ab05f782dde819fbd102"

SRC_URI += "file://0001-Add-support-for-notice-level-messages.patch"

S = "${WORKDIR}/git"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

LUADIR = "${libdir}/lua/5.1"

do_install() {
	install -dm 0755 ${D}${LUADIR}/logging
	install -m 0644 ${S}/src/logging.lua ${D}${LUADIR}/
	for src in console.lua file.lua socket.lua; do
		install -m 0644 ${S}/src/logging/${src} ${D}${LUADIR}/logging/
	done
}

PACKAGES += "\
	${PN}-console \
	${PN}-file \
	${PN}-socket \
"

FILES_${PN} = "${libdir}/lua/5.1/logging.lua"

SUMMARY_${PN}-console = "Plugin for lualogging"
DESCRIPTION_${PN}-console = "Prints logging information to console"
RDEPENDS_${PN}-console += "${PN}"
FILES_${PN}-console = "${libdir}/lua/5.1/logging/console.lua"

SUMMARY_${PN}-file = "Plugin for lualogging"
DESCRIPTION_${PN}-file = "Saves logging information in a file"
RDEPENDS_${PN}-file += "${PN}"
FILES_${PN}-file = "${libdir}/lua/5.1/logging/file.lua"

SUMMARY_${PN}-socket = "Plugin for lualogging"
DESCRIPTION_${PN}-socket = "Sends the logging information through a socket using luasocket"
RDEPENDS_${PN}-socket += "${PN} lua-socket"
FILES_${PN}-socket = "${libdir}/lua/5.1/logging/socket.lua"

