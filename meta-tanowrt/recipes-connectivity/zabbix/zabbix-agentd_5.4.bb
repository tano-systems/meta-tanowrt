#
# SPDX-License-Identifier: MIT
#
# This file Copyright (c) 2022 Tano Systems. All Rights Reserved.
# Author: Anton Kikin <a.kikin@tano-systems.com>
#

DESCRIPTION = "Zabbix agent"
SUMMARY = "Open-source monitoring solution for your IT infrastructure"
HOMEPAGE = "http://www.zabbix.com/"
SECTION = "Applications/Internet"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=300e938ad303147fede2294ed78fe02e"
DEPENDS  = "libevent libpcre virtual/libiconv zlib openssl"

PACKAGE_ARCH = "${MACHINE_ARCH}"

RRECOMMENDS:${PN} += "logrotate"
RRECOMMENDS:${PN} += "rsyslog"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

# 23-Dec-2021 09:00
PV = "5.4.9"
PR = "tano1"

SRC_URI = "https://cdn.zabbix.com/zabbix/sources/stable/5.4/zabbix-${PV}.tar.gz \
           file://0001-Fix-configure.ac.patch"

# Files
SRC_URI += "\
	file://zabbix_agentd.init \
	file://zabbix_agentd.config \
	file://zabbix_agentd.rsyslog \
	file://zabbix_agentd.logrotate \
"

S = "${WORKDIR}/zabbix-${PV}"
SRC_URI[sha256sum] = "19686628df76e8d5ef7c2ed2975b258c7ca3ec7d151b1bb59d7e132f9fc7c941"

inherit autotools-brokensep linux-kernel-base useradd pkgconfig

inherit tanowrt-services

TANOWRT_SERVICE_PACKAGES = "zabbix-agentd"
TANOWRT_SERVICE_SCRIPTS_zabbix-agentd += "zabbix_agentd"
TANOWRT_SERVICE_STATE_zabbix-agentd-zabbix_agentd ?= "disabled"

USERADD_PACKAGES = "${PN}"
GROUPADD_PARAM:${PN} = "-r zabbix"
USERADD_PARAM:${PN} = "-r -g zabbix -d /var/lib/zabbix \
	-s /sbin/nologin -c \"Zabbix Monitoring System\" zabbix \
"

EXTRA_OECONF = "\
	--enable-agent \
	--enable-ipv6 \
	--disable-java \
	--with-libpcre=${STAGING_EXECPREFIXDIR} \
	--with-openssl=${STAGING_EXECPREFIXDIR} \
	--with-iconv=${STAGING_EXECPREFIXDIR} \
"

KERNEL_VERSION = "${@get_kernelversion_headers('${STAGING_KERNEL_DIR}')}"

CFLAGS:append = " -pthread"

do_configure[depends] += "virtual/kernel:do_shared_workdir"

do_configure:prepend() {
	export KERNEL_VERSION="${KERNEL_VERSION}"
}

do_install:append() {
	rm  -f "${D}${sysconfdir}/zabbix_agentd.conf"
	rm -rf "${D}${sysconfdir}/zabbix_agentd.conf.d"

	install -d ${D}${sysconfdir}/config
	install -m 0644 ${WORKDIR}/zabbix_agentd.config \
		${D}${sysconfdir}/config/zabbix_agentd

	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/zabbix_agentd.init \
		${D}${sysconfdir}/init.d/zabbix_agentd

	install -d ${D}${sysconfdir}/rsyslog.d
	install -m 0644 ${WORKDIR}/zabbix_agentd.rsyslog \
		${D}${sysconfdir}/rsyslog.d/zabbix_agentd.conf

	install -d ${D}${sysconfdir}/logrotate.d
	install -m 0644 ${WORKDIR}/zabbix_agentd.logrotate \
		${D}${sysconfdir}/logrotate.d/zabbix_agentd
}

pkg_postinst_ontarget:${PN}() {
if [ -x "/etc/init.d/rsyslog" ]; then
	/etc/init.d/rsyslog running && /etc/init.d/rsyslog restart || true
fi
/etc/init.d/zabbix_agentd enabled && /etc/init.d/zabbix_agentd restart || true
}

pkg_prerm:${PN}() {
[ "$PKG_UPGRADE" != 1 ] && /etc/init.d/zabbix_agentd disable || true
/etc/init.d/zabbix_agentd stop
}

FILES:${PN} += "${libdir}"

CONFFILES:${PN}:append = "\
	${sysconfdir}/config/zabbix_agentd \
"
