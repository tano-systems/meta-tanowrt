#
# Dynamic DNS Client scripts (with IPv6 support)
#
# This file Copyright (c) 2018, Tano Systems. All Rights Reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
PR = "tano1"

#DEPENDS += "gzip-native"

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
	file://ddns.config \
	file://ddns.hotplug \
	file://ddns.init \
	file://update_godaddy_com_v1.sh \
	file://ddns.defaults \
	file://dynamic_dns_updater.sh \
	file://services \
	file://update_cloudflare_com_v4.sh \
	file://dynamic_dns_lucihelper.sh \
	file://dynamic_dns_functions.sh \
	file://update_nsupdate.sh \
	file://services_ipv6 \
	file://update_cloudflare_com_v1.sh \
	file://public_suffix_list.dat \
	file://update_no-ip_com.sh \
"

inherit openwrt-services

OPENWRT_SERVICE_PACKAGES = "${PN}"
OPENWRT_SERVICE_SCRIPTS_${PN} = "ddns"
OPENWRT_SERVICE_STATE_${PN}-ddns = "disabled"

inherit pkgconfig

PACKAGECONFIG ??= "cloudflare_v4 godaddy_com_v1 no_ip_com"

# Dynamic DNS Client scripts extension for CloudFlare.com API-v1 (deprecated)
PACKAGECONFIG[cloudflare_v1] = ",,"

# Dynamic DNS Client scripts extension for CloudFlare.com API-v4 (require/install cURL)
PACKAGECONFIG[cloudflare_v4] = ",,curl"

# Dynamic DNS Client scripts extension for GoDaddy.com (require/install cURL)
PACKAGECONFIG[godaddy_com_v1] = ",,curl"

# Dynamic DNS Client scripts extension for No-IP.com
PACKAGECONFIG[no_ip_com] = ",,"

# Dynamic DNS Client scripts extension for direct updates using Bind nsupdate
PACKAGECONFIG[nsupdate] = ",,bind-client"

do_compile() {
	cp ${S}/services* ${B}/
	cp ${S}/dynamic_dns_*.sh ${B}/
	cp ${S}/update_*.sh ${B}/
	cp ${S}/ddns.* ${B}/

	# ensure that VERSION inside dynamic_dns_functions.sh reflect package version
	sed -i '/^VERSION=*/s/.*/VERSION="${PV}-${PR}"/' ${B}/dynamic_dns_functions.sh

	# compress public_suffix_list.dat
	gzip -f9 -c ${S}/public_suffix_list.dat > ${B}/public_suffix_list.dat.gz

	chmod 0644 ${B}/services*
	chmod 0755 ${B}/*.sh
}

FILES_${PN} += "${libdir}"

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

	cp ${B}/services* ${D}${sysconfdir}/ddns/
	cp ${B}/dynamic_dns_*.sh ${D}${libdir}/ddns/

	if [ "${@bb.utils.contains('PACKAGECONFIG', 'cloudflare_v1', '1', '0', d)}" = "1" ]; then
		install -d ${D}/usr/share
		install -d ${D}${libdir}/ddns

		install -m 0644 ${B}/public_suffix_list.data.gz ${D}/usr/share/public_suffix_list.data.gz
		install -m 0755 ${B}/update_cloudflare_com_v1.sh ${D}${libdir}/ddns/update_cloudflare_com_v1.sh
	fi

	if [ "${@bb.utils.contains('PACKAGECONFIG', 'cloudflare_v4', '1', '0', d)}" = "1" ]; then
		install -d ${D}${libdir}/ddns
		install -m 0755 ${B}/update_cloudflare_com_v4.sh ${D}${libdir}/ddns/update_cloudflare_com_v4.sh
	fi

	if [ "${@bb.utils.contains('PACKAGECONFIG', 'godaddy_com_v1', '1', '0', d)}" = "1" ]; then
		install -d ${D}${libdir}/ddns
		install -m 0755 ${B}/update_godaddy_com_v1.sh ${D}${libdir}/ddns/update_godaddy_com_v1.sh
	fi

	if [ "${@bb.utils.contains('PACKAGECONFIG', 'no_ip_com', '1', '0', d)}" = "1" ]; then
		install -d ${D}${libdir}/ddns
		install -m 0755 ${B}/update_no-ip_com.sh ${D}${libdir}/ddns/update_no-ip_com.sh
	fi

	if [ "${@bb.utils.contains('PACKAGECONFIG', 'nsupdate', '1', '0', d)}" = "1" ]; then
		install -d ${D}${libdir}/ddns
		install -m 0755 ${B}/update_nsupdate.sh ${D}${libdir}/ddns/update_nsupdate.sh
	fi
}
