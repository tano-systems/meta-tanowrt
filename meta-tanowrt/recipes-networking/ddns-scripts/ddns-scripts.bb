#
# Dynamic DNS Client scripts (with IPv6 support)
#
# This file Copyright (c) 2018-2020, Tano Systems. All Rights Reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
PR = "tano10"
PV = "2.7.8"

inherit allarch

SUMMARY = "Dynamic DNS Client scripts (with IPv6 support)"
SECTION = "net/misc"
LICENSE = "GPL-2.0 & MPL-2.0"
LIC_FILES_CHKSUM += "file://${COREBASE}/meta/files/common-licenses/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"
LIC_FILES_CHKSUM += "file://${COREBASE}/meta/files/common-licenses/MPL-2.0;md5=815ca599c9df247a0c7f619bab123dad"

do_configure[noexec] = "1"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/files:"

S = "${WORKDIR}"
B = "${WORKDIR}/build"

SRC_URI = "\
	file://services \
	file://services_ipv6 \
	file://ddns.config \
	file://ddns.hotplug \
	file://ddns.init \
	file://ddns.defaults \
	file://update_godaddy_com_v1.sh \
	file://update_freedns_42_pl.sh \
	file://update_cloudflare_com_v4.sh \
	file://update_nsupdate.sh \
	file://update_no-ip_com.sh \
	file://update_route53_v1.sh \
	file://dynamic_dns_updater.sh \
	file://dynamic_dns_lucihelper.sh \
	file://dynamic_dns_functions.sh \
"

inherit tanowrt-services

TANOWRT_SERVICE_PACKAGES = "ddns-scripts"
TANOWRT_SERVICE_SCRIPTS_ddns-scripts += "ddns"
TANOWRT_SERVICE_STATE_ddns-scripts-ddns ?= "disabled"

DEPENDS += "curl openssl"
RDEPENDS_${PN} += "openssl-bin"
# bind-client

PACKAGES += "\
	${PN}-cloudflare-v4 \
	${PN}-godaddy-com-v1 \
	${PN}-no-ip-com \
	${PN}-nsupdate \
	${PN}-route53-v1 \
	${PN}-freedns-42-pl \
"

do_compile() {
	cp ${S}/services* ${B}/
	cp ${S}/dynamic_dns_*.sh ${B}/
	cp ${S}/update_*.sh ${B}/
	cp ${S}/ddns.* ${B}/

	# ensure that VERSION inside dynamic_dns_functions.sh reflect package version
	sed -i '/^VERSION=*/s/.*/VERSION="${PV}-${PR}"/' ${B}/dynamic_dns_functions.sh
}

do_install() {
	install -d ${D}${sysconfdir}/uci-defaults
	install -d ${D}${sysconfdir}/hotplug.d/iface
	install -d ${D}${sysconfdir}/init.d
	install -d ${D}${sysconfdir}/config

	install -m 0755 ${B}/ddns.defaults ${D}${sysconfdir}/uci-defaults/ddns
	install -m 0755 ${B}/ddns.hotplug ${D}${sysconfdir}/hotplug.d/iface/95-ddns
	install -m 0755 ${B}/ddns.init ${D}${sysconfdir}/init.d/ddns
	install -m 0644 ${B}/ddns.config ${D}${sysconfdir}/config/ddns

	install -d ${D}${sysconfdir}/ddns
	install -d ${D}${libdir}/ddns

	install -m 0644 ${B}/services ${D}${sysconfdir}/ddns/services
	install -m 0644 ${B}/services_ipv6 ${D}${sysconfdir}/ddns/services_ipv6

	install -m 0755 ${B}/dynamic_dns_functions.sh ${D}${libdir}/ddns/
	install -m 0755 ${B}/dynamic_dns_updater.sh ${D}${libdir}/ddns/
	install -m 0755 ${B}/dynamic_dns_lucihelper.sh ${D}${libdir}/ddns/

	install -m 0755 ${B}/update_cloudflare_com_v4.sh ${D}${libdir}/ddns/
	install -m 0755 ${B}/ddns.defaults ${D}${sysconfdir}/uci-defaults/ddns_cloudflare_com_v4

	install -m 0755 ${B}/update_godaddy_com_v1.sh ${D}${libdir}/ddns/
	install -m 0755 ${B}/ddns.defaults ${D}${sysconfdir}/uci-defaults/ddns_godaddy_com_v1

	install -m 0755 ${B}/update_no-ip_com.sh ${D}${libdir}/ddns/
	install -m 0755 ${B}/ddns.defaults ${D}${sysconfdir}/uci-defaults/ddns_no-ip_com

	install -m 0755 ${B}/update_nsupdate.sh ${D}${libdir}/ddns/
	install -m 0755 ${B}/ddns.defaults ${D}${sysconfdir}/uci-defaults/ddns_nsupdate

	install -m 0755 ${B}/update_route53_v1.sh ${D}${libdir}/ddns/
	install -m 0755 ${B}/ddns.defaults ${D}${sysconfdir}/uci-defaults/ddns_route53_v1

	install -m 0755 ${B}/update_freedns_42_pl.sh ${D}${libdir}/ddns/
	install -m 0755 ${B}/ddns.defaults ${D}${sysconfdir}/uci-defaults/ddns_freedns_42_pl
}

