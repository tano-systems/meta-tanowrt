#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2016 Khem Raj <raj.khem@gmail.com>
# Copyright (C) 2018-2022 Anton Kikin <a.kikin@tano-systems.com>
#

PR = "tano41"
DESCRIPTION = "Tiny HTTP server"
HOMEPAGE = "http://git.openwrt.org/?p=project/uhttpd.git;a=summary"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://main.c;beginline=1;endline=18;md5=ba30601dd30339f7ff3d0ad681d45679"
SECTION = "base"
DEPENDS = "libubox libucode ubus json-c ustream-ssl virtual/crypt"

FILESEXTRAPATHS_prepend := "${THISDIR}/${BPN}/patches:${THISDIR}/${BPN}/files:"

SRC_URI = "\
	git://${GIT_OPENWRT_ORG}/project/uhttpd.git;branch=master \
	file://uhttpd.config \
	file://uhttpd.init \
	file://ubus.default \
"

# Patches
SRC_URI += "\
	file://0001-fix-wrong-binaries-found-due-to-inconsistent-path.patch \
	file://0002-add-gz-support.patch \
	file://0003-ubus-allow-passing-sid-with-url.patch \
	file://1001-fix-uh_file_mime_lookup.patch \
	file://1002-Fix-building-for-GCC-8.2.patch \
	file://1003-ubus-Unsubscribe-on-client-request-network-timeout.patch \
	file://1004-main-Fix-formatting-in-usage-help-text.patch \
	file://1005-ubus-Disable-timeout-for-ubus-subscription-connectio.patch \
	file://1006-Fix-MIME-type-detection-for-files-with-dots-in-name.patch \
"

PROVIDES += "uhttpd-mod-ubus uhttpd-mod-lua"
RPROVIDES_${PN} += "uhttpd-mod-ubus uhttpd-mod-lua"

# 19.02.2022
# fix compiler uninitialized variable
SRCREV = "51283f9f1df5dedcba35f40367ef5d4ab1a55e0b"
S = "${WORKDIR}/git"

inherit cmake pkgconfig tanowrt-services tanowrt-lua

TANOWRT_SERVICE_PACKAGES = "uhttpd"
TANOWRT_SERVICE_SCRIPTS_uhttpd += "uhttpd"
TANOWRT_SERVICE_STATE_uhttpd-uhttpd ?= "enabled"

SRCREV_openwrt = "${OPENWRT_SRCREV}"

CFLAGS += "-D_DEFAULT_SOURCE"

EXTRA_OECMAKE = "-DTLS_SUPPORT=ON -DLUA_SUPPORT=ON -DUBUS_SUPPORT=ON -DUCODE_SUPPORT=ON"

do_unpack[vardeps] += "libdir base_libdir"

do_install_append() {
    install -d -m 0755 ${D}${sysconfdir}/config
    install -d -m 0755 ${D}${sysconfdir}/init.d
    install -d -m 0755 ${D}${sysconfdir}/uci-defaults
    install -Dm 0755 ${WORKDIR}/uhttpd.init ${D}${sysconfdir}/init.d/uhttpd
    install -Dm 0644 ${WORKDIR}/uhttpd.config ${D}${sysconfdir}/config/uhttpd
    install -Dm 0644 ${WORKDIR}/ubus.default ${D}${sysconfdir}/uci-defaults/00_uhttpd_ubus
    install -dm 0755 ${D}/usr/sbin
    ln -s /usr/bin/uhttpd ${D}/usr/sbin/uhttpd
    install -dm 0755 ${D}/www

    sed -i -e "s:LIBDIR=\"/usr/lib\":LIBDIR=\"${libdir}\":g" \
              ${D}${sysconfdir}/init.d/uhttpd
    sed -i -e "s:BASE_LIBDIR=\"/lib\":BASE_LIBDIR=\"${base_libdir}\":g" \
              ${D}${sysconfdir}/init.d/uhttpd
}

FILES_${PN}  += "${libdir}/* /www"

RDEPENDS_${PN} += "\
                  openssl \
                  openssl-bin \
                  base-files-scripts-openwrt \
                  "

CONFFILES_${PN}_append = "\
	${sysconfdir}/config/uhttpd \
	${sysconfdir}/uhttpd.crt \
	${sysconfdir}/uhttpd.key \
"
