#
# Copyright (C) 2013 Wind River Systems, Inc.
# Copyright (C) 2019 Tano Systems, Ltd.
#
SUMMARY = "IRQ allocation daemon"
DESCRIPTION = "A daemon to balance interrupts across multiple CPUs, \
which can lead to better performance and IO balance on SMP systems."

HOMEPAGE = "http://code.google.com/p/irqbalance/"
BUGTRACKER = "http://code.google.com/p/irqbalance/issues/list"

INC_PR = "0"

SRC_URI += "file://irqbalance.config.in \
            file://irqbalance.init \
           "

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f \
                    file://irqbalance.c;beginline=6;endline=8;md5=b94e153694672307b503b1bc87dc9e24 \
                   "

DEPENDS = "glib-2.0"

inherit autotools pkgconfig openwrt-services

OPENWRT_SERVICE_PACKAGES = "irqbalance"
OPENWRT_SERVICE_SCRIPTS_irqbalance ?= "irqbalance"
OPENWRT_SERVICE_STATE_irqbalance-irqbalance ?= "enabled"

EXTRA_OECONF = "--program-transform-name= \
                --with-systemd=no \
               "

IRQBALANCE_CONFIG_ENABLED ?= "0"

# let the recipes or appends define options
PACKAGECONFIG ?= ""

# enable,disable,depends,rdepends
#
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
	install -m 0755 ${WORKDIR}/irqbalance.config ${D}${sysconfdir}/config/irqbalance
}
