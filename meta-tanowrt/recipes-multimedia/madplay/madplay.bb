SUMMARY = "MPEG audio player in fixed point"
DESCRIPTION = "MAD is an MPEG audio decoder. It currently only supports the MPEG 1 \
standard, but fully implements all three audio layers (Layer I, Layer II, \
and Layer III, the latter often colloquially known as MP3.). There is also \
full support for ID3 tags."
HOMEPAGE = "http://sourceforge.net/projects/mad/"
SECTION = "sound"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

PV = "0.15.2b"
PR = "tano0"

DEPENDS += "libmad libid3tag alsa-lib"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

S = "${WORKDIR}/${BPN}-${PV}"

inherit autotools-brokensep gettext

SRC_URI = "${SOURCEFORGE_MIRROR}/projects/mad/files/${BPN}/${PV}/${BPN}-${PV}.tar.gz"
SRC_URI[sha256sum] = "5a79c7516ff7560dffc6a14399a389432bc619c905b13d3b73da22fa65acede0"

SRC_URI += "\
	file://0001-Fix-compilation-with-external-gettext.patch \
	file://0002-switch-to-new-alsa-api.patch \
	file://0003-Allow-to-specify-ALSA-device-in-o-option.patch \
"

EXTRA_OECONF += "\
	--enable-shared \
	--disable-static \
	--disable-debugging \
	--disable-profiling \
	--disable-experimental \
	--without-libiconv-prefix \
	--without-libintl-prefix \
	--without-esd \
	--with-alsa \
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