FILES_${PN} = "\
	${sysconfdir}/config \
	${sysconfdir}/ddns \
	${sysconfdir}/hotplug.d \
	${sysconfdir}/init.d \
	${sysconfdir}/uci-defaults/ddns \
	${libdir}/ddns/dynamic_dns_functions.sh \
	${libdir}/ddns/dynamic_dns_updater.sh \
	${libdir}/ddns/dynamic_dns_lucihelper.sh \
"

CONFFILES_${PN}_append = "\
	${sysconfdir}/config/ddns \
"

RRECOMMENDS_${PN} += "\
	${PN}-cloudflare-v4 \
	${PN}-godaddy-com-v1 \
	${PN}-no-ip-com \
	${PN}-route53-v1 \
	${PN}-freedns-42-pl \
"

pkg_postinst_${PN}() {
	#!/bin/sh
	IPKG_INSTROOT=$D
	# if NOT run buildroot and PKG_UPGRADE then (re)start service if enabled
	[ -z "${IPKG_INSTROOT}" ] && {
		[ -x /etc/uci-defaults/ddns ] && \
			/etc/uci-defaults/ddns && \
				rm -f /etc/uci-defaults/ddns >/dev/null 2>&1
		/etc/init.d/ddns enabled && \
			/etc/init.d/ddns start >/dev/null 2>&1
	}
}

pkg_prerm_${PN}() {
	#!/bin/sh
	IPKG_INSTROOT=$D
	# if run within buildroot exit
	[ -n "${IPKG_INSTROOT}" ] && exit 0
	# stop running scripts
	/etc/init.d/ddns stop
	/etc/init.d/ddns disable
	# clear LuCI indexcache
	rm -f /tmp/luci-indexcache >/dev/null 2>&1
}

####

SUMMARY_${PN}-cloudflare-v4 = "Dynamic DNS Client scripts extension for CloudFlare.com API-v4"
RDEPENDS_${PN}-cloudflare-v4 = "${PN} curl"
FILES_${PN}-cloudflare-v4 = "${libdir}/ddns/update_cloudflare_com_v4.sh ${sysconfdir}/uci-defaults/ddns_cloudflare_com_v4"

pkg_preinst_${PN}-cloudflare-v4() {
	#!/bin/sh
	IPKG_INSTROOT=$D
	# if NOT run buildroot then stop service
	[ -z "${IPKG_INSTROOT}" ] && /etc/init.d/ddns stop >/dev/null 2>&1
	exit 0	# suppress errors
}

pkg_postinst_${PN}-cloudflare-v4() {
	#!/bin/sh
	IPKG_INSTROOT=$D
	# remove old services file entries
	/bin/sed -i '/cloudflare\.com-v4/d' ${IPKG_INSTROOT}/etc/ddns/services		>/dev/null 2>&1
	/bin/sed -i '/cloudflare\.com-v4/d' ${IPKG_INSTROOT}/etc/ddns/services_ipv6	>/dev/null 2>&1
	# and create new
	printf "%s\\t%s\\n" '"cloudflare.com-v4"' '"update_cloudflare_com_v4.sh"' >> ${IPKG_INSTROOT}/etc/ddns/services
	printf "%s\\t%s\\n" '"cloudflare.com-v4"' '"update_cloudflare_com_v4.sh"' >> ${IPKG_INSTROOT}/etc/ddns/services_ipv6
	# on real system restart service if enabled
	[ -z "${IPKG_INSTROOT}" ] && {
		[ -x /etc/uci-defaults/ddns_cloudflare_com_v4 ] && \
			/etc/uci-defaults/ddns_cloudflare_com_v4 && \
				rm -f /etc/uci-defaults/ddns_cloudflare_com_v4 >/dev/null 2>&1
		/etc/init.d/ddns enabled && \
			/etc/init.d/ddns start >/dev/null 2>&1
	}
	exit 0	# suppress errors
}

