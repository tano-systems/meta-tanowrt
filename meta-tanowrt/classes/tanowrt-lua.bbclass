#
# SPDX-License-Identifier: MIT
#
# This file Copyright (C) 2015 Khem Raj <raj.kem@gmail.com> and
#           Copyright (C) 2022 Anton Kikin <a.kikin@tano-systems.com>
#

LUAPATH = "${libdir}/lua/5.1"
LUAPATH_NONARCH = "${nonarch_libdir}/lua/5.1"

OECMAKE_C_FLAGS += "-DLUA_COMPAT_5_1"
EXTRA_OECMAKE += "-DLUAPATH=${LUAPATH} -DLUAPATH_NONARCH=${LUAPATH_NONARCH}"

FILES_${PN}  += "${libdir}/* ${nonarch_libdir}/* ${datadir}/lua/5.*/"
FILES_${PN}-dbg  += "${LUAPATH}/.debug"

DEPENDS += "lua5.1-native"
OECMAKE_C_FLAGS += "-I${STAGING_INCDIR}/lua5.1"
CFLAGS += "-I${STAGING_INCDIR}/lua5.1"

do_configure_prepend () {
	if [ -e "${S}/CMakeLists.txt" ] ; then
		sed -i -e "s:ARCHIVE DESTINATION lib:ARCHIVE DESTINATION \${CMAKE_INSTALL_LIBDIR}:g" \
		       -e "s:LIBRARY DESTINATION lib:LIBRARY DESTINATION \${CMAKE_INSTALL_LIBDIR}:g" \
		       ${S}/CMakeLists.txt
	fi
}
