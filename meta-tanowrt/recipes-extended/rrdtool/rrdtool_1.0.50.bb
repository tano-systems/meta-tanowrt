#
DESCRIPTION = "High performance data logging and graphing system for time series data."
HOMEPAGE = "http://oss.oetiker.ch/rrdtool/"
SECTION = "utils"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=44fee82a1d2ed0676cf35478283e0aa0"

DEPENDS = "libpng zlib"

PR = "tano0"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI = "http://oss.oetiker.ch/rrdtool/pub/rrdtool-1.0.x/rrdtool-${PV}.tar.gz"

SRC_URI[md5sum] = "c466e2e7df95fa8e318e46437da87686"
SRC_URI[sha256sum] = "42aa7c213dedbd95d33ca84d92f4187880f7e96062c6a3fb05bfb16f77ba2a91"

# Patches
SRC_URI += "\
	file://001-no_ordering_cd_joke.patch \
	file://002-no_timezone.patch \
	file://020-x86-float-cast.patch \
	file://030-pod2man-stderr.patch \
"

inherit autotools

EXTRA_OECONF = "\
	--enable-shared=yes \
	--enable-static=yes \
	--with-gnu-ld \
	--enable-local-zlib \
	ac_cv_path_PERL=no \
	rd_cv_ieee_works=yes \
	shrext_cmds='.so' \
"

do_install_append() {
	rm -rf ${D}/usr/bin/trytime
	rm -rf ${D}/usr/contrib
	rm -rf ${D}/usr/doc
	rm -rf ${D}/usr/examples
	rm -rf ${D}/usr/html

#	mv ${D}/usr/html ${D}${docdir}/rrdtool/
#	mv ${D}/usr/doc/* ${D}${docdir}/rrdtool/
#	mv ${D}/usr/examples ${D}${docdir}/rrdtool/
#	mv ${D}/usr/contrib ${D}${docdir}/rrdtool/
}

#do_install_append() {
#	install -d ${D}${docdir}/rrdtool/
#}

#FILES_${PN} += "${libdir}/perl/auto/RRDs/RRDs.bs ${libdir}/perl/auto/RRDs/RRDs.so ${libdir}/perl/RRDs.pm ${libdir}/perl/RRDp.pm"
