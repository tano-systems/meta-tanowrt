#
# SPDX-License-Identifier: MIT
#
# This file Copyright (C) 2018-2020 Anton Kikin <a.kikin@tano-systems.com>
#
# OpenWrt/LEDE Traffic Usage Monitor
#
PR = "tano5"

DESCRIPTION = "LEDE Traffic Usage Monitor"
LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=0ac4788840cdc6290086d6a642deafd6"
SECTION = "net"

DEPENDS += "libubox zlib libnl"

inherit kmod/nf-conntrack-netlink

do_configure[depends] += "virtual/kernel:do_shared_workdir"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI = "\
	git://github.com/jow-/nlbwmon.git;branch=master;protocol=https \
"

# 2020-04-11.1
SRCREV = "34a188769750927d839ad27fbfc90c973fe7bb06"

SRC_URI += "\
	file://nlbwmon.init \
	file://nlbwmon.config \
"

inherit cmake
inherit tanowrt-services

TANOWRT_SERVICE_PACKAGES = "nlbwmon"
TANOWRT_SERVICE_SCRIPTS_nlbwmon += "nlbwmon"
TANOWRT_SERVICE_STATE_nlbwmon-nlbwmon ?= "enabled"

OECMAKE_C_FLAGS += "-I${STAGING_INCDIR}/libnl3 -Wno-error=cpp"
EXTRA_OECMAKE:append = " -DLIBNL_LIBRARY_TINY=OFF"

S = "${WORKDIR}/git"

do_install:append() {
	install -dm 0755 ${D}${sysconfdir}/config
	install -m 0644 ${WORKDIR}/nlbwmon.config ${D}${sysconfdir}/config/nlbwmon

	install -dm 0755 ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/nlbwmon.init ${D}${sysconfdir}/init.d/nlbwmon

	ln -sf nlbwmon ${D}${sbindir}/nlbw

	install -dm 0755 ${D}/usr/share/nlbwmon
	install -m 0644 ${S}/protocols.txt ${D}/usr/share/nlbwmon/protocols
}

CONFFILES:${PN}:append = "\
	${sysconfdir}/config/nlbwmon \
	/usr/share/nlbwmon/protocols \
"