pkg_prerm_${PN}-cloudflare-v4() {
	#!/bin/sh
	IPKG_INSTROOT=$D
	# if NOT run buildroot then stop service
	[ -z "${IPKG_INSTROOT}" ] && /etc/init.d/ddns stop				>/dev/null 2>&1
	# remove services file entries
	/bin/sed -i '/cloudflare\.com-v4/d' ${IPKG_INSTROOT}/etc/ddns/services		>/dev/null 2>&1
	/bin/sed -i '/cloudflare\.com-v4/d' ${IPKG_INSTROOT}/etc/ddns/services_ipv6	>/dev/null 2>&1
	exit 0	# suppress errors
}

####

SUMMARY_${PN}-godaddy-com-v1 = "Dynamic DNS Client scripts extension for GoDaddy.com"
RDEPENDS_${PN}-godaddy-com-v1 = "${PN} curl"
FILES_${PN}-godaddy-com-v1 = "${libdir}/ddns/update_godaddy_com_v1.sh ${sysconfdir}/uci-defaults/ddns_godaddy_com_v1"

pkg_preinst_${PN}-godaddy-com-v1() {
	#!/bin/sh
	IPKG_INSTROOT=$D
	# if NOT run buildroot then stop service
	[ -z "${IPKG_INSTROOT}" ] && /etc/init.d/ddns stop >/dev/null 2>&1
	exit 0	# suppress errors
}

pkg_postinst_${PN}-godaddy-com-v1() {
	#!/bin/sh
	IPKG_INSTROOT=$D
	# remove old services file entries
	/bin/sed -i '/godaddy\.com-v1/d' ${IPKG_INSTROOT}/etc/ddns/services		>/dev/null 2>&1
	/bin/sed -i '/godaddy\.com-v1/d' ${IPKG_INSTROOT}/etc/ddns/services_ipv6	>/dev/null 2>&1
	# and create new
	printf "%s\\t%s\\n" '"godaddy.com-v1"' '"update_godaddy_com_v1.sh"' >> ${IPKG_INSTROOT}/etc/ddns/services
	printf "%s\\t%s\\n" '"godaddy.com-v1"' '"update_godaddy_com_v1.sh"' >> ${IPKG_INSTROOT}/etc/ddns/services_ipv6
	# on real system restart service if enabled
	[ -z "${IPKG_INSTROOT}" ] && {
		[ -x /etc/uci-defaults/ddns_godaddy_com_v1 ] && \
			/etc/uci-defaults/ddns_godaddy_com_v1 && \
				rm -f /etc/uci-defaults/ddns_godaddy_com_v1 >/dev/null 2>&1
		/etc/init.d/ddns enabled \
			&& /etc/init.d/ddns start >/dev/null 2>&1
	}
	exit 0	# suppress errors
}

pkg_prerm_${PN}-godaddy-com-v1() {
	#!/bin/sh
	IPKG_INSTROOT=$D
	# if NOT run buildroot then stop service
	[ -z "${IPKG_INSTROOT}" ] && /etc/init.d/ddns stop				>/dev/null 2>&1
	# remove services file entries
	/bin/sed -i '/godaddy\.com-v1/d' ${IPKG_INSTROOT}/etc/ddns/services		>/dev/null 2>&1
	/bin/sed -i '/godaddy\.com-v1/d' ${IPKG_INSTROOT}/etc/ddns/services_ipv6	>/dev/null 2>&1
	exit 0	# suppress errors
}

####

SUMMARY_${PN}-no-ip-com = "Dynamic DNS Client scripts extension for No-IP.com"
RDEPENDS_${PN}-no-ip-com = "${PN}"
FILES_${PN}-no-ip-com = "${libdir}/ddns/update_no-ip_com.sh ${sysconfdir}/uci-defaults/ddns_no-ip_com"

pkg_preinst_${PN}-no-ip-com() {
	#!/bin/sh
	IPKG_INSTROOT=$D
	# if NOT run buildroot then stop service
	[ -z "${IPKG_INSTROOT}" ] && /etc/init.d/ddns stop >/dev/null 2>&1
	exit 0	# suppress errors
}

