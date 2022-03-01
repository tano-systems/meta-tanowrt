#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Copyright (C) 2018-2022 Anton Kikin <a.kikin@tano-systems.com>
#

PR = "tano35"
DESCRIPTION = "OpenWrt UBUS RPC server"
HOMEPAGE = "http://git.openwrt.org/?p=project/rpcd.git;a=summary"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://main.c;beginline=1;endline=18;md5=da5faf55ed0618f0dde1c88e76a0fc74"
SECTION = "base"
DEPENDS = "json-c libuci libubox libubus libucode libiwinfo virtual/crypt"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:${THISDIR}/${PN}/files-tmp:"

SRC_URI = "\
	git://${GIT_OPENWRT_ORG}/project/rpcd.git;name=rpcd \
	file://rpcd.init \
	file://rpcd.config \
	file://50-migrate-rpcd-ubus-sock.sh \
"

# Patches
SRC_URI += "\
	file://0001-file-Add-support-for-polled-execution-mode.patch \
	file://0002-exec-Increase-maximum-buffer-size-to-1-MiB.patch \
	file://0003-exec-Handle-ustream_poll-return-value-in-rpc_exec_pr.patch \
"

# 07.02.2022
# ucode: adjust to latest ucode api
SRCREV_rpcd = "909f2a04763dbc745488384b24281eca180452d6"

S = "${WORKDIR}/git"

inherit cmake tanowrt-services

PACKAGES += "${PN}-mod-file ${PN}-mod-iwinfo ${PN}-mod-rpcsys ${PN}-mod-ucode"

EXTRA_OECMAKE += "\
  -DCMAKE_INSTALL_LIBDIR:PATH=/usr/lib \
  -DFILE_SUPPORT=ON \
  -DIWINFO_SUPPORT=ON \
  -DRPCSYS_SUPPORT=ON \
  -DUCODE_SUPPORT=ON \
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

DESCRIPTION_${PN}-mod-ucode = "Allows implementing plugins using ucode scripts"
FILES_${PN}-mod-ucode = "${libdir}/rpcd/ucode.so"
RDEPENDS_${PN}-mod-ucode = "libucode"
