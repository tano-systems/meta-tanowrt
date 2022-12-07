#
# SPDX-License-Identifier: MIT
#
# Dynamic DNS Client scripts (with IPv6 support)
#
# This file Copyright (c) 2018-2020 Tano Systems LLC. All rights reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
PR = "tano1"
PV = "2.8.2"

inherit allarch

SUMMARY = "Dynamic DNS Client scripts (with IPv6 support)"
SECTION = "net/misc"
LICENSE = "GPL-2.0 & MPL-2.0"
LIC_FILES_CHKSUM += "file://LICENSE.GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"
LIC_FILES_CHKSUM += "file://LICENSE.MPL-2.0;md5=815ca599c9df247a0c7f619bab123dad"

do_configure[noexec] = "1"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/files:"

S = "${WORKDIR}"
B = "${WORKDIR}/build"

SRC_URI = "\
	file://ddns.config \
	file://ddns.hotplug \
	file://ddns.init \
	file://ddns.sh \
	file://update_godaddy_com_v1.sh \
	file://update_freedns_42_pl.sh \
	file://update_cloudflare_com_v4.sh \
	file://update_nsupdate.sh \
	file://update_no-ip_com.sh \
	file://update_route53_v1.sh \
	file://update_cnkuai_cn.sh \
	file://update_digitalocean_com_v2.sh \
	file://dynamic_dns_updater.sh \
	file://dynamic_dns_lucihelper.sh \
	file://dynamic_dns_functions.sh \
	file://LICENSE.GPL-2.0 \
	file://LICENSE.MPL-2.0 \
"

SRC_URI += "\
	file://list \
	file://default/3322.org.json \
	file://default/able.or.kr.json \
	file://default/afraid.org-basicauth.json \
	file://default/afraid.org-keyauth.json \
	file://default/afraid.org-v2-basic.json \
	file://default/afraid.org-v2-token.json \
	file://default/all-inkl.com.json \
	file://default/bind-nsupdate.json \
	file://default/changeip.com.json \
	file://default/cloudflare.com-v4.json \
	file://default/cnkuai.cn.json \
	file://default/core-networks.de.json \
	file://default/ddns.com.br.json \
	file://default/ddnss.de.json \
	file://default/ddo.jp.json \
	file://default/desec.io.json \
	file://default/dhis.org.json \
	file://default/digitalocean.com-v2.json \
	file://default/dnsdynamic.org.json \
	file://default/dnsever.com.json \
	file://default/dnsexit.com.json \
	file://default/dnshome.de.json \
	file://default/dnsmadeeasy.com.json \
	file://default/dnsmax.com.json \
	file://default/dnsomatic.com.json \
	file://default/dnspark.com.json \
	file://default/do.de.json \
	file://default/domopoli.de.json \
	file://default/dtdns.com.json \
	file://default/duckdns.org.json \
	file://default/duiadns.net.json \
	file://default/dy.fi.json \
	file://default/dyn.com.json \
	file://default/dyndns.it.json \
	file://default/dyndns.org.json \
	file://default/dyndnss.net.json \
	file://default/dyns.net.json \
	file://default/dynsip.org.json \
	file://default/dynu.com.json \
	file://default/dynv6.com.json \
	file://default/easydns.com.json \
	file://default/editdns.net.json \
	file://default/freedns.42.pl.json \
	file://default/godaddy.com-v1.json \
	file://default/goip.de.json \
	file://default/google.com.json \
	file://default/he.net.json \
	file://default/inwx.de.json \
	file://default/joker.com.json \
	file://default/loopia.se.json \
	file://default/moniker.com.json \
	file://default/mydns.ip.json \
	file://default/myip.co.ua.json \
	file://default/myonlineportal.net.json \
	file://default/mythic-beasts.com.json \
	file://default/namecheap.com.json \
	file://default/nettica.com.json \
	file://default/no-ip.com.json \
	file://default/no-ip.pl.json \
	file://default/now-dns.com.json \
	file://default/nsupdate.info.json \
	file://default/nubem.com.json \
	file://default/opendns.com.json \
	file://default/oray.com.json \
	file://default/ovh.com.json \
	file://default/regfish.de.json \
	file://default/route53-v1.json \
	file://default/schokokeks.org.json \
	file://default/selfhost.de.json \
	file://default/sitelutions.com.json \
	file://default/spdyn.de.json \
	file://default/strato.com.json \
	file://default/system-ns.com.json \
	file://default/thatip.com.json \
	file://default/twodns.de.json \
	file://default/udmedia.de.json \
	file://default/variomedia.de.json \
	file://default/xlhost.de.json \
	file://default/zerigo.com.json \
	file://default/zoneedit.com.json \
	file://default/zzzz.io.json \
"

