SUMMARY = "C library for encoding data in a QR Code symbol"
AUTHOR = "Kentaro Fukuchi"
HOMEPAGE = "http://fukuchi.org/works/qrencode/"
SECTION = "libs"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=2d5025d4aa3495befef8f17206a5b0a1"

INC_PR = "0"

inherit autotools pkgconfig

PACKAGES += "${PN}-tools"

FILES_${PN} = "${libdir}/libqrencode.so*"
RRECOMMENDS_${PN} += "${PN}-tools"

FILES_${PN}-tools = "${bindir}/"
RDEPENDS_${PN}-tools += "${PN}"

S = "${WORKDIR}/git"

EXTRA_OECONF += "--without-tests"
