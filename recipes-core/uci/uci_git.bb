# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>
# Released under the MIT license (see COPYING.MIT for the terms)

PR = "tano2"
SUMMARY = "Library and utility for the Unified Configuration Interface for OpenWrt"
HOMEPAGE = "http://wiki.openwrt.org/doc/uci"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://uci.h;beginline=1;endline=13;md5=0ee862ed12171ee619c8c2eb7eff77f2"
SECTION = "base"
DEPENDS = "libubox lua5.1"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI = "\
	git://git.openwrt.org/project/uci.git;branch=master;name=uci \
	file://uci.sh \
"

# 11.08.2018
# delta: fix parsing malformed delta lines
SRCREV = "4c8b4d6efc8302b508d261573351fffb75bd98c2"

S = "${WORKDIR}/git"

inherit cmake pkgconfig openwrt

SRCREV_openwrt = "${OPENWRT_SRCREV}"

OECMAKE_C_FLAGS += "-I${STAGING_INCDIR}/lua5.1"

do_install_append() {
    install -Dm 0755 ${WORKDIR}/uci.sh ${D}${base_libdir}/config/uci.sh

    mkdir -p ${D}/sbin
    mkdir -p ${D}/usr/sbin
    ln -s /usr/bin/uci ${D}/usr/sbin/uci
    ln -s /usr/bin/uci ${D}/sbin/uci
}

FILES_${PN} += "${base_libdir}"
