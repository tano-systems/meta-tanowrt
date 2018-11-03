# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>
# Released under the MIT license (see COPYING.MIT for the terms)

PR = "tano5"
DESCRIPTION = "OpenWrt UBUS RPC server"
HOMEPAGE = "http://git.openwrt.org/?p=project/rpcd.git;a=summary"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://main.c;beginline=1;endline=18;md5=da5faf55ed0618f0dde1c88e76a0fc74"
SECTION = "base"
DEPENDS = "json-c libuci"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI = "\
	git://git.openwrt.org/project/rpcd.git;name=rpcd \
	file://rpcd.init \
	file://rpcd.config \
"

# 09.08.2018
# uci: tighten uci reorder operation error handling
SRCREV_rpcd = "41333abee4c57e3de2bcfa08972954e2af20705a"

S = "${WORKDIR}/git"

inherit cmake pkgconfig openwrt-services openwrt

PACKAGECONFIG ??= "file"

# rpcd-mod-file
PACKAGECONFIG[file] = "-DFILE_SUPPORT=ON,-DFILE_SUPPORT=OFF,libubox libubus"

# rpcd-mod-iwinfo
PACKAGECONFIG[iwinfo] = "-DIWINFO_SUPPORT=ON,-DIWINFO_SUPPORT=OFF,libubox libubus iwinfo"

# rpcd-mod-rpcsys
PACKAGECONFIG[rpcsys] = "-DRPCSYS_SUPPORT=ON,-DRPCSYS_SUPPORT=OFF,libubox libubus"

EXTRA_OECMAKE += "\
  -DCMAKE_INSTALL_LIBDIR:PATH=/usr/lib/rpcd \
"

OPENWRT_SERVICE_PACKAGES = "rpcd"
OPENWRT_SERVICE_SCRIPTS_rpcd += "rpcd"
OPENWRT_SERVICE_STATE_rpcd-rpcd ?= "enabled"

SRCREV_openwrt = "${OPENWRT_SRCREV}"

do_install_append() {
    install -d ${D}${includedir}/rpcd
    install -m 0644 ${S}/include/rpcd/* ${D}${includedir}/rpcd/
    install -Dm 0644 ${WORKDIR}/rpcd.config ${D}${sysconfdir}/config/rpcd
    install -Dm 0755 ${WORKDIR}/rpcd.init ${D}${sysconfdir}/init.d/rpcd

    install -Dm 0644 ${S}/unauthenticated.json ${D}${datadir}/rpcd/acl.d/unauthenticated.json

    mkdir -p ${D}/sbin
    ln -s /usr/sbin/rpcd ${D}/sbin/rpcd
}

FILES_${PN} += "\
	${libdir}/* \
	${datadir}/* \
"

RDEPENDS_${PN} += "iwinfo"

CONFFILES_${PN}_append = "\
	${sysconfdir}/config/rpcd \
"
