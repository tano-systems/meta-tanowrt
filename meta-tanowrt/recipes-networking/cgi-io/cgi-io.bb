#
# SPDX-License-Identifier: MIT
# Copyright (c) 2019-2020 Tano Systems LLC. All rights reserved.
#

PR = "tano14"

DESCRIPTION = "This package contains an cgi utility that is useful for up/downloading files"
SUMMARY = "CGI utility for handling up/downloading of files"
SECTION = "net"
DEPENDS = "libubox libubus"
LICENSE = "GPL-2.0-only & MIT"
LIC_FILES_CHKSUM = "\
	file://main.c;beginline=1;endline=17;md5=5575bf2208fe08f4718ab46b4dc602d7 \
	file://multipart_parser.c;beginline=1;endline=4;md5=5300a1f2ef0420d64635f8e81858c30f \
"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/patches:"

SRC_URI = "\
	git://${GIT_OPENWRT_ORG}/project/cgi-io.git;branch=master \
	file://0001-Import-changes-from-meta-tanowrt-layer.patch \
"

# 12.10.2020
# tests: add cram based unit tests
SRCREV = "ab4c3471b26179b6e1decfb6ca27c4a87df9a0a4"
S = "${WORKDIR}/git"

inherit cmake

PACKAGECONFIG ??= "enable-chksum-options enable-direct-write-mode enable-append-mode"
PACKAGECONFIG[enable-chksum-options] = ""
PACKAGECONFIG[enable-direct-write-mode] = ""
PACKAGECONFIG[enable-append-mode] = ""

EXTRA_OECMAKE += "\
	-DENABLE_UPLOAD_CHKSUM_OPTIONS=${@bb.utils.contains('PACKAGECONFIG', 'enable-chksum-options', 'ON', 'OFF', d)} \
	-DENABLE_DIRECT_WRITE_MODE=${@bb.utils.contains('PACKAGECONFIG', 'enable-direct-write-mode', 'ON', 'OFF', d)} \
	-DENABLE_APPEND_MODE=${@bb.utils.contains('PACKAGECONFIG', 'enable-append-mode', 'ON', 'OFF', d)} \
"

FILES:${PN} = "\
	/usr/libexec/ \
	/www/cgi-bin/ \
"

do_install() {
	install -dm 0755 ${D}/usr/libexec
	install -dm 0755 ${D}/www/cgi-bin
	install -m 0755 ${B}/cgi-io ${D}/usr/libexec/cgi-io

	ln -s ../../usr/libexec/cgi-io ${D}/www/cgi-bin/cgi-upload
	ln -s ../../usr/libexec/cgi-io ${D}/www/cgi-bin/cgi-download
	ln -s ../../usr/libexec/cgi-io ${D}/www/cgi-bin/cgi-backup
	ln -s ../../usr/libexec/cgi-io ${D}/www/cgi-bin/cgi-exec
}
