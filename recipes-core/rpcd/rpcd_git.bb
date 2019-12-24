# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Copyright (C) 2018-2019 Anton Kikin <a.kikin@tano-systems.com>
# Released under the MIT license (see COPYING.MIT for the terms)

PR = "tano17"
DESCRIPTION = "OpenWrt UBUS RPC server"
HOMEPAGE = "http://git.openwrt.org/?p=project/rpcd.git;a=summary"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://main.c;beginline=1;endline=18;md5=da5faf55ed0618f0dde1c88e76a0fc74"
SECTION = "base"
DEPENDS = "json-c libuci libubox libubus libiwinfo"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI = "\
	git://${GIT_OPENWRT_ORG}/project/rpcd.git;name=rpcd \
	file://rpcd.init \
	file://rpcd.config \
"

# 10.12.2019
# file: extend exec acl checks to commands with arguments
SRCREV_rpcd = "aaa08366e6384d9933a405d1218b03c1b167f9e5"

S = "${WORKDIR}/git"

inherit cmake openwrt-services openwrt

PACKAGES += "${PN}-mod-file ${PN}-mod-iwinfo ${PN}-mod-rpcsys"

EXTRA_OECMAKE += "\
  -DCMAKE_INSTALL_LIBDIR:PATH=/usr/lib/rpcd \
  -DFILE_SUPPORT=ON \
  -DIWINFO_SUPPORT=ON \
  -DRPCSYS_SUPPORT=ON \
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

FILES_${PN} = "\
	${sysconfdir} \
	${base_sbindir} \
	${includedir} \
	${sbindir} \
	${datadir} \
"

CONFFILES_${PN}_append = "\
	${sysconfdir}/config/rpcd \
"

RDEPENDS_${PN} += "libubox libubus"

DESCRIPTION_${PN}-mod-file = "Provides ubus calls for file and directory operations"
FILES_${PN}-mod-file = "${libdir}/rpcd/file.so"

DESCRIPTION_${PN}-mod-rpcsys = "Provides ubus calls for sysupgrade and password changing"
FILES_${PN}-mod-rpcsys = "${libdir}/rpcd/rpcsys.so"

DESCRIPTION_${PN}-mod-iwinfo = "Provides ubus calls for accessing iwinfo data"
FILES_${PN}-mod-iwinfo = "${libdir}/rpcd/iwinfo.so"
RDEPENDS_${PN}-mod-iwinfo += "iwinfo"
