#
# SPDX-License-Identifier: MIT
#
# This file Copyright (C) 2018-2021 Anton Kikin <a.kikin@tano-systems.com>
#
# OE package         OpenWrt packages
# --------------------------------------------------
# ppp                ppp chat
# ppp-oa             ppp-mod-pppoa
# ppp-oe             ppp-mod-pppoe pppoe-discovery
# ppp-radius         ppp-mod-radius
# ppp-pptp           ppp-mod-pptp
# ppp-winbind        -
# ppp-minconn        -
# ppp-password       ppp-mod-passwordfd
# ppp-l2tp           ppp-mod-pppol2tp
# ppp-tools          pppdump pppstats
#
PR:append = ".tano9"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI:remove = "file://cifdefroute.patch"
SRC_URI:remove = "file://makefile.patch"
SRC_URI:remove = "file://makefile-remove-hard-usr-reference.patch"
SRC_URI:remove = "file://copts.patch"

EXTRA_OEMAKE:append = " PRECOMPILED_FILTER=1 STAGING_DIR=${STAGING_DIR_TARGET} OE_LIBDIR=${libdir} OE_BASE_LIBDIR=${base_libdir}"

SRC_URI:append = "\
	file://010-use_target_for_configure.patch \
	file://100-debian_ip-ip_option.patch \
	file://101-debian_close_dev_ppp.patch \
	file://103-debian_fix_link_pidfile.patch \
	file://105-debian_demand.patch \
	file://106-debian_stripMSdomain.patch \
	file://107-debian_pppoatm_wildcard.patch \
	file://110-debian_defaultroute.patch \
	file://120-debian_ipv6_updown_option.patch \
	file://121-debian_adaptive_lcp_echo.patch \
	file://133-fix_sha1_include.patch \
	file://140-pppoe_compile_fix.patch \
	file://200-makefile.patch \
	file://201-mppe_mppc_1.1.patch \
	file://202-no_strip.patch \
	file://203-opt_flags.patch \
	file://204-radius_config.patch \
	file://205-no_exponential_timeout.patch \
	file://207-lcp_mtu_max.patch \
	file://208-fix_status_code.patch \
	file://300-filter-pcap-includes-lib.patch \
	file://310-precompile_filter.patch \
	file://321-multilink_support_custom_iface_names.patch \
	file://330-retain_foreign_default_routes.patch \
	file://340-populate_default_gateway.patch \
	file://400-simplify_kernel_checks.patch \
	file://401-no_record_file.patch \
	file://403-no_wtmp.patch \
	file://404-remove_obsolete_protocol_names.patch \
	file://405-no_multilink_option.patch \
	file://500-add-pptp-plugin.patch \
	file://510-pptp_compile_fix.patch \
	file://511-pptp_cflags.patch \
"

SRC_URI:append = "\
	file://0001-pppd-do-not-hardcode-lib-directory.patch \
"

SRC_URI:append = "\
	file://options.pptp \
	file://radius.conf \
	file://servers \
	file://dictionary \
	file://dictionary.asnet \
	file://dictionary.microsoft \
	file://ppp.sh \
	file://ppp-up \
	file://ppp6-up \
	file://ppp-down \
	file://filter \
	file://options \
	file://chap-secrets \
"

