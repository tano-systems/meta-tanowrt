#
# SPDX-License-Identifier: MIT
# Copyright (c) 2020-2021, 2023 Tano Systems LLC. All rights reserved.
#

PR = "tano5"
PV = "1.5.2+git${SRCPV}"

DESCRIPTION = "IgH EtherCAT Master for Linux"
HOMEPAGE = "http://etherlab.org/download/ethercat"
LICENSE = "GPL-2.0-only & LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552"
SECTION = "net"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI = "git://gitlab.com/etherlab.org/ethercat.git;protocol=https;branch=stable-1.5"

# 09.06.2021
# Ignore cmake output.
SRCREV = "1fa5565aa0287223c937b7474d9926d08b9582e6"

SRC_URI += "\
	file://ethercatctl \
	file://ethercat.config \
	file://ethercat.init \
"

SRC_URI += "\
	file://1003-Fix-ethercat-tool-compilation.patch \
"

S = "${WORKDIR}/git"

PACKAGECONFIG ??= "generic"

PACKAGECONFIG[generic] = "--enable-generic,--disable-generic,"
PACKAGECONFIG[8139too] = "--enable-8139too,--disable-8139too,"
PACKAGECONFIG[e100]    = "--enable-e100,--disable-e100,"
PACKAGECONFIG[e1000]   = "--enable-e1000,--disable-e1000,"
PACKAGECONFIG[e1000e]  = "--enable-e1000e,--disable-e1000e,"
PACKAGECONFIG[r8169]   = "--enable-r8169,--disable-r8169,"

do_configure[depends] += "virtual/kernel:do_shared_workdir"

inherit autotools-brokensep pkgconfig module-base

EXTRA_OECONF += "--with-linux-dir=${STAGING_KERNEL_BUILDDIR}"
EXTRA_OECONF += "--with-module-dir=kernel/ethercat"

do_configure:prepend() {
	# Fixes configure error
	# | Makefile.am: error: required file './ChangeLog' not found"
	touch ChangeLog
}

do_compile:append() {
	oe_runmake modules
}

do_install:append() {
	oe_runmake MODLIB=${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION} modules_install

	rm -rf ${D}${libdir}/systemd
	rm -rf ${D}${sysconfdir}/sysconfig
	rm  -f ${D}${sysconfdir}/ethercat.conf
	rm  -f ${D}${sbindir}/ethercatctl

	install -m 0755 -D ${WORKDIR}/ethercatctl ${D}${sbindir}/ethercatctl
	install -m 0644 -D ${WORKDIR}/ethercat.config ${D}${sysconfdir}/config/ethercat
	install -m 0755 -D ${WORKDIR}/ethercat.init ${D}${sysconfdir}/init.d/ethercat
}

FILES:${PN} += "${nonarch_base_libdir}/modules/${KERNEL_VERSION}"
CONFFILES:${PN} += "${sysconfdir}/config/ethercat"

inherit tanowrt-services

TANOWRT_SERVICE_PACKAGES = "igh-ethercat"
TANOWRT_SERVICE_SCRIPTS_igh-ethercat += "ethercat"
TANOWRT_SERVICE_STATE_igh-ethercat-ethercat ?= "disabled"

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
