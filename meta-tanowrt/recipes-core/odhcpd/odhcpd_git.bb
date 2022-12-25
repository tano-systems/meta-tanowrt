#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2018 Daniel Dickinson <cshored@thecshore.com>
# Copyright (C) 2018-2021 Anton Kikin <a.kikin@tano-systems.com>
#

PR = "tano27"

SUMMARY = "OpenWrt DHCP/DHCPv6(-PD)/RA Server & Relay"
HOMEPAGE = "http://git.openwrt.org/?p=project/odhcpd.git;a=summary"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://src/odhcpd.c;beginline=1;endline=13;md5=b5b1da01ca7e1cdd0308c552dc0a1384"
SECTION = "base"
DEPENDS = "libubox ubus libnl uci"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI = "\
    git://${GIT_OPENWRT_ORG}/project/odhcpd.git;name=odhcpd;branch=master \
    file://0100-OE-build-fails-due-to-libnl-tiny-dependency-in-CMakeLists.patch \
    file://odhcpd.defaults \
    file://odhcpd.init \
    file://odhcpd-update \
"

# 11.08.2021
# dhcpv4: fix uninitialized hostname in some ubus events
SRCREV = "01b4e6046f10e21809c3f380f2d33bf3fe59698d"
S = "${WORKDIR}/git"

inherit cmake pkgconfig tanowrt-services

TANOWRT_SERVICE_PACKAGES = "odhcpd"
TANOWRT_SERVICE_SCRIPTS_odhcpd += "odhcpd"
TANOWRT_SERVICE_STATE_odhcpd-odhcpd ?= "disabled"

SRCREV_openwrt = "${OPENWRT_SRCREV}"

EXTRA_OECMAKE:append = " -DUBUS=1"

do_install:append() {
    install -Dm 0644 ${WORKDIR}/odhcpd.defaults ${D}${sysconfdir}/uci-defaults/15_odhcpd
    install -Dm 0755 ${WORKDIR}/odhcpd.init ${D}${sysconfdir}/init.d/odhcpd
    install -Dm 0755 ${WORKDIR}/odhcpd-update ${D}${sbindir}/odhcpd-update
}

FILES:${PN} += "\
               ${sysconfdir}/uci-defaults/odhcpd.defaults \
               ${sysconfdir}/init.d/odhcpd \
               ${sbindir}/odhcpd-update \
               "

RDEPENDS:${PN} += "base-files-scripts-openwrt"
