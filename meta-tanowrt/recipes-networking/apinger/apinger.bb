#
# SPDX-License-Identifier: MIT
#
# Recipe for Alarm Pinger (apinger) tool.
#
# Copyright (c) 2022 Tano Systems LLC. All rights reserved.
# Aleksandr Kobelev <kobelev.an8@mail.ru>
# Anton Kikin <a.kikin@tano-systems.com>
#
PV = "0.4.1+git${SRCPV}"
PR = "tano0"

SUMMARY = "apinger tool"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://src/main.c;beginline=1;endline=18;md5=9ba91fa94d7e6cc5ffc7311a912dcefa"

SRCREV = "78eb328721ba1a10571c19df95acddcb5f0c17c8"
GIT_BRANCH   = "master"
GIT_PROTOCOL = "https"
SRC_URI = "git://github.com/Jajcus/apinger.git;protocol=${GIT_PROTOCOL};branch=${GIT_BRANCH}"

S = "${WORKDIR}/git"

DEPENDS += "byacc-native bison-native rrdtool"

# Files
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/files:"
SRC_URI:append = "\
	file://apinger-hotplug \
	file://apinger.config \
	file://apinger.init \
	file://apinger.rpc \
	file://graphs.sh \
	file://iface.hotplug \
	file://user.hotplug"

# Patches
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/patches:"
SRC_URI:append = "\
	file://002-run_as_user.patch \
	file://003-no_docs.patch \
	file://010-poll.patch \
	file://020-gcc10.patch \
	file://030-apinger-no_exit.patch \
	file://040-srcip.patch \
	file://050-statusformat.patch \
	file://060-format-alarm-list.patch \
	file://070-rpl-malloc.patch"

inherit autotools

PARALLEL_MAKE = ""

do_configure:prepend() {
	rm -f ${S}/autogen.sh
}

do_install() {
	install -d ${D}${sbindir}/
	install -m 0755 ${B}/src/apinger ${D}${sbindir}/apinger
	install -d ${D}${sysconfdir}/
	install -m 0644 ${S}/src/apinger.conf ${D}${sysconfdir}/apinger.conf
	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/apinger.init ${D}${sysconfdir}/init.d/apinger
	install -d ${D}${sysconfdir}/config
	install -m 0644 ${WORKDIR}/apinger.config ${D}${sysconfdir}/config/apinger
	install -d ${D}${libexecdir}
	install -m 0755 ${WORKDIR}/apinger-hotplug ${D}${libexecdir}/apinger-hotplug
	install -d ${D}${sysconfdir}/hotplug.d/apinger
	install -m 0644 ${WORKDIR}/user.hotplug ${D}${sysconfdir}/hotplug.d/apinger/01-user
	install -d ${D}${sysconfdir}/hotplug.d/iface
	install -m 0644 ${WORKDIR}/iface.hotplug ${D}${sysconfdir}/hotplug.d/iface/25-apinger
	install -d ${D}${libexecdir}/rpcd
	install -m 0755 ${WORKDIR}/apinger.rpc ${D}${libexecdir}/rpcd/apinger
	install -d ${D}${libexecdir}/apinger/rpc
	install -m 0755 ${WORKDIR}/graphs.sh ${D}${libexecdir}/apinger/rpc
}

CONFFILES:${PN} += "\
	${sysconfdir}/config/apinger \
	${sysconfdir}/apinger.user \
"

inherit tanowrt-services

TANOWRT_SERVICE_PACKAGES = "apinger"
TANOWRT_SERVICE_SCRIPTS_apinger += "apinger"
TANOWRT_SERVICE_STATE_apinger-apinger ?= "disabled"
