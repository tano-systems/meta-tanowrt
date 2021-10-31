#
# SPDX-License-Identifier: MIT
# Copyright (c) 2021 Tano Systems LLC. All rights reserved.
#
PR_append = ".tano2"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

inherit tanowrt-services

TANOWRT_SERVICE_PACKAGES = "keepalived"
TANOWRT_SERVICE_SCRIPTS_keepalived += "keepalived"
TANOWRT_SERVICE_STATE_keepalived-keepalived ?= "disabled"

SRC_URI += "\
	file://hotplug-user \
	file://keepalived.config \
	file://keepalived.init \
	file://keepalived.uci-defaults \
	file://keepalived.user \
	file://keepalived.rsyslog \
	file://keepalived.logrotate \
"

PACKAGES += "${PN}-mib"

PACKAGECONFIG ??= "libnl snmp"
PACKAGECONFIG[snmp] = "--enable-snmp --enable-snmp-vrrp --enable-snmp-rfcv2 --enable-snmp-rfcv3,--disable-snmp,net-snmp"

do_install_append() {
	rm -rf ${D}${sysconfdir}/sysconfig

	install -d ${D}${datadir}/snmp/mibs

	install -d ${D}${sysconfdir}/uci-defaults
	install -m 0755 ${WORKDIR}/keepalived.uci-defaults \
		${D}${sysconfdir}/uci-defaults/keepalived

	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/keepalived.init \
		${D}${sysconfdir}/init.d/keepalived

	install -d ${D}${sysconfdir}/config
	install -m 0644 ${WORKDIR}/keepalived.config \
		${D}${sysconfdir}/config/keepalived

	install -d ${D}${sysconfdir}
	install -m 0644 ${WORKDIR}/keepalived.user \
		${D}${sysconfdir}/keepalived.user

	install -d ${D}${sysconfdir}/hotplug.d/keepalived
	install -m 0644 ${WORKDIR}/hotplug-user \
		${D}${sysconfdir}/hotplug.d/keepalived/01-user

	install -d ${D}${sysconfdir}/rsyslog.d
	install -m 0644 ${WORKDIR}/keepalived.rsyslog \
		${D}${sysconfdir}/rsyslog.d/keepalived.conf

	install -d ${D}${sysconfdir}/logrotate.d
	install -m 0644 ${WORKDIR}/keepalived.logrotate \
		${D}${sysconfdir}/logrotate.d/keepalived
}

FILES_${PN}-mib = "${datadir}/snmp/mibs/"

CONFFILES_${PN} += "\
	${sysconfdir}/keepalived/keepalived.conf \
	${sysconfdir}/config/keepalived \
	${sysconfdir}/keepalived.user \
"

EXTRA_OECONF += "\
	--enable-vrrp \
	--enable-lvs \
	--enable-bfd \
	--enable-json \
	--disable-dbus \
	--disable-nftables \
	--disable-track-process \
	--with-run-dir='/var/run' \
"

FILES_${PN}_remove = "${datadir}/snmp/mibs/KEEPALIVED-MIB.txt"
