# SPDX-License-Identifier: MIT
# This file Copyright (C) 2019 Anton Kikin <a.kikin@tano-systems.com>

SUMMARY = "/etc/urandom.seed handling for OpenWrt"
SECTION = "base"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=801f80980d171dd6425610833a22dbe6"
PR = "tano0"

S = "${WORKDIR}"

RDEPENDS:${PN} += "getrandom"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI += "\
	file://rootfs/etc/init.d/urandom_seed \
	file://rootfs/lib/preinit/81_urandom_seed \
	file://rootfs/sbin/urandom_seed \
	file://LICENSE \
"

inherit tanowrt-services

TANOWRT_SERVICE_PACKAGES = "urandom-seed"
TANOWRT_SERVICE_SCRIPTS_urandom-seed += "urandom_seed"
TANOWRT_SERVICE_STATE_urandom-seed-urandom_seed ?= "enabled"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

FILES:${PN} += "${sysconfdir} ${nonarch_base_libdir} ${base_sbindir}"

do_install() {
	install -d -m 0755 ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/rootfs/etc/init.d/urandom_seed ${D}${sysconfdir}/init.d/urandom_seed

	install -d -m 0755 ${D}${TANOWRT_PATH_PREINIT}
	install -m 0644 ${WORKDIR}/rootfs/lib/preinit/81_urandom_seed ${D}${TANOWRT_PATH_PREINIT}/81_urandom_seed

	install -d -m 0755 ${D}${base_sbindir}
	install -m 0755 ${WORKDIR}/rootfs/sbin/urandom_seed ${D}${base_sbindir}/urandom_seed
}