do_install:append() {
	# Remove unused in OpenWrt files
	rm -rf ${D}${bindir}

	rm -rf ${D}${sysconfdir}/init.d
	rm -rf ${D}${sysconfdir}/ppp
	rm -rf ${D}${sysconfdir}/chatscripts
	rm -rf ${D}${systemd_unitdir}

	install -dm 0755 ${D}${sysconfdir}/ppp

	# pptp
	install -m 0644 ${WORKDIR}/options.pptp ${D}${sysconfdir}/ppp/options.pptp

	# radius
	install -dm 0755 ${D}${sysconfdir}/ppp/radius
	install -m 0644 ${WORKDIR}/radius.conf ${D}${sysconfdir}/ppp/radius.conf
	install -m 0644 ${WORKDIR}/servers ${D}${sysconfdir}/ppp/radius/servers
	install -m 0644 ${WORKDIR}/dictionary ${D}${sysconfdir}/ppp/radius/dictionary
	install -m 0644 ${WORKDIR}/dictionary.asnet ${D}${sysconfdir}/ppp/radius/dictionary.asnet
	install -m 0644 ${WORKDIR}/dictionary.microsoft ${D}${sysconfdir}/ppp/radius/dictionary.microsoft

	# ppp
	install -m 0644 ${WORKDIR}/chap-secrets ${D}${sysconfdir}/ppp/chap-secrets
	install -m 0644 ${WORKDIR}/filter ${D}${sysconfdir}/ppp/filter
	install -m 0644 ${WORKDIR}/options ${D}${sysconfdir}/ppp/options
#	ln -sf /tmp/resolv.conf.ppp ${D}{$sysconfdir}/ppp/resolv.conf
	install -dm 0755 ${D}/lib/netifd/proto
	install -m 0755 ${WORKDIR}/ppp.sh ${D}/lib/netifd/proto/ppp.sh
	install -m 0755 ${WORKDIR}/ppp-up ${D}/lib/netifd/ppp-up
	install -m 0755 ${WORKDIR}/ppp-down ${D}/lib/netifd/ppp-down
	install -m 0755 ${WORKDIR}/ppp6-up ${D}/lib/netifd/ppp6-up

	sed -i -e "s:/usr/lib/pppd:${libdir}/pppd:g" \
	          ${D}/lib/netifd/proto/ppp.sh
}

PACKAGES += "${PN}-pptp"

FILES:${PN} = "\
	${sysconfdir}/ppp/chap-secrets \
	${sysconfdir}/ppp/filter \
	${sysconfdir}/ppp/options \
	${sysconfdir}/ppp/resolv.conf \
	/lib/netifd/* \
	${sbindir}/chat \
	${sbindir}/pppd \
	/usr/sbin/ \
	/usr/share/* \
	/usr/include/* \
"

FILES:${PN}-pptp = "${libdir}/pppd/${PV}/pptp.so ${sysconfdir}/ppp/options.pptp"
SUMMARY:${PN}-pptp = "Plugin for PPP for PPtP support"

FILES:${PN}-radius += "\
	${sysconfdir}/ppp/radius.conf \
	${sysconfdir}/ppp/radius \
"

CONFFILES:${PN}-radius = "\
	${sysconfdir}/ppp/radius.conf \
	${sysconfdir}/ppp/radius/* \
"

do_configure[depends] += "virtual/kernel:do_shared_workdir"

inherit kmod/ppp

# ${PN}-pptp
inherit kmod/pptp
inherit kmod/mppe

# ${PN}-oa ${PN}-oe
inherit kmod/pppoe

# ${PN}-l2tp
inherit kmod/pppol2tp

RDEPENDS:${PN}-pptp += "${PN} resolveip"
RDEPENDS:${PN}-oa += "${PN} linux-atm"
RDEPENDS:${PN}-oe += "${PN}"
RDEPENDS:${PN}-radius += "${PN}"
RDEPENDS:${PN}-winbind += "${PN}"
RDEPENDS:${PN}-minconn += "${PN}"
RDEPENDS:${PN}-password += "${PN}"
RDEPENDS:${PN}-l2tp += "${PN}"
RDEPENDS:${PN}-tools += "${PN}"

CONFFILES:${PN} = "\
	${sysconfdir}/ppp/chap-secrets \
	${sysconfdir}/ppp/filter \
	${sysconfdir}/ppp/options \
"

RSUGGESTS:${PN} = "${PN}-tools ${PN}-oa ${PN}-oe ${PN}-radius ${PN}-winbind ${PN}-minconn ${PN}-password ${PN}-l2tp"
RRECOMMENDS:${PN} = "${PN}-tools ${PN}-pptp ${PN}-oe"
