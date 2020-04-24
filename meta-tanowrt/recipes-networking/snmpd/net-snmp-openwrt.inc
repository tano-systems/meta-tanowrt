# This file Copyright (C) 2018-2020 Anton Kikin <a.kikin@tano-systems.com>

INC_PR = "2"

EXTRA_OECONF += "--with-logfile=/var/log/snmpd.log"

inherit openwrt-services

OPENWRT_SERVICE_PACKAGES = "net-snmp-server-snmpd net-snmp-server-snmptrapd"

OPENWRT_SERVICE_SCRIPTS_net-snmp-server-snmpd += "snmpd"
OPENWRT_SERVICE_STATE_net-snmp-server-snmpd-snmpd ?= "enabled"

OPENWRT_SERVICE_SCRIPTS_net-snmp-server-snmptrapd += "snmptrapd"
OPENWRT_SERVICE_STATE_net-snmp-server-snmptrapd-snmptrapd ?= "disabled"

SRC_URI += "\
	file://snmpd.config \
	file://snmpd.init \
	file://snmptrapd.init \
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

do_install_append() {
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

FILES_${PN}-server-snmpd = "${sbindir}/snmpd"
FILES_${PN}-server-snmptrapd = "${sbindir}/snmptrapd"

FILES_${PN}-server-snmpd += "\
	${sysconfdir}/config/snmpd \
	${sysconfdir}/snmp/snmpd.conf \
	${sysconfdir}/init.d/snmpd"

FILES_${PN}-server-snmptrapd += "\
	${sysconfdir}/init.d/snmptrapd"

CONFFILES_${PN}-server-snmpd = "\
	${sysconfdir}/config/snmpd \
	${sysconfdir}/snmp/snmpd.conf"

# Provide OpenWrt package names
RPROVIDES_${PN}-server-snmpd     += "snmpd"
RPROVIDES_${PN}-server-snmptrapd += "snmptrapd"
RPROVIDES_${PN}-client           += "snmp-utils"
RPROVIDES_${PN}-mibs             += "snmp-mibs"
RPROVIDES_${PN}-libs             += "libnetsnmp"

CONFFILES_${PN}_append = "\
	${sysconfdir}/config/snmpd \
"
