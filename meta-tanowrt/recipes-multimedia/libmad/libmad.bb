SUMMARY = "An high-quality MPEG audio decoding library"
DESCRIPTION = "MAD is a high-quality MPEG audio decoder. It currently supports \
MPEG-1 and the MPEG-2 extension to lower sampling frequencies, \
as well as the de facto MPEG 2.5 format. All three audio layers - \
Layer I, Layer II, and Layer III (i.e. MP3) - are fully implemented."
HOMEPAGE = "http://www.underbit.com/products/mad/"
SECTION = "libs"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

PV = "0.15.1b"
PR = "tano0"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

S = "${WORKDIR}/${BPN}-${PV}"

inherit autotools-brokensep pkgconfig

SRC_URI = "${SOURCEFORGE_MIRROR}/projects/mad/files/${BPN}/${PV}/${BPN}-${PV}.tar.gz"
SRC_URI[sha256sum] = "bbfac3ed6bfbc2823d3775ebb931087371e142bb0e9bb1bee51a76a6e0078690"

SRC_URI += "\
	file://001-mips_removal_h_constraint.patch \
	file://101-CVE-2017-8374-length-check.patch \
	file://102-CVE-2017-8373-CVE-2017-8372-md-size.patch \
	file://0001-configure-Remove-fforce-mem-option.patch \
"

FPM = "intel"
FPM:arm = "arm"
FPM:mips = "mips"

EXTRA_OECONF += "\
	--enable-shared \
	--enable-static \
	--enable-fpm="${FPM}" \
	--disable-debugging \
	--enable-speed \
"

# Create files before starting the autoreconf to fix configure errors:
# | Makefile.am: error: required file './NEWS' not found
# | Makefile.am: error: required file './AUTHORS' not found
# | Makefile.am: error: required file './ChangeLog' not found
do_configure:prepend(){
	touch ${B}/NEWS
	touch ${B}/AUTHORS
	touch ${B}/ChangeLog
}

BBCLASSEXTEND = "native nativesdk"
