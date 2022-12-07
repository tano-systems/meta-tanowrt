#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Copyright (C) 2018-2022 Anton Kikin <a.kikin@tano-systems.com>
#

PR = "tano15"
SUMMARY = "Library and utility for the Unified Configuration Interface for OpenWrt"
HOMEPAGE = "http://wiki.openwrt.org/doc/uci"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://uci.h;beginline=1;endline=13;md5=0ee862ed12171ee619c8c2eb7eff77f2"
SECTION = "base"
DEPENDS = "libubox lua5.1"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI = "\
	git://${GIT_OPENWRT_ORG}/project/uci.git;branch=master;name=uci \
	file://uci.sh \
"

PROVIDES += "libuci libuci-lua"
RPROVIDES:${PN} += "libuci-lua libuci"

# 24.04.2020
# file: preserve original file mode after commit
SRCREV = "ec8d3233948603485e1b97384113fac9f1bab5d6"

S = "${WORKDIR}/git"

inherit cmake pkgconfig tanowrt-lua

FILES_SOLIBSDEV = ""

SRCREV_openwrt = "${OPENWRT_SRCREV}"

OECMAKE_C_FLAGS += "-I${STAGING_INCDIR}/lua5.1"

do_install:append() {
    install -Dm 0755 ${WORKDIR}/uci.sh ${D}${nonarch_base_libdir}/config/uci.sh

    mkdir -p ${D}/sbin
    mkdir -p ${D}/usr/sbin
    ln -s /usr/bin/uci ${D}/usr/sbin/uci
    ln -s /usr/bin/uci ${D}/sbin/uci
}

FILES:${PN} += "${nonarch_base_libdir}"

BBCLASSEXTEND = "native nativesdk"
