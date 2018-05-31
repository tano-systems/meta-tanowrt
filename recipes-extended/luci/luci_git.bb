# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>
# Released under the MIT license (see COPYING.MIT for the terms)

PR = "tano0"

DESCRIPTION = "OpenWrt LuCI web user interface"
HOMEPAGE = "https://github.com/openwrt/luci"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=2b42edef8fa55315f34f2370b4715ca9"
SECTION = "base"
DEPENDS = "json-c libubox libnl lua5.1 iwinfo openssl"
RDEPENDS_${PN} = "lua5.1"

inherit cmake openwrt pkgconfig
inherit openwrt-luci-i18n

require luci.inc

prefix=""
includedir="/usr/include"
bindir="usr/bin"
libdir="/usr/lib"

OECMAKE_C_FLAGS += "-I${STAGING_INCDIR}/libnl3 -DDESTDIR=${D}"

FILES_${PN} += "/www /usr/lib /usr/share/acl.d /${bindir} /lib/upgrade/luci-add-conffiles.sh"

S = "${WORKDIR}/git/"

PACKAGECONFIG ??= "\
	luci-app-commands-predefined \
"

LUCI_APPLICATIONS ?= "\
	luci-app-commands \
	luci-app-firewall \
	luci-app-uhttpd \
	luci-app-openvpn"

LUCI_THEMES ?= "\
	luci-theme-bootstrap"

PACKAGECONFIG[luci-app-commands-predefined] = ""

SRC_URI += "${@bb.utils.contains('PACKAGECONFIG', 'luci-app-commands-predefined', 'file://luci-app-commands-predefined', '', d)}"
SRC_URI += "file://CMakeLists.txt"

apply_config() {
	OBJ_TYPE=$1
	OBJ_LIST=$2

	OBJS=`ls ${S}/${OBJ_TYPE}/`

	for obj in ${OBJS}; do
		DEL=1
		for iapp in ${OBJ_LIST}; do
			if [ "$iapp" = "$obj" ]; then
				DEL=0
			fi
		done
		
		if [ "$DEL" = "1" ]; then
			rm -rf ${S}/${OBJ_TYPE}/${obj}
			bbnote "Removed folder for ${obj} from sources"
		else
			luci_add_to_build "${OBJ_TYPE}" "${obj}"
			bbnote "Added to build ${obj}"
		fi
	done
}

apply_luci_version() {
	LUCI_VERSION_SRC="${S}/modules/luci-base/luasrc/version.lua"
	sed -i -e 's/\(distname\s*=\s*\)\".*\"/\1\"${LUCI_DISTNAME}\"/' ${LUCI_VERSION_SRC}
	sed -i -e 's/\(distversion\s*=\s*\)\".*\"/\1\"${LUCI_DISTVERSION}\"/' ${LUCI_VERSION_SRC}
	sed -i -e 's/\(luciname\s*=\s*\)\".*\"/\1\"${LUCI_NAME}\"/' ${LUCI_VERSION_SRC}
	sed -i -e 's/\(luciversion\s*=\s*\)\".*\"/\1\"${LUCI_VERSION}\"/' ${LUCI_VERSION_SRC}
}

do_preconfigure() {
	# Replace original root CMakeLists.txt
	rm -rf ${S}/CMakeLists.txt
	cp ${WORKDIR}/CMakeLists.txt ${S}

	# Clear extra CMakeLists.txt
	echo -n "" > ${S}/CMakeLists-extra.txt

	# Remove unused modules from sources
	rm -rf ${S}/modules/luci-mod-freifunk \
	       ${S}/modules/luci-mod-freifunc-community \
	       ${S}/modules/luci-mod-admin-mini \
	       ${S}/modules/luci-mod-rpc

	apply_config applications "${LUCI_APPLICATIONS}"
	apply_config themes "${LUCI_THEMES}"
	apply_luci_version
}

do_install_append() {
	if [ "${@bb.utils.contains('LUCI_APPLICATIONS', 'luci-app-commands', '1', '0', d)}" = "1" ]; then
		if [ "${@bb.utils.contains('PACKAGECONFIG', 'luci-app-commands-predefined', '1', '0', d)}" = "1" ]; then
			# Add pre-defined commands to LuCI config
			cat ${WORKDIR}/luci-app-commands-predefined >> ${D}${sysconfdir}/config/luci
		fi
	fi

	# Configure initial language
	sed -i -e "s/\(option\s*lang\).*/\1 \'${LUCI_INITIAL_LANG}\'/" ${D}${sysconfdir}/config/luci
}

luci_add_to_build() {
	FOLDER=$1
	NAME=$2

	echo "ADD_SUBDIRECTORY(${FOLDER}/${NAME})" >> ${S}/CMakeLists-extra.txt

	CMAKELISTS=${S}/${FOLDER}/${NAME}/CMakeLists.txt
	rm -rf ${CMAKELISTS}

	echo "cmake_minimum_required(VERSION 3.0)" > ${CMAKELISTS}
	echo "" >> ${CMAKELISTS}
	echo "PROJECT(${NAME} C)" >> ${CMAKELISTS}

	if [ -d "${S}/${FOLDER}/${NAME}/luasrc" ]; then
		echo "" >> ${CMAKELISTS}
		echo "INSTALL(DIRECTORY luasrc/" >> ${CMAKELISTS}
		echo "	DESTINATION \"\${LUAPATH}/luci\"" >> ${CMAKELISTS}
		echo "	USE_SOURCE_PERMISSIONS" >> ${CMAKELISTS}
		echo ")" >> ${CMAKELISTS}
	fi

	if [ -d "${S}/${FOLDER}/${NAME}/root" ]; then
		echo "" >> ${CMAKELISTS}
		echo "INSTALL(DIRECTORY root/" >> ${CMAKELISTS}
		echo "	DESTINATION \"\${CMAKE_INSTALL_PREFIX}/\"" >> ${CMAKELISTS}
		echo "	USE_SOURCE_PERMISSIONS" >> ${CMAKELISTS}
		echo ")" >> ${CMAKELISTS}
	fi

	if [ -d "${S}/${FOLDER}/${NAME}/htdocs" ]; then
		echo "" >> ${CMAKELISTS}
		echo "INSTALL(DIRECTORY htdocs/" >> ${CMAKELISTS}
		echo "	DESTINATION \"\${CMAKE_INSTALL_PREFIX}/www\"" >> ${CMAKELISTS}
		echo "	USE_SOURCE_PERMISSIONS" >> ${CMAKELISTS}
		echo ")" >> ${CMAKELISTS}
	fi
}

addtask preconfigure before do_configure after do_patch
