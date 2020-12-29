#
# SPDX-License-Identifier: MIT
#
# Recipe for alternate VSFTPd with UCI support
# This file Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>
#
# Original makefile and patches taken from
# https://github.com/coolsnowwolf/lede.git
# (master branch, revision a828ffad85e5d9b673d49ea9b8316ab3689d34c0)
#
SUMMARY = "Very Secure FTP server"
HOMEPAGE = "https://security.appspot.com/vsftpd.html"
SECTION = "net"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = " \
	file://COPYING;md5=a6067ad950b28336613aed9dd47b1271 \
	file://COPYRIGHT;md5=04251b2eb0f298dae376d92454f6f72e \
	file://LICENSE;md5=654df2042d44b8cac8a5654fc5be63eb \
"

PV = "3.0.3"
PR = "tano5"

DEPENDS = "libcap openssl libuci virtual/crypt"

SRC_URI = "\
	https://security.appspot.com/downloads/vsftpd-${PV}.tar.gz \
"

SRC_URI[md5sum] = "da119d084bd3f98664636ea05b5bb398"
SRC_URI[sha256sum] = "9d4d2bf6e6e2884852ba4e69e157a2cecd68c5a7635d66a3a8cf8d898c955ef7"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

# Files
SRC_URI += "\
	file://vsftpd.init \
	file://vsftpd.config \
"

# Patches
SRC_URI += "\
	file://001-destdir.patch \
	file://002-find_libs.patch \
	file://003-chroot.patch \
	file://004-disable-capabilities.patch \
	file://005-disable-pam.patch \
	file://006-musl-compatibility.patch \
	file://007-CVE-2015-1419.patch \
	file://100-add-uci-auth-support.patch \
	file://101-enable-chroot-on-writable-dir.patch \
	file://102-keep-local-user-rights.patch \
"

inherit tanowrt-services
inherit useradd

USERADD_PACKAGES = "${PN}"
USERADD_PARAM_${PN} = "--system --home-dir /home/ftp --no-create-home -g ftp --shell /bin/false ftp "
GROUPADD_PARAM_${PN} = "-r ftp"

TANOWRT_SERVICE_PACKAGES = "vsftpd"
TANOWRT_SERVICE_SCRIPTS_vsftpd += "vsftpd"
TANOWRT_SERVICE_STATE_vsftpd-vsftpd ?= "enabled"

LDFLAGS_append =" -lcrypt -lcap -luci"
EXTRA_OEMAKE = "-e MAKEFLAGS="

do_compile() {
	oe_runmake "LIBS=-L${STAGING_LIBDIR} -lcrypt -lcap -luci"
}

do_install() {
	oe_runmake 'DESTDIR=${D}' install
	rm -rf ${D}${sysconfdir}/xinetd.d

	install -dm 0755 ${D}${sysconfdir}/config
	install -m 0644 ${WORKDIR}/vsftpd.config ${D}${sysconfdir}/config/vsftpd

	install -dm 0755 ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/vsftpd.init ${D}${sysconfdir}/init.d/vsftpd
}

CONFFILES_${PN}_append = "\
	${sysconfdir}/config/vsftpd \
"
