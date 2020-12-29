#
# SPDX-License-Identifier: MIT
#
# This file Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>
#
# A set of scripts that abstract QoS configuration into a simple 
# configuration file supporting stanzas that specify any number of QoS 
# entries.
#
PV = "1.3.1"
PR = "tano3"

DESCRIPTION = "QoS scripts"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"
SECTION = "net"

RDEPENDS_${PN} += "\
	iptables \
	iproute2-tc \
"

inherit kmod/sched-core
inherit kmod/sched-connmark
inherit kmod/ifb
inherit kmod/ipt-ipopt
inherit kmod/ipt-conntrack-extra

do_configure[depends] += "virtual/kernel:do_shared_workdir"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI = "\
	file://qos.init \
	file://qos.hotplug \
	file://qos.config \
	file://qos-start \
	file://qos-stop \
	file://qos-stat \
	file://tcrules.awk \
	file://generate.sh \
"

inherit tanowrt-services

TANOWRT_SERVICE_PACKAGES = "qos-scripts"
TANOWRT_SERVICE_SCRIPTS_qos-scripts += "qos"
TANOWRT_SERVICE_STATE_qos-scripts-qos ?= "enabled"

FILES_${PN} += "${libdir}/"

S = "${WORKDIR}"

do_compile[noexec] = "1"
do_configure[noexec] = "1"

do_install() {
	install -dm 0755 ${D}${sysconfdir}/config
	install -m 0644 ${WORKDIR}/qos.config ${D}${sysconfdir}/config/qos

	install -dm 0755 ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/qos.init ${D}${sysconfdir}/init.d/qos

	install -dm 0755 ${D}${sysconfdir}/hotplug.d/iface
	install -m 0755 ${WORKDIR}/qos.hotplug ${D}${sysconfdir}/hotplug.d/iface/10-qos

	install -dm 0755 ${D}${bindir}
	install -m 0755 ${WORKDIR}/qos-start ${D}${bindir}/qos-start
	install -m 0755 ${WORKDIR}/qos-stop ${D}${bindir}/qos-stop
	install -m 0755 ${WORKDIR}/qos-stat ${D}${bindir}/qos-stat

	install -dm 0755 ${D}${libdir}/qos
	install -m 0755 ${WORKDIR}/generate.sh ${D}${libdir}/qos/generate.sh
	install -m 0755 ${WORKDIR}/tcrules.awk ${D}${libdir}/qos/tcrules.awk
}

CONFFILES_${PN}_append = "\
	${sysconfdir}/config/qos \
"
