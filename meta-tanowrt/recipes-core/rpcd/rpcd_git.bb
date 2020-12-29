#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Copyright (C) 2018-2020 Anton Kikin <a.kikin@tano-systems.com>
#

PR = "tano25"
DESCRIPTION = "OpenWrt UBUS RPC server"
HOMEPAGE = "http://git.openwrt.org/?p=project/rpcd.git;a=summary"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://main.c;beginline=1;endline=18;md5=da5faf55ed0618f0dde1c88e76a0fc74"
SECTION = "base"
DEPENDS = "json-c libuci libubox libubus libiwinfo virtual/crypt"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI = "\
	git://${GIT_OPENWRT_ORG}/project/rpcd.git;name=rpcd \
	file://rpcd.init \
	file://rpcd.config \
	file://50-migrate-rpcd-ubus-sock.sh \
"

# 18.09.2020
# rc: support init.d scripts with START=0
SRCREV_rpcd = "3fea6559817a22de1b8375b9b1f3d818e6534591"

S = "${WORKDIR}/git"

inherit cmake tanowrt-services

PACKAGES += "${PN}-mod-file ${PN}-mod-iwinfo ${PN}-mod-rpcsys"

EXTRA_OECMAKE += "\
  -DCMAKE_INSTALL_LIBDIR:PATH=/usr/lib/rpcd \
  -DFILE_SUPPORT=ON \
  -DIWINFO_SUPPORT=ON \
  -DRPCSYS_SUPPORT=ON \
"

TANOWRT_SERVICE_PACKAGES = "rpcd"
TANOWRT_SERVICE_SCRIPTS_rpcd += "rpcd"
TANOWRT_SERVICE_STATE_rpcd-rpcd ?= "enabled"

SRCREV_openwrt = "${OPENWRT_SRCREV}"

do_configure_prepend () {
	if [ -e "${S}/CMakeLists.txt" ] ; then
		sed -i -e "s:ARCHIVE DESTINATION lib:ARCHIVE DESTINATION \${CMAKE_INSTALL_LIBDIR}:g" \
		       -e "s:LIBRARY DESTINATION lib:LIBRARY DESTINATION \${CMAKE_INSTALL_LIBDIR}:g" \
		       ${S}/CMakeLists.txt
	fi
}

do_install_append() {
    install -d ${D}${includedir}/rpcd
    install -m 0644 ${S}/include/rpcd/* ${D}${includedir}/rpcd/
    install -Dm 0644 ${WORKDIR}/rpcd.config ${D}${sysconfdir}/config/rpcd
    install -Dm 0755 ${WORKDIR}/rpcd.init ${D}${sysconfdir}/init.d/rpcd

    install -Dm 0644 ${S}/unauthenticated.json ${D}${datadir}/rpcd/acl.d/unauthenticated.json

    install -d ${D}${sysconfdir}/uci-defaults
    install -m 0755 ${WORKDIR}/50-migrate-rpcd-ubus-sock.sh ${D}${sysconfdir}/uci-defaults/

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
