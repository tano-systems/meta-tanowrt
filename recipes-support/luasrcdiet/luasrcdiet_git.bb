#
# LuaSrcDiet - Compresses Lua source code by removing unnecessary characters.
#
# This file Copyright (C) 2018 Tano Systems
# Anton Kikin <a.kikin@tano-systems.com>
#
SUMMARY = "LuaSrcDiet"
DESCRIPTION = "Compresses Lua source code by removing unnecessary characters."
SECTION = "utils"
LICENSE = "LuaSrcDiet"
LIC_FILES_CHKSUM = "file://COPYRIGHT;md5=901db83aee840148cdccd058178e141a"
DEPENDS = "lua5.1"
RDEPENDS_${PN} = "lua5.1"

PV = "1.0.0"
PR = "tano0"

SRC_URI = "git://github.com/jirutka/luasrcdiet.git"
SRCREV = "f138fc9359821d9201cd6b57cfa2fcbed5b9af97"

S = "${WORKDIR}/git"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

FILES_${PN} += "/usr/lib"
FILES_${PN} += "/bin"

do_install_append() {
	install -dm 0755 ${D}${base_bindir}
	install -dm 0755 ${D}${libdir}/lua/5.1/luasrcdiet
	install -dm 0755 ${D}${libdir}/lua/5.1/luasrcdiet/plugin

	install -m 0755 ${B}/bin/luasrcdiet ${D}${base_bindir}/luasrcdiet

	install -m 0644 ${B}/luasrcdiet/equiv.lua ${D}${libdir}/lua/5.1/luasrcdiet/equiv.lua
	install -m 0644 ${B}/luasrcdiet/fs.lua ${D}${libdir}/lua/5.1/luasrcdiet/fs.lua
	install -m 0644 ${B}/luasrcdiet/init.lua ${D}${libdir}/lua/5.1/luasrcdiet/init.lua
	install -m 0644 ${B}/luasrcdiet/llex.lua ${D}${libdir}/lua/5.1/luasrcdiet/llex.lua
	install -m 0644 ${B}/luasrcdiet/lparser.lua ${D}${libdir}/lua/5.1/luasrcdiet/lparser.lua
	install -m 0644 ${B}/luasrcdiet/optlex.lua ${D}${libdir}/lua/5.1/luasrcdiet/optlex.lua
	install -m 0644 ${B}/luasrcdiet/optparser.lua ${D}${libdir}/lua/5.1/luasrcdiet/optparser.lua
	install -m 0644 ${B}/luasrcdiet/utils.lua ${D}${libdir}/lua/5.1/luasrcdiet/utils.lua

	install -m 0644 ${B}/luasrcdiet/plugin/example.lua ${D}${libdir}/lua/5.1/luasrcdiet/plugin/example.lua
	install -m 0644 ${B}/luasrcdiet/plugin/html.lua ${D}${libdir}/lua/5.1/luasrcdiet/plugin/html.lua
	install -m 0644 ${B}/luasrcdiet/plugin/sloc.lua ${D}${libdir}/lua/5.1/luasrcdiet/plugin/sloc.lua
}
