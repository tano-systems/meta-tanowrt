#
# CLI utility to build and manage a PKI CA
# OpenWrt recipe
#
# Copyright (c) 2018, Tano Systems. All Rights Reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
PR = "tano0"

SUMMARY = "CLI utility to build and manage a PKI CA"
HOMEPAGE = "http://openvpn.net"
SECTION = "net"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=bc50580af64334feaf532250b8d12631"
DEPENDS = "openssl"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI = "\
	https://github.com/OpenVPN/easy-rsa/releases/download/${PV}/EasyRSA-${PV}.zip \
	file://openvpn-easy-rsa.upgrade \
"

SRC_URI[md5sum] = "7e465cec5f143990b9b40c2d6735be31"
SRC_URI[sha256sum] = "4ac05aac894a71490e57def4a476915440e1c557393d31c440c3ae5130e9c624"

do_configure[noexec] = "1"
do_compile[noexec]   = "1"

S = "${WORKDIR}/EasyRSA-${PV}"

FILES_${PN} = "\
	/usr/sbin/* \
	/etc/easy-rsa/* \
	/lib/upgrade/keep.d/* \
"

CONFFILES_${PN} = " \
	${sysconfdir}/easy-rsa/vars \
	${sysconfdir}/easy-rsa/openssl-1.0.cnf \
"

do_install_append() {
	install -d ${D}/usr/sbin
	install -m 0755 ${S}/easyrsa ${D}/usr/sbin

	install -d ${D}${sysconfdir}/easy-rsa

	install -m 0644 ${S}/openssl-1.0.cnf ${D}${sysconfdir}/easy-rsa/openssl-1.0.cnf
	install -m 0644 ${S}/vars.example ${D}${sysconfdir}/easy-rsa/vars

	install -m 700 -d ${D}${sysconfdir}/easy-rsa/pki
	install -m 700 -d ${D}${sysconfdir}/easy-rsa/pki/private
	install -m 700 -d ${D}${sysconfdir}/easy-rsa/pki/reqs

	install -d ${D}${sysconfdir}/easy-rsa/x509-types
	cp --preserve=mode,timestamps -R ${S}/x509-types/* ${D}${sysconfdir}/easy-rsa/x509-types/

	install -d ${D}/lib/upgrade/keep.d
	install -m 0644 ${WORKDIR}/openvpn-easy-rsa.upgrade ${D}/lib/upgrade/keep.d/${PN}
}
