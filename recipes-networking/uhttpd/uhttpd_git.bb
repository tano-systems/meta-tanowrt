# Copyright (C) 2016 Khem Raj <raj.khem@gmail.com>
# Copyright (C) 2018-2020 Anton Kikin <a.kikin@tano-systems.com>
# Released under the MIT license (see COPYING.MIT for the terms)

PR = "tano20"
DESCRIPTION = "Tiny HTTP server"
HOMEPAGE = "http://git.openwrt.org/?p=project/uhttpd.git;a=summary"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://main.c;beginline=1;endline=18;md5=ba30601dd30339f7ff3d0ad681d45679"
SECTION = "base"
DEPENDS = "libubox ubus json-c ustream-ssl virtual/crypt"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI = "\
	git://${GIT_OPENWRT_ORG}/project/uhttpd.git \
	file://0100-fix-wrong-binaries-found-due-to-inconsistent-path.patch \
	file://0200-add-gz-support.patch \
	file://0201-fix-uh_file_mime_lookup.patch \
	file://uhttpd.config \
	file://uhttpd.init \
	file://ubus.default \
	file://0001-Fix-building-for-GCC-8.2.patch \
"

PROVIDES += "uhttpd-mod-ubus uhttpd-mod-lua"
RPROVIDES_${PN} += "uhttpd-mod-ubus uhttpd-mod-lua"

# 22.12.2019
# client: fix invalid data access through invalid content-length values
SRCREV = "5f9ae5738372aaa3a6be2f0a278933563d3f191a"
S = "${WORKDIR}/git"

inherit cmake pkgconfig openwrt-services openwrt

OPENWRT_SERVICE_PACKAGES = "uhttpd"
OPENWRT_SERVICE_SCRIPTS_uhttpd += "uhttpd"
OPENWRT_SERVICE_STATE_uhttpd-uhttpd ?= "enabled"

SRCREV_openwrt = "${OPENWRT_SRCREV}"

CFLAGS += "-D_DEFAULT_SOURCE"

EXTRA_OECMAKE = "-DTLS_SUPPORT=ON -DLUA_SUPPORT=ON -DUBUS_SUPPORT=ON"

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
