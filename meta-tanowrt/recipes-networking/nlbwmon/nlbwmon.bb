#
# This file Copyright (C) 2018-2020 Anton Kikin <a.kikin@tano-systems.com>
#
# OpenWrt/LEDE Traffic Usage Monitor
#
PR = "tano3"

DESCRIPTION = "LEDE Traffic Usage Monitor"
LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://COPYING;md5=0ac4788840cdc6290086d6a642deafd6"
SECTION = "net"

DEPENDS += "libubox zlib libnl"

RDEPENDS_${PN} += "\
	kmod-nf-conntrack-netlink \
"

do_configure[depends] += "virtual/kernel:do_shared_workdir"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI = "\
	git://github.com/jow-/nlbwmon.git \
"
SRCREV = "e921ca0af9957d3cb05797acfb8bde4d7d2278e5"

SRC_URI += "\
	file://nlbwmon.init \
	file://nlbwmon.config \
"

inherit cmake
inherit openwrt-services

OPENWRT_SERVICE_PACKAGES = "nlbwmon"
OPENWRT_SERVICE_SCRIPTS_nlbwmon += "nlbwmon"
OPENWRT_SERVICE_STATE_nlbwmon-nlbwmon ?= "enabled"

OECMAKE_C_FLAGS += "-I${STAGING_INCDIR}/libnl3 -Wno-error=cpp"
EXTRA_OECMAKE_append = " -DLIBNL_LIBRARY_TINY=OFF"

S = "${WORKDIR}/git"

do_install_append() {
	install -dm 0755 ${D}${sysconfdir}/config
	install -m 0644 ${WORKDIR}/nlbwmon.config ${D}${sysconfdir}/config/nlbwmon

	install -dm 0755 ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/nlbwmon.init ${D}${sysconfdir}/init.d/nlbwmon

	ln -sf nlbwmon ${D}${sbindir}/nlbw

	install -dm 0755 ${D}/usr/share/nlbwmon
	install -m 0644 ${S}/protocols.txt ${D}/usr/share/nlbwmon/protocols
}

CONFFILES_${PN}_append = "\
	${sysconfdir}/config/nlbwmon \
	/usr/share/nlbwmon/protocols \
"
