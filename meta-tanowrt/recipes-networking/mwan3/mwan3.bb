#
# SPDX-License-Identifier: MIT
#
# This file Copyright (C) 2018-2020 Anton Kikin <a.kikin@tano-systems.com>
#
# Hotplug script which makes configuration of multiple WAN interfaces simple
# and manageable. With loadbalancing/failover support for up to 250 wan
# interfaces, connection tracking and an easy to manage traffic ruleset.
#
PV = "2.10.6"
PR = "tano1"

DESCRIPTION = "Multiwan hotplug script with connection tracking support"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=801f80980d171dd6425610833a22dbe6"
SECTION = "net"

DEPENDS += "iptables ipset"

RDEPENDS_${PN} += "\
	rpcd \
	jshn \
	ipset \
	iptables \
"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI = "\
	file://etc/mwan3.user \
	file://etc/config/mwan3 \
	file://etc/init.d/mwan3 \
	file://etc/hotplug.d/iface/15-mwan3 \
	file://etc/hotplug.d/iface/16-mwan3-user \
	file://etc/uci-defaults/mwan3-migrate-flush_conntrack \
	file://lib/mwan3/common.sh \
	file://lib/mwan3/mwan3.sh \
	file://usr/libexec/rpcd/mwan3 \
	file://usr/sbin/mwan3 \
	file://usr/sbin/mwan3track \
	file://usr/sbin/mwan3rtmon \
	file://src/sockopt_wrap.c \
	file://src/LICENSE \
"

inherit tanowrt-services

TANOWRT_SERVICE_PACKAGES = "mwan3"
TANOWRT_SERVICE_SCRIPTS_mwan3 += "mwan3"
TANOWRT_SERVICE_STATE_mwan3-mwan3 ?= "disabled"

FILES_${PN} += "${libdir}/ ${base_libdir}/ ${nonarch_base_libdir}/"

S = "${WORKDIR}/src"

CFLAGS += "-fPIC -shared ${@bb.utils.contains('DISTRO_FEATURES', 'ipv6', '-DCONFIG_IPv6', '', d)}"
LDFLAGS += "-ldl"

do_compile() {
	${CC} ${CFLAGS} ${LDFLAGS} ${S}/sockopt_wrap.c -o ${B}/libwrap_mwan3_sockopt.so.1.0
}

do_install() {
	install -dm 0755 ${D}${sysconfdir}
	install -m 0644 ${WORKDIR}/etc/mwan3.user ${D}${sysconfdir}/

	install -dm 0755 ${D}${sysconfdir}/config
	install -m 0644 ${WORKDIR}/etc/config/mwan3 ${D}${sysconfdir}/config/

	install -dm 0755 ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/etc/init.d/mwan3 ${D}${sysconfdir}/init.d/

	install -dm 0755 ${D}${sysconfdir}/hotplug.d/iface
	install -m 0755 ${WORKDIR}/etc/hotplug.d/iface/15-mwan3 ${D}${sysconfdir}/hotplug.d/iface/
	install -m 0755 ${WORKDIR}/etc/hotplug.d/iface/16-mwan3-user ${D}${sysconfdir}/hotplug.d/iface/

	install -dm 0755 ${D}${nonarch_base_libdir}/mwan3
	install -m 0755 ${WORKDIR}/lib/mwan3/common.sh ${D}${nonarch_base_libdir}/mwan3/
	install -m 0755 ${WORKDIR}/lib/mwan3/mwan3.sh ${D}${nonarch_base_libdir}/mwan3/

	install -dm 0755 ${D}/usr/libexec/rpcd
	install -m 0755 ${WORKDIR}/usr/libexec/rpcd/mwan3 ${D}/usr/libexec/rpcd/

	install -dm 0755 ${D}${sbindir}
	install -m 0755 ${WORKDIR}/usr/sbin/mwan3 ${D}${sbindir}/
	install -m 0755 ${WORKDIR}/usr/sbin/mwan3track ${D}${sbindir}/
	install -m 0755 ${WORKDIR}/usr/sbin/mwan3rtmon ${D}${sbindir}/

	install -dm 0755 ${D}${sysconfdir}/uci-defaults
	install -m 0755 ${WORKDIR}/etc/uci-defaults/mwan3-migrate-flush_conntrack ${D}${sysconfdir}/uci-defaults/

	install -dm 0755 ${D}${base_libdir}/mwan3
	install -m 0755 ${B}/libwrap_mwan3_sockopt.so.1.0 ${D}${base_libdir}/mwan3/
}

pkg_postinst_${PN}() {
#!/bin/sh
IPKG_INSTROOT=$D
if [ -z "${IPKG_INSTROOT}" ]; then
	/etc/init.d/rpcd restart
fi
}

pkg_postrm_${PN}() {
#!/bin/sh
IPKG_INSTROOT=$D
if [ -z "${IPKG_INSTROOT}" ]; then
	/etc/init.d/rpcd restart
fi
}

CONFFILES_${PN}_append = "\
	${sysconfdir}/config/mwan3 \
	${sysconfdir}/mwan3.user \
"
