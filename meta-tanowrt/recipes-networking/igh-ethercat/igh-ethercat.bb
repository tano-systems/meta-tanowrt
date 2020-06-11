# This file Copyright (C) 2020 Tano Systems LLC
# Released under the MIT license (see COPYING.MIT for the terms)

PR = "tano0"
PV = "1.5.2"

DESCRIPTION = "IgH EtherCAT Master for Linux"
HOMEPAGE = "http://etherlab.org/download/ethercat"
LICENSE = "GPL-2.0 & LGPL-2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552"
SECTION = "net"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI = "http://etherlab.org/download/ethercat/ethercat-${PV}.tar.bz2"
SRC_URI[md5sum] = "6b4001f8d975865d74a0b108b3bdda3d"
SRC_URI[sha256sum] = "5f34ef3a5e1b8666ae77650917d0ec6eb4d7a437b3b66ea667a61158c8f4e8c4"

SRC_URI += "\
	file://ethercatctl \
	file://ethercat.config \
	file://ethercat.init \
"

SRC_URI += "\
	file://0001-Fixed-compilation-error-for-the-EtherCat-drivers.patch \
	file://0002-Modify-the-example-code.patch \
	file://0003-Fixed-error-that-the-EtherCat-can-not-load-the-modul.patch\
	file://0004-Fixed-compilation-error-for-the-IGH-EtherCAT.patch \
	file://0005-Replace-the-init_timer-with-timer_setup-function.patch \
	file://1001-Fix-subdir-objects-configure-warning.patch \
	file://1002-Add-support-for-5.x-kernels.patch \
"

S = "${WORKDIR}/ethercat-${PV}"

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

do_compile_append() {
	oe_runmake modules
}

do_install_append() {
	oe_runmake MODLIB=${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION} modules_install

	rm -rf ${D}${libdir}/systemd
	rm -rf ${D}${sysconfdir}/sysconfig
	rm  -f ${D}${sysconfdir}/ethercat.conf
	rm  -f ${D}${sbindir}/ethercatctl

	install -m 0755 -D ${WORKDIR}/ethercatctl ${D}${sbindir}/ethercatctl
	install -m 0644 -D ${WORKDIR}/ethercat.config ${D}${sysconfdir}/config/ethercat
	install -m 0755 -D ${WORKDIR}/ethercat.init ${D}${sysconfdir}/init.d/ethercat

	cd ${D}/lib/modules/${KERNEL_VERSION} && \
		find kernel -name '*.ko' -exec sh -c 'mod="{}"; ln -sf $mod ${D}/lib/modules/${KERNEL_VERSION}/$(basename "$mod")' \;
}

FILES_${PN} += "${nonarch_base_libdir}/modules/${KERNEL_VERSION}"
CONFFILES_${PN} += "${sysconfdir}/config/ethercat"

inherit tanowrt-services

TANOWRT_SERVICE_PACKAGES = "igh-ethercat"
TANOWRT_SERVICE_SCRIPTS_igh-ethercat += "ethercat"
TANOWRT_SERVICE_STATE_igh-ethercat-ethercat ?= "disabled"
