# This file Copyright (C) 2019 Anton Kikin <a.kikin@tano-systems.com>

PR = "tano4"

DESCRIPTION = "This package contains an cgi utility that is useful for up/downloading files"
SUMMARY = "CGI utility for handling up/downloading of files"
SECTION = "net"
DEPENDS = "libubox libubus"
LICENSE = "GPLv2 & MIT"
LIC_FILES_CHKSUM = "\
	file://main.c;beginline=1;endline=17;md5=5575bf2208fe08f4718ab46b4dc602d7 \
	file://multipart_parser.c;beginline=1;endline=4;md5=5300a1f2ef0420d64635f8e81858c30f \
"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/src:"

SRC_URI = "\
	file://main.c \
	file://multipart_parser.c \
	file://multipart_parser.h \
	file://CMakeLists.txt \
"

S = "${WORKDIR}"

inherit cmake pkgconfig

PACKAGECONFIG ??= "enable-chksum-options enable-direct-write-mode"
PACKAGECONFIG[enable-chksum-options] = ""
PACKAGECONFIG[enable-direct-write-mode] = ""

EXTRA_OECMAKE += "\
	-DENABLE_UPLOAD_CHKSUM_OPTIONS=${@bb.utils.contains('PACKAGECONFIG', 'enable-chksum-options', 'ON', 'OFF', d)} \
	-DENABLE_DIRECT_WRITE_MODE=${@bb.utils.contains('PACKAGECONFIG', 'enable-direct-write-mode', 'ON', 'OFF', d)} \
"

FILES_${PN} = "\
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
}