inherit tanowrt-services

TANOWRT_SERVICE_PACKAGES = "ddns-scripts"
TANOWRT_SERVICE_SCRIPTS_ddns-scripts += "ddns"
TANOWRT_SERVICE_STATE_ddns-scripts-ddns ?= "disabled"

DEPENDS += "curl openssl"
RDEPENDS:${PN} += "openssl-bin"
# bind-client

PACKAGES += "\
	${PN}-cloudflare \
	${PN}-godaddy \
	${PN}-digitalocean \
	${PN}-freedns \
	${PN}-noip \
	${PN}-nsupdate \
	${PN}-route53 \
	${PN}-cnkuai \
"

do_compile[noexec] = "1"

do_install() {
	install -d ${D}${sysconfdir}/hotplug.d/iface
	install -d ${D}${sysconfdir}/init.d
	install -d ${D}${sysconfdir}/config

	install -m 0755 ${WORKDIR}/ddns.hotplug ${D}${sysconfdir}/hotplug.d/iface/95-ddns
	install -m 0755 ${WORKDIR}/ddns.init ${D}${sysconfdir}/init.d/ddns
	install -m 0644 ${WORKDIR}/ddns.config ${D}${sysconfdir}/config/ddns

	install -d ${D}${sysconfdir}/ddns
	install -d ${D}${libdir}/ddns

	install -d ${D}${datadir}/ddns
	install -d ${D}${datadir}/ddns/default
	install -m 0644 ${WORKDIR}/list ${D}${datadir}/ddns/list
	install -m 0644 ${WORKDIR}/default/*.json ${D}${datadir}/ddns/default

	install -m 0755 ${WORKDIR}/dynamic_dns_functions.sh ${D}${libdir}/ddns/
	install -m 0755 ${WORKDIR}/dynamic_dns_updater.sh ${D}${libdir}/ddns/
	install -m 0755 ${WORKDIR}/dynamic_dns_lucihelper.sh ${D}${libdir}/ddns/

	install -m 0755 ${WORKDIR}/update_cloudflare_com_v4.sh ${D}${libdir}/ddns/
	install -m 0755 ${WORKDIR}/update_godaddy_com_v1.sh ${D}${libdir}/ddns/
	install -m 0755 ${WORKDIR}/update_no-ip_com.sh ${D}${libdir}/ddns/
	install -m 0755 ${WORKDIR}/update_nsupdate.sh ${D}${libdir}/ddns/
	install -m 0755 ${WORKDIR}/update_route53_v1.sh ${D}${libdir}/ddns/
	install -m 0755 ${WORKDIR}/update_freedns_42_pl.sh ${D}${libdir}/ddns/
	install -m 0755 ${WORKDIR}/update_digitalocean_com_v2.sh ${D}${libdir}/ddns/
	install -m 0755 ${WORKDIR}/update_cnkuai_cn.sh ${D}${libdir}/ddns/

	install -d ${D}${bindir}
	install -m 0755 ${WORKDIR}/ddns.sh ${D}${bindir}/ddns

	echo "${PV}-${PR}" > ${D}${datadir}/ddns/version
}

FILES:${PN} = "\
	${sysconfdir}/config \
	${sysconfdir}/ddns \
	${sysconfdir}/hotplug.d \
	${sysconfdir}/init.d \
	${libdir}/ddns/dynamic_dns_functions.sh \
	${libdir}/ddns/dynamic_dns_updater.sh \
	${libdir}/ddns/dynamic_dns_lucihelper.sh \
	${datadir}/ddns \
	${bindir} \
"

CONFFILES:${PN}:append = "\
	${sysconfdir}/config/ddns \
"

RRECOMMENDS:${PN} += "\
	${PN}-cloudflare \
	${PN}-freedns \
	${PN}-godaddy \
	${PN}-digitalocean \
	${PN}-noip \
	${PN}-route53 \
"

pkg_postinst:${PN}() {
	#!/bin/sh
	IPKG_INSTROOT=$D
	if [ -z "${IPKG_INSTROOT}" ]; then
		/etc/init.d/ddns enabled
		/etc/init.d/ddns start
	fi
	exit 0
}

pkg_prerm:${PN}() {
	#!/bin/sh
	IPKG_INSTROOT=$D
	if [ -z "${IPKG_INSTROOT}" ]; then
		/etc/init.d/ddns stop
		/etc/init.d/ddns disable
	fi
	exit 0
}

####

SUMMARY:${PN}-cloudflare = "Dynamic DNS Client scripts extension for CloudFlare.com API-v4"
RDEPENDS:${PN}-cloudflare = "${PN} curl"
FILES:${PN}-cloudflare = "\
	${libdir}/ddns/update_cloudflare_com_v4.sh \
	${datadir}/ddns/default/cloudflare.com-v4.json \
"

pkg_prerm:${PN}-cloudflare() {
	#!/bin/sh
	IPKG_INSTROOT=$D
	if [ -z "$${IPKG_INSTROOT}" ]; then
		/etc/init.d/ddns stop
	fi
	exit 0
}

####

SUMMARY:${PN}-freedns = "DDNS extension for FreeDNS.42.pl"
RDEPENDS:${PN}-freedns = "${PN} curl"
FILES:${PN}-freedns = "\
	${libdir}/ddns/update_freedns_42_pl.sh \
	${datadir}/ddns/default/freedns.42.pl.json \
"

pkg_prerm:${PN}-freedns() {
	#!/bin/sh
	IPKG_INSTROOT=$D
	if [ -z "$${IPKG_INSTROOT}" ]; then
		/etc/init.d/ddns stop
	fi
	exit 0
}

####

SUMMARY:${PN}-godaddy = "Dynamic DNS Client scripts extension for GoDaddy.com"
RDEPENDS:${PN}-godaddy = "${PN} curl"
FILES:${PN}-godaddy = "\
	${libdir}/ddns/update_godaddy_com_v1.sh \
	${datadir}/ddns/default/godaddy.com-v1.json \
"

pkg_prerm:${PN}-godaddy() {
	#!/bin/sh
	IPKG_INSTROOT=$D
	if [ -z "$${IPKG_INSTROOT}" ]; then
		/etc/init.d/ddns stop
	fi
	exit 0
}

####

SUMMARY:${PN}-digitalocean = "Dynamic DNS Client scripts extention for digitalocean.com API v2"
RDEPENDS:${PN}-digitalocean = "${PN} curl"
FILES:${PN}-digitalocean = "\
	${libdir}/ddns/update_digitalocean_com_v2.sh \
	${datadir}/ddns/default/digitalocean.com-v2.json \
"

pkg_prerm:${PN}-digitalocean() {
	#!/bin/sh
	IPKG_INSTROOT=$D
	if [ -z "$${IPKG_INSTROOT}" ]; then
		/etc/init.d/ddns stop
	fi
	exit 0
}

####

SUMMARY:${PN}-noip = "Dynamic DNS Client scripts extension for No-IP.com"
RDEPENDS:${PN}-noip = "${PN}"
FILES:${PN}-noip = "\
	${libdir}/ddns/update_no-ip_com.sh \
	${datadir}/ddns/default/no-ip.com.json \
"

pkg_prerm:${PN}-noip() {
	#!/bin/sh
	IPKG_INSTROOT=$D
	if [ -z "$${IPKG_INSTROOT}" ]; then
		/etc/init.d/ddns stop
	fi
	exit 0
}

####

SUMMARY:${PN}-nsupdate = "Dynamic DNS Client scripts extension for direct updates using Bind nsupdate"
RDEPENDS:${PN}-nsupdate = "${PN}"
# TODO: depends on bind-client"
FILES:${PN}-nsupdate = "\
	${libdir}/ddns/update_nsupdate.sh \
	${datadir}/ddns/default/bind-nsupdate.json \
"

pkg_prerm:${PN}-nsupdate() {
	#!/bin/sh
	IPKG_INSTROOT=$D
	if [ -z "$${IPKG_INSTROOT}" ]; then
		/etc/init.d/ddns stop
	fi
	exit 0
}

####

SUMMARY:${PN}-route53 = "Dynamic DNS Client scripts extension for Amazon AWS Route 53 API v1"
RDEPENDS:${PN}-route53 = "${PN} curl openssl openssl-bin"
FILES:${PN}-route53 = "\
	${libdir}/ddns/update_route53_v1.sh \
	${datadir}/ddns/default/route53-v1.json \
"

pkg_prerm:${PN}-route53() {
	#!/bin/sh
	IPKG_INSTROOT=$D
	if [ -z "$${IPKG_INSTROOT}" ]; then
		/etc/init.d/ddns stop
	fi
	exit 0
}

####

SUMMARY:${PN}-cnkuai = "Dynamic DNS Client scripts extension for CnKuai API"
RDEPENDS:${PN}-cnkuai = "${PN} curl"
# TODO: depends on giflib-utils
FILES:${PN}-cnkuai = "\
	${libdir}/ddns/update_cnkuai_cn.sh \
	${datadir}/ddns/default/cnkuai.cn.json \
"

pkg_prerm:${PN}-cnkuai() {
	#!/bin/sh
	IPKG_INSTROOT=$D
	if [ -z "$${IPKG_INSTROOT}" ]; then
		/etc/init.d/ddns stop
	fi
	exit 0
}

####
