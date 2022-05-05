#
# SPDX-License-Identifier: MIT
#
# LuCI core libraries
#
# This file Copyright (c) 2019-2020 Tano Systems LLC. All rights reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#

PR = "tano9"

SUMMARY = "LuCI core libraries"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

DEPENDS += "lemon-native"
DEPENDS += "lua5.1"

RDEPENDS_${PN} += "\
	lua5.1 \
	luci-lib-nixio \
	luci-lib-ip \
	luci-lib-jsonc \
	luci-lib-base \
	libubus-lua \
	rpcd \
	rpcd-mod-rrdns \
	rpcd-mod-luci \
	lucihttp \
	cgi-io \
	luasyslog \
"

inherit cmake

python __anonymous() {
    d.delVarFlag('do_configure', 'noexec')
    d.delVarFlag('do_compile', 'noexec')
}

inherit tanowrt-lua
inherit tanowrt-luci-mod
inherit tanowrt-luci-i18n
inherit tanowrt-services

TANOWRT_SERVICE_PACKAGES = "luci-base"
TANOWRT_SERVICE_SCRIPTS_luci-base += "ucitrack"
TANOWRT_SERVICE_STATE_luci-base-ucitrack ?= "enabled"

# Remove the dependency on ourselves
RDEPENDS_${PN}_remove = "luci-base"

SRC_URI = "${LUCI_GIT_URI};branch=${LUCI_GIT_BRANCH};protocol=${LUCI_GIT_PROTOCOL};subpath=modules/luci-base;destsuffix=git/"
SRCREV = "${LUCI_GIT_SRCREV}"
S = "${WORKDIR}/git"

SRC_URI += "\
	file://cmake.patch;pnum=3 \
	file://99_luci-theme-initial \
"

LUCI_VERSION ?= "${PV}-${PR} git-${@d.getVar('SRCREV', d)[:12]}"

apply_luci_version() {
	LUCI_VERSION_SRC="${S}/luasrc/version.lua"
	sed -i -e 's/\(distname\s*=\s*\)\".*\"/\1\"${LUCI_DISTNAME}\"/' ${LUCI_VERSION_SRC}
	sed -i -e 's/\(distversion\s*=\s*\)\".*\"/\1\"${LUCI_DISTVERSION}\"/' ${LUCI_VERSION_SRC}
	sed -i -e 's/\(luciname\s*=\s*\)\".*\"/\1\"${LUCI_NAME}\"/' ${LUCI_VERSION_SRC}
	sed -i -e 's/\(luciversion\s*=\s*\)\".*\"/\1\"${LUCI_VERSION}\"/' ${LUCI_VERSION_SRC}
}

do_preconfigure() {
	apply_luci_version
}

do_preconfigure[vardeps] += "LUCI_DISTNAME LUCI_DISTVERSION LUCI_NAME LUCI_VERSION"

do_install_append() {
	# Configure initial language
	sed -i -e "s/\(option\s*lang\).*/\1 \'${LUCI_INITIAL_LANG}\'/" ${D}${sysconfdir}/config/luci

	# Configure initial mediaurlbase
	install -d ${D}${sysconfdir}/uci-defaults
	install -m 0755 ${WORKDIR}/99_luci-theme-initial ${D}${sysconfdir}/uci-defaults/zz_luci-theme-initial

	MEDIAURLBASE_ESCAPED="${@d.getVar('LUCI_INITIAL_MEDIAURLBASE', True).replace('/', '\/')}"
	sed -i -e "s/\(luci\.main\.mediaurlbase\)=.*/\1=${MEDIAURLBASE_ESCAPED}/" ${D}${sysconfdir}/uci-defaults/zz_luci-theme-initial

	if [ "${LUCI_INSTALL_LUASRC_PATH}" != "${nonarch_libdir}/lua/luci" ]; then
		# Make symlink for compatibility with upstream paths
		ln -s ${LUCI_INSTALL_LUASRC_PATH} ${D}${nonarch_libdir}/lua/luci
	fi

	if [ -d "${D}${LUCI_INSTALL_HTDOCS_PATH}/cgi-bin" ]; then
		chmod 0755 ${D}${LUCI_INSTALL_HTDOCS_PATH}/cgi-bin/*
	fi

	if [ -d "${D}${libexecdir}/rpcd" ]; then
		chmod 0755 ${D}${libexecdir}/rpcd/*
	fi
}

do_install[vardeps] += "LUCI_INITIAL_LANG LUCI_INITIAL_MEDIAURLBASE"

addtask preconfigure before do_configure after do_patch

CONFFILES_${PN}_append = "\
	${sysconfdir}/config/luci \
	${sysconfdir}/config/ucitrack \
"

# This is needed for parser.so success build
do_configure_prepend() {
	oe_runmake -C ${S}/src/ clean
	oe_runmake -C ${S}/src/ plural_formula.c
}

inherit uci-config

do_uci_config_append() {
	${UCI} set luci.main.libdir_arch="${libdir}"
	${UCI} set luci.main.libdir_nonarch="${nonarch_libdir}"
	${UCI} commit luci
}
