# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Copyright (C) 2020 Anton Kikin <a.kikin@tano-systems.com>

# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Lua is a powerful light-weight programming language designed \
for extending applications."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYRIGHT;md5=59bdd99bb82238f238cf5c65c21604fd"
HOMEPAGE = "http://www.lua.org/"

PR = "tano3"

V = "5.1"

DEPENDS += "readline"
SRC_URI = "\
	http://www.lua.org/ftp/lua-${PV}.tar.gz \
	file://${BPN}.pc \
"

# native
SRC_URI_append_class-native = "\
	file://patches-host/001-include-version-number.patch \
	file://patches-host/010-lua-5.1.3-lnum-full-260308.patch \
	file://patches-host/011-lnum-use-double.patch \
	file://patches-host/012-lnum-fix-ltle-relational-operators.patch \
	file://patches-host/013-lnum-strtoul-parsing-fixes.patch \
	file://patches-host/015-lnum-ppc-compat.patch \
	file://patches-host/030-archindependent-bytecode.patch \
"

# target
SRC_URI_append_class-target = "\
	file://patches/001-include-version-number.patch \
	file://patches/010-lua-5.1.3-lnum-full-260308.patch \
	file://patches/011-lnum-use-double.patch \
	file://patches/012-lnum-fix-ltle-relational-operators.patch \
	file://patches/013-lnum-strtoul-parsing-fixes.patch \
	file://patches/015-lnum-ppc-compat.patch \
	file://patches/030-archindependent-bytecode.patch \
	file://patches/300-opcode_performance.patch \
"

SRC_URI[md5sum] = "2e115fe26e435e33b0d5c022e4490567"
SRC_URI[sha256sum] = "2640fc56a795f29d28ef15e13c34a47e223960b0240e8cb0a82d9b0738695333"

inherit pkgconfig binconfig

S = "${WORKDIR}/lua-${PV}"

TARGET_CC_ARCH += " -fPIC ${LDFLAGS}"
EXTRA_OEMAKE = "'CC=${CC} -fPIC' 'MYCFLAGS=${CFLAGS} -DLUA_USE_LINUX -fPIC' MYLDFLAGS='${LDFLAGS}'"

do_configure_prepend() {
    sed -i -e s:/usr/local:${prefix}:g src/luaconf.h
}

do_compile () {
    oe_runmake \
        'PKG_VERSION=${PV}' \
        'INSTALL_ROOT=${prefix}' \
        linux
}

do_install () {
    oe_runmake \
        'PKG_VERSION=${PV}' \
        'INSTALL_TOP=${D}${prefix}' \
        'INSTALL_BIN=${D}${bindir}' \
        'INSTALL_INC=${D}${includedir}/${BPN}' \
        'INSTALL_MAN=${D}${mandir}/man1' \
        'INSTALL_SHARE=${D}${datadir}/lua' \
        'INSTALL_LIB=${D}${libdir}' \
        'INSTALL_CMOD=${D}${libdir}/lua/${V}' \
        'TO_INC=lua.h luaconf.h lualib.h lauxlib.h ../etc/lua.hpp lnum_config.h' \
        install
    install -D -m 0644 ${WORKDIR}/${BPN}.pc ${D}${libdir}/pkgconfig/${BPN}.pc
    mv ${D}${libdir}/liblua.a ${D}${libdir}/lib${BPN}.a
    rmdir ${D}${datadir}/lua/${V}
    rmdir ${D}${datadir}/lua
}

do_install_append_class-native() {
    ln -s lua${V} ${D}${bindir}/lua
    ln -s luac${V} ${D}${bindir}/luac
}

FILES_${PN} += "${libdir}/lua ${libdir}/lua/${V}"

BBCLASSEXTEND = "native"

inherit update-alternatives

ALTERNATIVE_PRIORITY = "100"

ALTERNATIVE_${PN} = "lua luac"

ALTERNATIVE_TARGET[lua] = "${bindir}/lua${V}"
ALTERNATIVE_TARGET[luac] = "${bindir}/luac${V}"
