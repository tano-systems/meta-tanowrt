# nl80211 based CLI configuration utility for wireless devices
SUMMARY = "nl80211 based CLI configuration utility for wireless devices"
DESCRIPTION = "iw is a new nl80211 based CLI configuration utility for \
wireless devices. It supports almost all new drivers that have been added \
to the kernel recently. "
HOMEPAGE = "http://wireless.kernel.org/en/users/Documentation/iw"
SECTION = "base"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=878618a5c4af25e9b93ef0be1a93f774"

PR = "tano0"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"
DEPENDS = "libnl"

SRC_URI = "http://www.kernel.org/pub/software/network/iw/${BP}.tar.gz"

# Patches
SRC_URI += "\
	file://001-nl80211_h_sync.patch \
	file://120-antenna_gain.patch \
	file://130-survey-bss-rx-time.patch \
	file://200-reduce_size.patch \
"

SRC_URI[md5sum] = "6d4d1c0ee34f3a7bda0e6aafcd7aaf31"
SRC_URI[sha256sum] = "175abbfce86348c0b70e778c13a94c0bfc9abc7a506d2bd608261583aeedf64a"

inherit pkgconfig

CFLAGS += "\
	-DIW_FULL \
	-DCONFIG_LIBNL20 \
	-D_GNU_SOURCE \
	-flto \
"

EXTRA_OEMAKE = "\
	'IW_FULL=1' \
	'PREFIX=${prefix}' \
	'SBINDIR=${sbindir}' \
	'MANDIR=${mandir}' \
"

B = "${S}"

do_install() {
	oe_runmake 'DESTDIR=${D}' install
}
