#
PV = "1.6.17"
PR = "tano0"

SUMMARY = "A library to simplify DNS programming"
DESCRIPTION = "\
The goal of ldns is to simplify DNS programming, it supports recent RFCs like \
the DNSSEC documents, and allows developers to easily create software \
conforming to current RFCs, and experimental software for current Internet \
Drafts."

SECTION = "net/misc"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"
DEPENDS = "libtool openssl"

SRC_URI = "\
	http://www.nlnetlabs.nl/downloads/ldns/ldns-${PV}.tar.gz \
	file://001-perl5-defined-array.patch \
"

SRC_URI[sha256sum] = "8b88e059452118e8949a2752a55ce59bc71fa5bc414103e17f5b6b06f9bcc8cd"
S = "${WORKDIR}/ldns-${PV}"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=34330f15b2b4abbbaaa7623f79a6a019"

inherit autotools

EXTRA_OECONF += "\
	--disable-ecdsa \
	--disable-gost \
	--with-drill \
	--with-ssl=${STAGING_DIR_TARGET}/usr \
"

do_configure_append() {
	ln -s ${HOST_SYS}-libtool ${B}/libtool
}

PACKAGES += "drill"

SUMMARY_drill = "DNS(SEC) information tool"
DESCRIPTION_drill = "\
drill is a tool to designed to get all sorts of information out of the DNS. It \
is specificly designed to be used with DNSSEC."

RDEPENDS_drill += "${PN}"
ALLOW_EMPTY_drill = "1"
