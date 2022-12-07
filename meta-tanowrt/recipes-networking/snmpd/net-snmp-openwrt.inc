#
# SPDX-License-Identifier: MIT
# This file Copyright (C) 2018-2021 Anton Kikin <a.kikin@tano-systems.com>
#

INC_PR = "3"

EXTRA_OECONF += "--with-logfile=/var/log/snmpd.log"

inherit tanowrt-services

TANOWRT_SERVICE_PACKAGES = "net-snmp-server-snmpd net-snmp-server-snmptrapd"

TANOWRT_SERVICE_SCRIPTS_net-snmp-server-snmpd += "snmpd"
TANOWRT_SERVICE_STATE_net-snmp-server-snmpd-snmpd ?= "enabled"

TANOWRT_SERVICE_SCRIPTS_net-snmp-server-snmptrapd += "snmptrapd"
TANOWRT_SERVICE_STATE_net-snmp-server-snmptrapd-snmptrapd ?= "disabled"

SRC_URI += "\
	file://snmpd.config \
	file://snmpd.init \
	file://snmptrapd.init \
	file://000-cross-compile.patch \
	file://010-HOST-MIB-hr_filesys-fix-compile-error.patch \
	file://100-debian-statistics.patch \
	file://110-debian-makefiles.patch \
	file://120-debian-searchdirs.patch \
	file://130-debian-extramibs.patch \
	file://160-no_ldconfig.patch \
	file://170-ldflags.patch \
	file://750-ieee802dot11.patch \
	file://900-musl-compat.patch \
	file://901-ignore-interfaces.patch \
"

##
## OpenWrt build options
##	--enable-mfd-rewrites \
##	--enable-static \
##	--with-default-snmp-version=1 \
##	--with-sys-contact=root@localhost \
##	--with-sys-location=Unknown \
##	--enable-applications \
##	--disable-debugging \
##	--disable-scripts \
##	--with-out-mib-modules="$(SNMP_MIB_MODULES_EXCLUDED)" \
##	--with-mib-modules="$(SNMP_MIB_MODULES_INCLUDED)" \
##	--with-out-transports="$(SNMP_TRANSPORTS_EXCLUDED)" \
##	--with-transports="$(SNMP_TRANSPORTS_INCLUDED)" \
##	--without-openssl \
##	--without-libwrap \
##	--without-mysql \
##	--without-rpm \
##	--without-zlib \
##	--with-nl \
##

do_install:append() {
	# Remove unneeded files installed by original OE recipe
	rm -rf ${D}${sysconfdir}/snmp/*
	rm -rf ${D}${systemd_unitdir}
	rm -f ${D}${sysconfdir}/init.d/snmpd

	# Install OpenWrt files
	install -d ${D}${sysconfdir}/snmp
	install -d ${D}${sysconfdir}/init.d
	install -d ${D}${sysconfdir}/config

	install -m 0755 ${WORKDIR}/snmpd.init ${D}${sysconfdir}/init.d/snmpd
	install -m 0755 ${WORKDIR}/snmptrapd.init ${D}${sysconfdir}/init.d/snmptrapd
	install -m 0644 ${WORKDIR}/snmpd.config ${D}${sysconfdir}/config/snmpd

	ln -s /var/run/snmpd.conf ${D}${sysconfdir}/snmp/snmpd.conf
}

FILES:${PN}-server-snmpd = "${sbindir}/snmpd"
FILES:${PN}-server-snmptrapd = "${sbindir}/snmptrapd"

FILES:${PN}-server-snmpd += "\
	${sysconfdir}/config/snmpd \
	${sysconfdir}/snmp/snmpd.conf \
	${sysconfdir}/init.d/snmpd"

FILES:${PN}-server-snmptrapd += "\
	${sysconfdir}/init.d/snmptrapd"

CONFFILES:${PN}-server-snmpd = "\
	${sysconfdir}/config/snmpd \
	${sysconfdir}/snmp/snmpd.conf"

# Provide OpenWrt package names
RPROVIDES:${PN}-server-snmpd     += "snmpd"
RPROVIDES:${PN}-server-snmptrapd += "snmptrapd"
RPROVIDES:${PN}-client           += "snmp-utils"
RPROVIDES:${PN}-mibs             += "snmp-mibs"
RPROVIDES:${PN}-libs             += "libnetsnmp"

CONFFILES:${PN}:append = "\
	${sysconfdir}/config/snmpd \
"
