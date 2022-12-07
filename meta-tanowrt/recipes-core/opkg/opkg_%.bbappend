#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2018-2019, 2021-2022 Anton Kikin <a.kikin@tano-systems.com>
#

PR:append = ".tano6"
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

OPKGLIBDIR = "${libdir}"

SRC_URI:append = "\
	file://opkg-key \
	file://opkg.conf \
	file://opkg-smime.conf \
	file://customfeeds.conf \
	file://distfeeds.conf \
	file://20_migrate-feeds \
"

SRC_URI += "\
	file://0001-Change-configuration-paths.patch \
	file://0002-Add-nocase-option-to-opkg.patch \
	file://0003-Set-PKG_UPGRADE-environment-variable.patch \
"

do_install:append () {
	install -dm 0755 ${D}/usr/lib/opkg
	install -dm 0755 ${D}/bin
	install -dm 0755 ${D}/usr/sbin
	install -dm 0755 ${D}${sysconfdir}/opkg
	install -dm 0755 ${D}${sysconfdir}/uci-defaults

	install -m 0644 ${WORKDIR}/customfeeds.conf ${D}${sysconfdir}/opkg/customfeeds.conf
	install -m 0644 ${WORKDIR}/distfeeds.conf ${D}${sysconfdir}/opkg/distfeeds.conf
	rm -f ${D}${sysconfdir}/opkg/opkg.conf
	install -m 0644 ${WORKDIR}/opkg.conf ${D}${sysconfdir}/opkg.conf

	install -m 0755 ${WORKDIR}/20_migrate-feeds ${D}${sysconfdir}/uci-defaults/20_migrate-feeds
	install -m 0755 ${WORKDIR}/opkg-key ${D}/usr/sbin/opkg-key
	ln -s /usr/bin/opkg ${D}/bin/opkg
}

FILES:${PN} += "/bin /usr/lib"
