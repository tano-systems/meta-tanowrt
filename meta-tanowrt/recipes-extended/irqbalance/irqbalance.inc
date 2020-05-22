#
# Copyright (C) 2013 Wind River Systems, Inc.
# Copyright (C) 2019-2020 Tano Systems LLC
#
SUMMARY = "IRQ usage balancing for multi-core systems"
DESCRIPTION = "A daemon to balance interrupts across multiple CPUs, \
which can lead to better performance and IO balance on SMP systems."

HOMEPAGE = "http://code.google.com/p/irqbalance/"
BUGTRACKER = "http://code.google.com/p/irqbalance/issues/list"

INC_PR = "2"

SRC_URI += "file://irqbalance.config.in \
            file://irqbalance.init \
           "

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f \
                    file://irqbalance.c;beginline=6;endline=8;md5=b94e153694672307b503b1bc87dc9e24 \
                   "

DEPENDS = "glib-2.0"

inherit autotools pkgconfig tanowrt-services

TANOWRT_SERVICE_PACKAGES = "irqbalance"
TANOWRT_SERVICE_SCRIPTS_irqbalance ?= "irqbalance"
TANOWRT_SERVICE_STATE_irqbalance-irqbalance ?= "enabled"

EXTRA_OECONF = "--program-transform-name= \
                --with-systemd=no \
                --without-irqbalance-ui \
               "

IRQBALANCE_CONFIG_ENABLED ?= "0"

# let the recipes or appends define options
PACKAGECONFIG ?= "static-glib2"

# enable,disable,depends,rdepends
#
PACKAGECONFIG[static-glib2] = "--enable-static=glib2,,"
PACKAGECONFIG[numa] = "--enable-numa,--disable-numa,numactl,"
PACKAGECONFIG[libcap-ng] = "--with-libcap-ng,--without-libcap-ng,libcap-ng,"

do_compile_append() {
	sed -e 's/@@IRQBALANCE_CONFIG_ENABLED@@/${IRQBALANCE_CONFIG_ENABLED}/' \
		"${WORKDIR}/irqbalance.config.in" > "${WORKDIR}/irqbalance.config"
}

do_install () {
	oe_runmake 'DESTDIR=${D}' install

	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/irqbalance.init ${D}${sysconfdir}/init.d/irqbalance

	install -d ${D}${sysconfdir}/config
	install -m 0644 ${WORKDIR}/irqbalance.config ${D}${sysconfdir}/config/irqbalance
}
