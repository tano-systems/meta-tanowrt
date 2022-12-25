#
# SPDX-License-Identifier: MIT
# Copyright (c) 2018-2020 Tano Systems LLC. All rights reserved.
#
SUMMARY = "Apcupsd a daemon for controlling APC UPSes"

PR = "tano3"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

DEPENDS += "dos2unix-native util-linux-native"

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRC_URI = "https://netcologne.dl.sourceforge.net/project/apcupsd/apcupsd%20-%20Stable/${PV}/apcupsd-${PV}.tar.gz"
SRC_URI[md5sum] = "cc8f5ced77f38906a274787acb9bc980"
SRC_URI[sha256sum] = "db7748559b6b4c3784f9856561ef6ac6199ef7bd019b3edcd7e0a647bf8f9867"

SRC_URI += "file://010-fix-usb.patch"

SRC_URI += "\
	file://apcupsd.init \
	file://apccontrol \
	file://apcupsd.conf \
	file://config \
	file://commok \
	file://changeme \
	file://onbattery \
	file://offbattery \
	file://commfailure \
"

inherit autotools pkgconfig tanowrt-services

TANOWRT_SERVICE_PACKAGES = "apcupsd"
TANOWRT_SERVICE_SCRIPTS_apcupsd += "apcupsd"
TANOWRT_SERVICE_STATE_apcupsd-apcupsd ?= "disabled"

LD = "${CXX}"

EXTRA_OECONF = " \
	--with-distname=${DISTRO} \
	--sysconfdir=${sysconfdir}/apcupsd \
"

PACKAGECONFIG ??= "usb"

PACKAGECONFIG[usb] = "--enable-usb,--disable-usb,libusb1"
PACKAGECONFIG[cgi] = "--enable-cgi,--disable-cgi,gd"
PACKAGECONFIG[x] = "--with-x,--without-x,"

# Build fix
B = "${S}"

do_fix_lineendings() {
	dos2unix ${S}/include/libusb.h.in
}

do_fix_lineendings[depends] = "dos2unix-native:do_populate_sysroot"
addtask fix_lineendings after do_unpack before do_patch

do_configure() {
    export topdir=${S}
    cp -R --no-dereference --preserve=mode,links -v ${S}/autoconf/* ${S}

    if ! [ -d ${S}/platforms/${DISTRO} ] ; then
        cp -R --no-dereference --preserve=mode,links -v ${S}/platforms/unknown ${S}/platforms/${DISTRO}
    fi

    gnu-configize --force

    # install --help says '-c' is an ignored option, but it turns out that the argument to -c isn't ignored, so drop the complete '-c path/to/strip' line
    sed -i -e 's:$(INSTALL_PROGRAM) $(STRIP):$(INSTALL_PROGRAM):g' ${S}/autoconf/targets.mak
    
    # Searching in host dirs triggers the QA checks
    sed -i -e 's:-I/usr/local/include::g' -e 's:-L/usr/local/lib64::g' -e 's:-L/usr/local/lib::g' ${S}/configure

    # m4 macros are missing, using autotools_do_configure leads to linking errors with gethostname_re
    oe_runconf
}

do_install:append() {
	rm ${D}${datadir}/hal -rf

	install -d ${D}${sysconfdir}/apcupsd
	install -m 0644 ${WORKDIR}/config ${D}${sysconfdir}/apcupsd/config
	install -m 0644 ${WORKDIR}/apcupsd.conf ${D}${sysconfdir}/apcupsd/apcupsd.conf

	install -m 0755 ${WORKDIR}/apccontrol ${D}${sysconfdir}/apcupsd/apccontrol
	install -m 0755 ${WORKDIR}/commok ${D}${sysconfdir}/apcupsd/commok
	install -m 0755 ${WORKDIR}/changeme ${D}${sysconfdir}/apcupsd/changeme
	install -m 0755 ${WORKDIR}/onbattery ${D}${sysconfdir}/apcupsd/onbattery
	install -m 0755 ${WORKDIR}/offbattery ${D}${sysconfdir}/apcupsd/offbattery
	install -m 0755 ${WORKDIR}/commfailure ${D}${sysconfdir}/apcupsd/commfailure

	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/apcupsd.init ${D}${sysconfdir}/init.d/apcupsd
}
