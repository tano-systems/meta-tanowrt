SUMMARY = "C library for encoding data in a QR Code symbol"
AUTHOR = "Kentaro Fukuchi"
HOMEPAGE = "http://fukuchi.org/works/qrencode/"
SECTION = "libs"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=2d5025d4aa3495befef8f17206a5b0a1"
PV = "4.0.2"
PR = "tano0"

SRCREV = "59ee597f913fcfda7a010a6e106fbee2595f68e4"
SRC_URI = "git://github.com/fukuchi/libqrencode.git;branch=4.0"

S = "${WORKDIR}/git"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"
SRC_URI += "file://001-add-inline-svg.patch"

inherit autotools pkgconfig

EXTRA_OECONF += "--without-tests"

PACKAGES += "${PN}-tools"

FILES_${PN} = "${libdir}/libqrencode.so*"
RRECOMMENDS_${PN} += "${PN}-tools"

FILES_${PN}-tools = "${bindir}/"
RDEPENDS_${PN}-tools += "${PN}"
