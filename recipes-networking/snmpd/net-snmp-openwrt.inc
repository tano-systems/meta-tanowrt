#

inherit openwrt-services

OPENWRT_SERVICE_PACKAGES = "${PN}-server-snmpd ${PN}-server-snmptrapd"

OPENWRT_SERVICE_SCRIPTS_${PN}-server-snmpd = "snmpd"
OPENWRT_SERVICE_STATE_${PN}-server-snmpd-snmpd = "enabled"

OPENWRT_SERVICE_SCRIPTS_${PN}-server-snmptrapd = "snmptrapd"
OPENWRT_SERVICE_STATE_${PN}-server-snmptrapd-snmptrapd = "disabled"

#	file://000-cross-compile.patch
#	file://160-no_ldconfig.patch
#	file://170-ldflags.patch
#	file://900-musl-compat.patch

SRC_URI += "\
	file://snmpd.config \
	file://snmpd.init \
	file://snmptrapd.init \
	file://100-debian-statistics.patch \
	file://110-debian-makefiles.patch \
	file://120-debian-searchdirs.patch \
	file://130-debian-extramibs.patch \
	file://170-ldflags.patch \
	file://750-ieee802dot11.patch"

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
	install -d ${D}${sysconfdir}/snmp
	install -d ${D}${sysconfdir}/init.d
	install -d ${D}${sysconfdir}/config

	install -m 0755 ${WORKDIR}/snmpd.init ${D}${sysconfdir}/init.d/snmpd
	install -m 0755 ${WORKDIR}/snmptrapd.init ${D}${sysconfdir}/init.d/snmptrapd
	install -m 0644 ${WORKDIR}/snmpd.config ${D}${sysconfdir}/config/snmpd

	ln -s /var/run/snmpd.conf ${D}${sysconfdir}/snmp/snmpd.conf
}

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
