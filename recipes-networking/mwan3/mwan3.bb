#
# This file Copyright (C) 2018-2019 Anton Kikin <a.kikin@tano-systems.com>
#
# Hotplug script which makes configuration of multiple WAN interfaces simple
# and manageable. With loadbalancing/failover support for up to 250 wan
# interfaces, connection tracking and an easy to manage traffic ruleset.
#
PV = "2.7.10"
PR = "tano0"

DESCRIPTION = "Multiwan hotplug script with connection tracking support"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"
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
	file://mwan3.user \
	file://mwan3.config \
	file://mwan3.init \
	file://mwan3.15-hotplug \
	file://mwan3.16-hotplug \
	file://mwan3-user.16-hotplug \
	file://common.sh \
	file://mwan3.sh \
	file://mwan3.rpcd \
	file://mwan3 \
	file://mwan3track \
	file://mwan3rtmon \
"

inherit openwrt-services

OPENWRT_SERVICE_PACKAGES = "mwan3"
OPENWRT_SERVICE_SCRIPTS_mwan3 += "mwan3"
OPENWRT_SERVICE_STATE_mwan3-mwan3 ?= "disabled"

FILES_${PN} += "${libdir}/ ${base_libdir}/"

S = "${WORKDIR}"

do_compile[noexec] = "1"
do_configure[noexec] = "1"

do_install() {
	install -dm 0755 ${D}${sysconfdir}
	install -m 0644 ${WORKDIR}/mwan3.user ${D}${sysconfdir}/mwan3.user

	install -dm 0755 ${D}${sysconfdir}/config
	install -m 0644 ${WORKDIR}/mwan3.config ${D}${sysconfdir}/config/mwan3

	install -dm 0755 ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/mwan3.init ${D}${sysconfdir}/init.d/mwan3

	install -dm 0755 ${D}${sysconfdir}/hotplug.d/iface
	install -m 0755 ${WORKDIR}/mwan3.15-hotplug ${D}${sysconfdir}/hotplug.d/iface/15-mwan3
	install -m 0755 ${WORKDIR}/mwan3.16-hotplug ${D}${sysconfdir}/hotplug.d/iface/16-mwan3
	install -m 0755 ${WORKDIR}/mwan3-user.16-hotplug ${D}${sysconfdir}/hotplug.d/iface/16-mwan3-user

	install -dm 0755 ${D}${base_libdir}/mwan3
	install -m 0755 ${WORKDIR}/common.sh ${D}${base_libdir}/mwan3/common.sh
	install -m 0755 ${WORKDIR}/mwan3.sh ${D}${base_libdir}/mwan3/mwan3.sh

	install -dm 0755 ${D}/usr/libexec/rpcd
	install -m 0755 ${WORKDIR}/mwan3.rpcd ${D}/usr/libexec/rpcd/mwan3

	install -dm 0755 ${D}${sbindir}
	install -m 0755 ${WORKDIR}/mwan3 ${D}${sbindir}/mwan3
	install -m 0755 ${WORKDIR}/mwan3track ${D}${sbindir}/mwan3track
	install -m 0755 ${WORKDIR}/mwan3rtmon ${D}${sbindir}/mwan3rtmon
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
