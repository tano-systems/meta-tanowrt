# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>
# Released under the MIT license (see COPYING.MIT for the terms)

PR = "tano21"

DESCRIPTION = "OpenWrt LuCI web user interface"
HOMEPAGE = "https://github.com/openwrt/luci"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=2b42edef8fa55315f34f2370b4715ca9"
SECTION = "base"
DEPENDS = "json-c libubox libnl lua5.1 iwinfo openssl"
RDEPENDS_${PN} = "lua5.1 lucihttp"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

inherit cmake openwrt pkgconfig
inherit openwrt-luci-i18n
inherit openwrt-services
inherit luasrcdiet

OPENWRT_SERVICE_PACKAGES = "luci"
OPENWRT_SERVICE_SCRIPTS_luci += "ucitrack"
OPENWRT_SERVICE_STATE_luci-ucitrack ?= "enabled"

require luci.inc

SRC_URI = "\
	${LUCI_GIT_URI};branch=${LUCI_GIT_BRANCH};protocol=${LUCI_GIT_PROTOCOL} \
	file://cmake.patch \
	file://0001-Fix-user.setpasswd-function.patch \
	file://0002-Use-etc-localtime-with-zoneinfo-instead-of-etc-TZ.patch \
	file://0004-fix-network-bridge-functions.patch \
	file://0005-fix-ru-i18n.patch \
	file://0006-fix-markup.patch \
	file://0009-fix-syslog-and-dmesg-textarea-height.patch \
	file://0010-fix-mounted-filesystems-info.patch \
	file://0014-luci-mod-status-split-status-page-into-a-series-of-p.patch \
	file://0015-luci-mod-system-output-only-existing-timezones.patch \
	file://0016-luci-mod-system-change-button-text-from-Dismiss-to-Close.patch \
	file://99_luci-theme-initial \
"

SRCREV = "${LUCI_GIT_SRCREV}" 

EXTRA_OECMAKE += "\
  -DCMAKE_INSTALL_PREFIX:PATH= \
  -DCMAKE_INSTALL_BINDIR:PATH=/usr/bin \
  -DCMAKE_INSTALL_SBINDIR:PATH=sbin \
  -DCMAKE_INSTALL_LIBEXECDIR:PATH=libexec \
  -DCMAKE_INSTALL_SYSCONFDIR:PATH=/etc \
  -DCMAKE_INSTALL_LOCALSTATEDIR:PATH=/var \
  -DCMAKE_INSTALL_LIBDIR:PATH=/usr/lib \
  -DCMAKE_INSTALL_INCLUDEDIR:PATH=/usr/include \
  -DCMAKE_INSTALL_DATAROOTDIR:PATH=/usr/share \
"

OECMAKE_C_FLAGS += "-I${STAGING_INCDIR}/libnl3 -DDESTDIR=${D}"

FILES_${PN} += "/www /usr/lib /usr/share/acl.d /usr/share/rpcd/acl.d ${bindir} /lib/upgrade/luci-add-conffiles.sh"

S = "${WORKDIR}/git/"

SRC_URI += "file://CMakeLists.txt"

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

	# Remove applications
	rm -rf ${S}/applications

	# Remove themes
	rm -rf ${S}/themes

	apply_luci_version
}

do_preconfigure[vardeps] += "LUCI_DISTNAME LUCI_DISTVERSION LUCI_NAME LUCI_VERSION"

do_install_append() {

	# Configure initial language
	sed -i -e "s/\(option\s*lang\).*/\1 \'${LUCI_INITIAL_LANG}\'/" ${D}${sysconfdir}/config/luci

	# Configure initial mediaurlbase
	install -d ${D}${sysconfdir}/uci-defaults
	install -m 0755 ${WORKDIR}/99_luci-theme-initial ${D}${sysconfdir}/uci-defaults/99_luci-theme-initial

	MEDIAURLBASE_ESCAPED="${@d.getVar('LUCI_INITIAL_MEDIAURLBASE', True).replace('/', '\/')}"
	sed -i -e "s/\(luci\.main\.mediaurlbase\)=.*/\1=${MEDIAURLBASE_ESCAPED}/" ${D}${sysconfdir}/uci-defaults/99_luci-theme-initial
}

do_install[vardeps] += "LUCI_INITIAL_LANG LUCI_INITIAL_MEDIAURLBASE"

addtask preconfigure before do_configure after do_patch

CONFFILES_${PN}_append = "\
	${sysconfdir}/config/luci \
	${sysconfdir}/config/ucitrack \
"
