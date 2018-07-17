# Copyright (C) 2016 Khem Raj <raj.khem@gmail.com>
# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>
# Released under the MIT license (see COPYING.MIT for the terms)

PR = "tano2"
DESCRIPTION = "Tiny HTTP server"
HOMEPAGE = "http://git.openwrt.org/?p=project/uhttpd.git;a=summary"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://main.c;beginline=1;endline=18;md5=ba30601dd30339f7ff3d0ad681d45679"
SECTION = "base"
DEPENDS = "libubox ubus json-c ustream-ssl"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI = "\
          git://git.openwrt.org/project/uhttpd.git \
          file://0100-fix-wrong-binaries-found-due-to-inconsistent-path.patch \
          file://uhttpd.config \
          file://uhttpd.init \
          file://ubus.default \
"

# 26.06.2018
# client: flush buffered SSL output when tearing down client ustream
SRCREV = "796d42bceed2015bb00309a3bf0f49279b070c19"
S = "${WORKDIR}/git"

inherit cmake pkgconfig openwrt-services openwrt openwrt-base-files

OPENWRT_SERVICE_PACKAGES = "${PN}"
OPENWRT_SERVICE_SCRIPTS_${PN} = "${PN}"
OPENWRT_SERVICE_STATE_${PN}-${PN} = "enabled"

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
                  base-files-scripts-openwrt \
                  "

CONFFILES_${PN}_append = "\
	${sysconfdir}/config/uhttpd \
	${sysconfdir}/uhttpd.crt \
	${sysconfdir}/uhttpd.key \
"
