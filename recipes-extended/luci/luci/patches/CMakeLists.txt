cmake_minimum_required(VERSION 3.0)

PROJECT(luci)

INCLUDE(GNUInstallDirs)

IF(NOT LUAPATH)
	SET(LUAPATH "${CMAKE_INSTALL_LIBDIR}/lua/5.1")
ENDIF()

IF(NOT LUA_CFLAGS)
	FIND_PROGRAM(PKG_CONFIG pkg-config)
	IF(PKG_CONFIG)
		EXECUTE_PROCESS(
			COMMAND pkg-config --silence-errors --cflags lua5.1
			OUTPUT_VARIABLE LUA_CFLAGS
			OUTPUT_STRIP_TRAILING_WHITESPACE
		)
	ENDIF()
ENDIF()

ADD_DEFINITIONS(${LUA_CFLAGS})

ADD_SUBDIRECTORY(modules/luci-base)
ADD_SUBDIRECTORY(modules/luci-mod-admin-full)
ADD_SUBDIRECTORY(modules/luci-mod-network)
ADD_SUBDIRECTORY(modules/luci-mod-status)
ADD_SUBDIRECTORY(modules/luci-mod-system)
ADD_SUBDIRECTORY(libs/luci-lib-nixio)
ADD_SUBDIRECTORY(libs/luci-lib-ip)
ADD_SUBDIRECTORY(libs/luci-lib-jsonc)
ADD_SUBDIRECTORY(libs/luci-lib-iptparser)

INCLUDE(${CMAKE_CURRENT_SOURCE_DIR}/CMakeLists-extra.txt)