pkg_postinst_${PN}-no-ip-com() {
	#!/bin/sh
	IPKG_INSTROOT=$D
	# remove old services file entries
	/bin/sed -i '/no-ip\.com/d' ${IPKG_INSTROOT}/etc/ddns/services	>/dev/null 2>&1
	# and create new
	printf "%s\\t%s\\n" '"no-ip.com"' '"update_no-ip_com.sh"' >> ${IPKG_INSTROOT}/etc/ddns/services
	# on real system restart service if enabled
	[ -z "${IPKG_INSTROOT}" ] && {
		[ -x /etc/uci-defaults/ddns_no-ip_com ] && \
			/etc/uci-defaults/ddns_no-ip_com && \
				rm -f /etc/uci-defaults/ddns_no-ip_com >/dev/null 2>&1
		/etc/init.d/ddns enabled && \
			/etc/init.d/ddns start >/dev/null 2>&1
	}
	exit 0	# suppress errors
}

pkg_prerm_${PN}-no-ip-com() {
	#!/bin/sh
	IPKG_INSTROOT=$D
	# if NOT run buildroot then stop service
	[ -z "${IPKG_INSTROOT}" ] && /etc/init.d/ddns stop		>/dev/null 2>&1
	# remove services file entries
	/bin/sed -i '/no-ip\.com/d' ${IPKG_INSTROOT}/etc/ddns/services	>/dev/null 2>&1
	exit 0	# suppress errors
}

####

SUMMARY_${PN}-nsupdate = "Dynamic DNS Client scripts extension for direct updates using Bind nsupdate"
RDEPENDS_${PN}-nsupdate = "${PN}"
# bind-client"
FILES_${PN}-nsupdate = "${libdir}/ddns/update_nsupdate.sh ${sysconfdir}/uci-defaults/ddns_nsupdate"

pkg_preinst_${PN}-nsupdate() {
	#!/bin/sh
	IPKG_INSTROOT=$D
	# if NOT run buildroot then stop service
	[ -z "${IPKG_INSTROOT}" ] && /etc/init.d/ddns stop >/dev/null 2>&1
	exit 0	# suppress errors
}

pkg_postinst_${PN}-nsupdate() {
	#!/bin/sh
	IPKG_INSTROOT=$D
	# remove old services file entries
	/bin/sed -i '/bind-nsupdate/d' ${IPKG_INSTROOT}/etc/ddns/services	>/dev/null 2>&1
	/bin/sed -i '/bind-nsupdate/d' ${IPKG_INSTROOT}/etc/ddns/services_ipv6	>/dev/null 2>&1
	# and create new
	printf "%s\\t%s\\n" '"bind-nsupdate"' '"update_nsupdate.sh"' >> ${IPKG_INSTROOT}/etc/ddns/services
	printf "%s\\t%s\\n" '"bind-nsupdate"' '"update_nsupdate.sh"' >> ${IPKG_INSTROOT}/etc/ddns/services_ipv6
	# on real system restart service if enabled
	[ -z "${IPKG_INSTROOT}" ] && {
		[ -x /etc/uci-defaults/ddns_nsupdate ] && \
			/etc/uci-defaults/ddns_nsupdate && \
				rm -f /etc/uci-defaults/ddns_nsupdate >/dev/null 2>&1
		/etc/init.d/ddns enabled && \
			/etc/init.d/ddns start >/dev/null 2>&1
	}
	exit 0	# suppress errors
}

pkg_prerm_${PN}-nsupdate() {
	#!/bin/sh
	IPKG_INSTROOT=$D
	# if NOT run buildroot then stop service
	[ -z "${IPKG_INSTROOT}" ] && /etc/init.d/ddns stop			>/dev/null 2>&1
	# remove services file entries
	/bin/sed -i '/bind-nsupdate/d' ${IPKG_INSTROOT}/etc/ddns/services	>/dev/null 2>&1
	/bin/sed -i '/bind-nsupdate/d' ${IPKG_INSTROOT}/etc/ddns/services_ipv6	>/dev/null 2>&1
	exit 0	# suppress errors
}

####

SUMMARY_${PN}-route53-v1 = "Amazon AWS Route 53 API v1"
RDEPENDS_${PN}-route53-v1 = "${PN} curl openssl openssl-bin"
FILES_${PN}-route53-v1 = "${libdir}/ddns/update_route53_v1.sh ${sysconfdir}/uci-defaults/ddns_route53_v1"

