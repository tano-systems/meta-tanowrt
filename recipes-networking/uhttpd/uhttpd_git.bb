# Copyright (C) 2016 Khem Raj <raj.khem@gmail.com>
# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>
# Released under the MIT license (see COPYING.MIT for the terms)

PR = "tano0"
DESCRIPTION = "Tiny HTTP server"
HOMEPAGE = "http://git.openwrt.org/?p=project/uhttpd.git;a=summary"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://main.c;beginline=1;endline=18;md5=ba30601dd30339f7ff3d0ad681d45679"
SECTION = "base"
DEPENDS = "libubox ubus json-c ustream-ssl"

SRC_URI = "\
          git://git.openwrt.org/project/uhttpd.git \
          file://0100-fix-wrong-binaries-found-due-to-inconsistent-path.patch \
          file://uhttpd.config \
"

SRCREV = "a235636a2687fafb9c474e4b134a59ff66425c92"
S = "${WORKDIR}/git"

inherit cmake pkgconfig openwrt-services openwrt openwrt-base-files

OPENWRT_SERVICE_PACKAGES = "${PN}"
OPENWRT_SERVICE_SCRIPTS_${PN} = "${PN}"
OPENWRT_SERVICE_STATE_${PN}-${PN} = "enabled"

SRCREV_openwrt = "${OPENWRT_SRCREV}"

CFLAGS += "-D_DEFAULT_SOURCE"

EXTRA_OECMAKE = "-DTLS_SUPPORT=ON -DLUA_SUPPORT=ON -DUBUS_SUPPORT=ON"

do_install_append() {
    install -Dm 0755 ${S}/openwrt/package/network/services/uhttpd/files/uhttpd.init ${D}${sysconfdir}/init.d/uhttpd
    install -Dm 0644 ${S}/openwrt/package/network/services/uhttpd/files/uhttpd.config ${D}${sysconfdir}/config/uhttpd
    install -Dm 0644 ${S}/openwrt/package/network/services/uhttpd/files/ubus.default ${D}${sysconfdir}/uci-defaults/00_uhttpd_ubus
    install -dm 0755 ${D}/usr/sbin
    ln -s /usr/bin/uhttpd ${D}/usr/sbin/uhttpd
    install -dm 0755 ${D}/www

    install -d -m 0755 ${D}${sysconfdir}/config
    install -m 0644 ${WORKDIR}/uhttpd.config ${D}${sysconfdir}/config/uhttpd
}

FILES_${PN}  += "${libdir}/* /www"

RDEPENDS_${PN} += "\
                  openssl \
                  base-files-scripts-openwrt \
                  "
