# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Copyright (C) 2018 Daniel Dickinson <cshored@thecshore.com>
# Copyright (C) 2018-2019 Anton Kikin <a.kikin@tano-systems.com>

# Released under the MIT license (see COPYING.MIT for the terms)

INC_PR = "11"

PACKAGECONFIG[ubus] = ",,libubus"
EXTRA_OEMAKE += "\
    'COPTS+=${@bb.utils.contains('PACKAGECONFIG', 'ubus', '-DHAVE_UBUS', '', d)}' \
"

PACKAGECONFIG += " ubus "

# Patches
SRC_URI += "\
	file://0001-Impove-cache-behaviour-for-TCP-connections.patch \
	file://0002-Ensure-that-AD-bit-is-reset-on-answers-from-address-.patch \
	file://0003-Remove-ability-to-compile-without-IPv6-support.patch \
	file://0004-Don-t-forward-.bind-.server-queries-upstream.patch \
	file://0005-Fix-logging-in-cf5984367bc6a949e3803a576512c5a7bc48e.patch \
	file://0006-Fix-spurious-AD-flags-in-some-DNS-replies-from-local.patch \
	file://0007-Do-not-rely-on-dead-code-elimination-use-array-inste.patch \
	file://0008-Fix-Makefile-lines-generating-UBUS-linker-config.patch \
	file://0009-Revert-68f6312d4bae30b78daafcd6f51dc441b8685b1e.patch \
	file://0010-Remove-the-NO_FORK-compile-time-option-and-support-f.patch \
	file://0011-Free-config-file-values-on-parsing-errors.patch \
	file://0013-Treat-DS-and-DNSKEY-queries-being-forwarded-the-same.patch \
	file://0014-Fix-option-parsing-errors-introduced-in-59e470381f84.patch \
	file://0015-fix-ipv6-ipset-bug-in-master.patch \
	file://0016-build-failure-on-master-with-NO_DHCPv6-and-fix.patch \
	file://0017-Alter-DHCP-address-selection-after-DECLINE-in-consec.patch \
	file://0018-Tidy-all_addr-union-merge-log-and-rcode-fields.patch \
	file://0019-Tidy-address-union-handling-move-class-into-explicit.patch \
	file://0020-Futher-address-union-tidying.patch \
	file://0021-Remove-nested-struct-union-in-cache-records-and-all_.patch \
	file://0022-File-logic-bug-in-cache-marshalling-code.-Introduced.patch \
	file://0023-Fix-typo-in-ra-param-man-page-section.patch \
	file://0024-Cache-SRV-records.patch \
	file://0025-Fix-crash-freeing-negative-SRV-cache-entries.patch \
	file://0026-Check-for-not-DS-or-DNSKEY-in-is_outdated_cname_poin.patch \
	file://0027-Fix-e7bfd556c079c8b5e7425aed44abc35925b24043-to-actu.patch \
	file://0028-Tidy-cache_blockdata_free.patch \
	file://0029-Fix-removal-of-DHCP_CLIENT_MAC-options-from-DHCPv6-r.patch \
	file://0030-Fix-entries-in-etc-hosts-disabling-static-leases.patch \
	file://0031-Fix-missing-braces-in-8eac67c0a15b673c8d27002c248651.patch \
	file://0032-Change-read_leases-to-skip-invalid-entries.patch \
	file://0040-Fix-crash-when-negative-SRV-response-over-TCP-gets-s.patch \
	file://050-crypto-use-nettle-ecc_curve-access-functions.patch \
	file://110-ipset-remove-old-kernel-support.patch \
	file://230-fix-poll-h-include-warning-on-musl.patch \
"

# Files
SRC_URI += "\
	file://dhcp.conf \
	file://dnsmasq.conf \
	file://dhcpbogushostname.conf \
	file://dnsmasqsec.hotplug \
	file://dnsmasq.init \
	file://dnsmasq_acl.json \
	file://dhcp-script.sh \
	file://rfc6761.conf \
"

SRCREV_openwrt = "${OPENWRT_SRCREV}"

inherit openwrt openwrt-services useradd

OPENWRT_SERVICE_PACKAGES = "dnsmasq"
OPENWRT_SERVICE_SCRIPTS_dnsmasq += "dnsmasq"
OPENWRT_SERVICE_STATE_dnsmasq-dnsmasq ?= "enabled"

do_install_append() {
    install -d ${D}${sysconfdir}
    install -d ${D}${sysconfdir}/config
    install -d ${D}${sysconfdir}/init.d
    install -d ${D}${sysconfdir}/rc.d
    install -d ${D}${sbindir}

    install -m 0644 ${WORKDIR}/dnsmasq.conf ${D}${sysconfdir}/
    install -m 0644 ${WORKDIR}/dhcp.conf ${D}${sysconfdir}/config/dhcp
    install -m 0755 ${WORKDIR}/dnsmasq.init ${D}${sysconfdir}/init.d/dnsmasq

    install -d ${D}${sysconfdir}/hotplug.d/ntp
    install -d ${D}${sysconfdir}/hotplug.d/dhcp
    install -d ${D}${sysconfdir}/hotplug.d/neigh
    install -d ${D}${sysconfdir}/hotplug.d/tftp
    install -m 0644 ${WORKDIR}/dnsmasqsec.hotplug ${D}${sysconfdir}/hotplug.d/ntp/25-dnsmasqsec

    install -d ${D}/usr/lib/dnsmasq
    install -m 0755 ${WORKDIR}/dhcp-script.sh ${D}/usr/lib/dnsmasq/dhcp-script.sh

    install -d ${D}/usr/share/dnsmasq
    install -m 0644 ${WORKDIR}/rfc6761.conf ${D}/usr/share/dnsmasq/rfc6761.conf
    install -m 0644 ${WORKDIR}/dhcpbogushostname.conf ${D}/usr/share/dnsmasq/dhcpbogushostname.conf
    install -m 0644 ${B}/trust-anchors.conf ${D}/usr/share/dnsmasq

    install -d ${D}/usr/share/acl.d
    install -m 0644 ${WORKDIR}/dnsmasq_acl.json ${D}/usr/share/acl.d/dnsmasq_acl.json


    # dnsmasq installs in /usr/bin, openwrt looks for it in /usr/sbin
    ln -s ${bindir}/dnsmasq ${D}${sbindir}/dnsmasq

    # remove unused in OpenWrt files/folders
    rm -rf ${D}${bindir}/dhcp_release
    rm -rf ${D}${sysconfdir}/dnsmasq.d
    rm -rf ${D}${sysconfdir}/systemd
    rm -rf ${D}${sysconfdir}/rc.d
}

USERADD_PACKAGES = "${PN}"

USERADD_PARAM_${PN} = "--system -d /var/lib/dnsmasq --no-create-home \
  --shell /bin/false --user-group dnsmasq"

RDEPENDS_${PN} += "jsonpath"
RDEPENDS_${PN} += "kmod-ipt-ipset"
do_configure[depends] += "virtual/kernel:do_shared_workdir"

FILES_${PN}_append = "\
	/usr/share \
"

CONFFILES_${PN}_append = "\
	${sysconfdir}/config/dhcp \
	${sysconfdir}/dnsmasq.conf \
"
