# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>
# Released under the MIT license (see COPYING.MIT for the terms)

PR = "tano1"

DESCRIPTION = "OpenWrt MDNS daemon"
HOMEPAGE = "http://git.openwrt.org/?p=project/mdnsd.git;a=summary"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://main.c;beginline=1;endline=12;md5=ce0be9da20a926574bf76c1285343bef"
SECTION = "base"
DEPENDS = "json-c libubox ubus"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI = "\
	git://git.openwrt.org/project/mdnsd.git \
	file://0001-Fix-compile-warnings-treated-as-errors.patch \
"

# 02.01.2018
# umdnsd: Replace strerror(errno) with %m.
SRCREV = "78974417e182a3de8f78b7d73366ec0c98396b6c"

S = "${WORKDIR}/git"

inherit cmake pkgconfig openwrt

FILES_${PN}  += "${libdir}/*"
