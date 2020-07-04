#
# JSON Module for Lua
# http://dkolf.de/src/dkjson-lua.fsl/home
# dkjson is free software released under the same conditions
# as the Lua interpreter.
#
# This file Copyright (C) 2020 Anton Kikin <a.kikin@tano-systems.com>
#

PR = "tano0"
PV = "2.5"

SUMMARY = "This is a JSON module written in Lua. It supports UTF-8."
DESCRIPTION = "JSON Module for Lua"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
DEPENDS = "lua5.1"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

inherit tanowrt-lua
inherit luasrcdiet

RDEPENDS_${PN} += "lua5.1"

SRC_URI = "git://github.com/LuaDist/dkjson.git"
SRCREV = "e72ba0c9f5d8b8746fc306f6189a819dbb5cd0be"

S = "${WORKDIR}/git"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

LUADIR = "${libdir}/lua/5.1"

do_install() {
	install -d ${D}${LUADIR}
	install -m 0644 ${S}/dkjson.lua ${D}${LUADIR}/dkjson.lua
}

FILES_${PN} = "${libdir}/lua/5.1/dkjson.lua"