pkg_preinst_${PN}-route53-v1() {
	#!/bin/sh
	IPKG_INSTROOT=$D
	# if NOT run buildroot then stop service
	[ -z "${IPKG_INSTROOT}" ] && /etc/init.d/ddns stop >/dev/null 2>&1
	exit 0	# suppress errors
}

pkg_postinst_${PN}-route53-v1() {
	#!/bin/sh
	IPKG_INSTROOT=$D
	# remove old services file entries
	/bin/sed -i '/route53-v1/d' ${IPKG_INSTROOT}/etc/ddns/services		>/dev/null 2>&1
	/bin/sed -i '/route53-v1/d' ${IPKG_INSTROOT}/etc/ddns/services_ipv6	>/dev/null 2>&1
	# and create new
	printf "%s\\t%s\\n" '"route53-v1"' '"update_route53_v1.sh"' >> ${IPKG_INSTROOT}/etc/ddns/services
	printf "%s\\t%s\\n" '"route53-v1"' '"update_route53_v1.sh"' >> ${IPKG_INSTROOT}/etc/ddns/services_ipv6
	# on real system restart service if enabled
	[ -z "${IPKG_INSTROOT}" ] && {
		[ -x /etc/uci-defaults/ddns_route53_v1 ] && \
			/etc/uci-defaults/ddns_route53_v1 && \
				rm -f /etc/uci-defaults/ddns_route53_v1 >/dev/null 2>&1
		/etc/init.d/ddns enabled \
			&& /etc/init.d/ddns start >/dev/null 2>&1
	}
	exit 0	# suppress errors
}

pkg_prerm_${PN}-route53-v1() {
	#!/bin/sh
	IPKG_INSTROOT=$D
	# if NOT run buildroot then stop service
	[ -z "${IPKG_INSTROOT}" ] && /etc/init.d/ddns stop				>/dev/null 2>&1
	# remove services file entries
	/bin/sed -i 'route53-v1/d' ${IPKG_INSTROOT}/etc/ddns/services		>/dev/null 2>&1
	/bin/sed -i 'route53-v1/d' ${IPKG_INSTROOT}/etc/ddns/services_ipv6	>/dev/null 2>&1
	exit 0	# suppress errors
}

####

SUMMARY_${PN}-freedns-42-pl = "DDNS extension for FreeDNS.42.pl"
RDEPENDS_${PN}-freedns-42-pl = "${PN} curl"
FILES_${PN}-freedns-42-pl = "${libdir}/ddns/update_freedns_42_pl.sh ${sysconfdir}/uci-defaults/ddns_freedns_42_pl"

pkg_preinst_${PN}-freedns-42-pl() {
	#!/bin/sh
	IPKG_INSTROOT=$D
	# if NOT run buildroot then stop service
	[ -z "${IPKG_INSTROOT}" ] && /etc/init.d/ddns stop >/dev/null 2>&1
	exit 0	# suppress errors
}

pkg_postinst_${PN}-freedns-42-pl() {
	#!/bin/sh
	IPKG_INSTROOT=$D
	# remove old services file entries
	/bin/sed -i '/freedns\.42\.pl/d' ${IPKG_INSTROOT}/etc/ddns/services	>/dev/null 2>&1
	/bin/sed -i '/FreeDNS\.42\.pl/d' ${IPKG_INSTROOT}/etc/ddns/services	>/dev/null 2>&1
	# and create new
	printf "%s\\t%s\\n" '"freedns.42.pl"' '"update_freedns_42_pl.sh"' >> ${IPKG_INSTROOT}/etc/ddns/services
	# on real system restart service if enabled
	[ -z "${IPKG_INSTROOT}" ] && {
		[ -x /etc/uci-defaults/ddns_freedns_42_pl ] && \
			/etc/uci-defaults/ddns_freedns_42_pl && \
				rm -f /etc/uci-defaults/ddns_freedns_42_pl >/dev/null 2>&1
		/etc/init.d/ddns enabled && \
			/etc/init.d/ddns start >/dev/null 2>&1
	}
	exit 0	# suppress errors
}

pkg_prerm_${PN}-freedns-42-pl() {
	#!/bin/sh
	IPKG_INSTROOT=$D
	# if NOT run buildroot then stop service
	[ -z "${IPKG_INSTROOT}" ] && /etc/init.d/ddns stop		>/dev/null 2>&1
	# remove services file entries
	/bin/sed -i '/freedns\.42\.pl/d' ${IPKG_INSTROOT}/etc/ddns/services	>/dev/null 2>&1
	exit 0	# suppress errors
}
