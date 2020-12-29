#
# SPDX-License-Identifier: MIT
#
# CLI utility to build and manage a PKI CA
# OpenWrt recipe
#
# Copyright (c) 2019 Tano Systems LLC. All rights reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
INC_PR = "1"

SUMMARY = "CLI utility to build and manage a PKI CA"
HOMEPAGE = "http://openvpn.net"
SECTION = "net"
DEPENDS = "openssl"

SRC_URI += "\
	file://openvpn-easy-rsa.upgrade \
"

do_configure[noexec] = "1"
do_compile[noexec]   = "1"

FILES_${PN} = "\
	/usr/bin/* \
	/etc/easy-rsa/* \
	/lib/upgrade/keep.d/* \
"

CONFFILES_${PN} = " \
	${sysconfdir}/easy-rsa/vars \
	${sysconfdir}/easy-rsa/openssl-1.0.cnf \
	${sysconfdir}/easy-rsa/openssl-easyrsa.cnf \
"

do_install_append() {
	install -d ${D}/usr/bin
	install -m 0755 ${S}/easyrsa ${D}/usr/bin

	install -d ${D}${sysconfdir}/easy-rsa

	if [ -f ${S}/openssl-easyrsa.cnf ]; then
		# For versions 3.0.4+
		install -m 0644 ${S}/openssl-easyrsa.cnf ${D}${sysconfdir}/easy-rsa/openssl-easyrsa.cnf
		ln -s ${sysconfdir}/easy-rsa/openssl-easyrsa.cnf ${D}${sysconfdir}/easy-rsa/openssl-1.0.cnf
	else
		if [ -f ${S}/openssl-1.0.cnf ]; then
			# For version 3.0.3 and lower
			install -m 0644 ${S}/openssl-1.0.cnf ${D}${sysconfdir}/easy-rsa/openssl-1.0.cnf
		fi
	fi

	install -m 0644 ${S}/vars.example ${D}${sysconfdir}/easy-rsa/vars

	install -m 700 -d ${D}${sysconfdir}/easy-rsa/pki
	install -m 700 -d ${D}${sysconfdir}/easy-rsa/pki/private
	install -m 700 -d ${D}${sysconfdir}/easy-rsa/pki/reqs

	install -d ${D}${sysconfdir}/easy-rsa/x509-types
	cp --preserve=mode,timestamps -R ${S}/x509-types/* ${D}${sysconfdir}/easy-rsa/x509-types/

	install -d ${D}/lib/upgrade/keep.d
	install -m 0644 ${WORKDIR}/openvpn-easy-rsa.upgrade ${D}/lib/upgrade/keep.d/${PN}
}
