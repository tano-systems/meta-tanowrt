#
# This file (C) Copyright 2020, Tano Systems LLC
# Author: Anton Kikin <a.kikin@tano-systems.com>
#
# Recipe based on original recipe from meta-swi layer
# (https://github.com/legatoproject/meta-swi.git) and and uses some parts from it
#
# Original recipe: meta-swi/common/recipes-core/initscripts/initscripts_1.0.bbappend
#
SUMMARY = "Init scripts for Sierra Wireless devices"
DESCRIPTION = "Initscripts provide the basic system startup initialization scripts for the Sierra Wireless systems."
SECTION = "base"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"
PR = "swi0"

PACKAGE_ARCH = "${MACHINE_ARCH}"

INHIBIT_DEFAULT_DEPS = "1"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

SRC_URI = "\
	file://81_swi_00_alignment \
	file://81_swi_01_confighw \
	file://81_swi_05_find_partitions \
	file://81_swi_06_firmware_links \
	file://81_swi_10_mount_early \
	file://swi-load-modem.init \
	file://swi-ipa-fws.init \
	file://swi-mssboot.init \
	file://swi-power-config.init \
	file://prepro.awk \
	file://swi-run.env.in \
	file://swi-restart_at_uart \
	file://swi-restart_nmea \
	file://swi-restart_swi_apps \
"

S = "${WORKDIR}"

inherit tanowrt-services

TANOWRT_SERVICE_PACKAGES = "swi-initscripts"
TANOWRT_SERVICE_SCRIPTS_swi-initscripts ?= "\
	swi-01-load-modem \
	swi-04-ipa-fws \
	swi-13-mssboot \
	swi-22-power-config \
"

TANOWRT_SERVICE_STATE_swi-initscripts-swi-01-load-modem ?= "enabled"
TANOWRT_SERVICE_STATE_swi-initscripts-swi-04-ipa-fws ?= "enabled"
TANOWRT_SERVICE_STATE_swi-initscripts-swi-13-mssboot ?= "enabled"
TANOWRT_SERVICE_STATE_swi-initscripts-swi-22-power-config ?= "enabled"

TMPL_FLAGS ??= ""

#
# Preprocess *.in files with @if directives.
#
# Default flags can be extended through TMPL_FLAGS
#
process_templates() {
	set -x
	chmod a+x ${WORKDIR}/prepro.awk
	for file in ${WORKDIR}/*.in ; do
		${WORKDIR}/prepro.awk -v CPPFLAGS="${TMPL_FLAGS} -D${MACHINE}" $file > ${file%.in}
	done
}

do_install() {
	process_templates

	install -d ${D}${base_libdir}/preinit
	install -m 0644 ${WORKDIR}/81_swi_00_alignment ${D}${base_libdir}/preinit/
	install -m 0644 ${WORKDIR}/81_swi_01_confighw ${D}${base_libdir}/preinit/
	install -m 0644 ${WORKDIR}/81_swi_05_find_partitions ${D}${base_libdir}/preinit/
	install -m 0644 ${WORKDIR}/81_swi_06_firmware_links ${D}${base_libdir}/preinit/
	install -m 0644 ${WORKDIR}/81_swi_10_mount_early ${D}${base_libdir}/preinit/

	install -d ${D}${sysconfdir}
	install -m 0644 ${WORKDIR}/swi-run.env ${D}${sysconfdir}/run.env

	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/swi-load-modem.init ${D}${sysconfdir}/init.d/swi-01-load-modem
	install -m 0755 ${WORKDIR}/swi-ipa-fws.init ${D}${sysconfdir}/init.d/swi-04-ipa-fws
	install -m 0755 ${WORKDIR}/swi-mssboot.init ${D}${sysconfdir}/init.d/swi-13-mssboot
	install -m 0755 ${WORKDIR}/swi-power-config.init ${D}${sysconfdir}/init.d/swi-22-power-config

	install -m 0755 ${WORKDIR}/swi-restart_nmea -D ${D}${sbindir}/swi-restart_nmea
	install -m 0755 ${WORKDIR}/swi-restart_at_uart -D ${D}${sbindir}/swi-restart_at_uart
	install -m 0755 ${WORKDIR}/swi-restart_swi_apps -D ${D}${sbindir}/swi-restart_swi_apps
}

FILES_${PN} = "${sysconfdir} ${base_libdir}/preinit ${sbindir}"
