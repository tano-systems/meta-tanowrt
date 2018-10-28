# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>

PR = "tano1"

SUMMARY = "non-mainline-kernel netfilter extensions"
DESCRIPTION = "Xtables-addons contains a set of possibly useful but not included in the mainline kernel nefilter extensions"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=751419260aa954499f7abaabaa882bbe"
DEPENDS = "virtual/kernel iptables bc-native"

inherit autotools kernel-module-split module-base pkgconfig

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI = "git://git.code.sf.net/p/xtables-addons/xtables-addons;branch=master"
SRCREV = "0e9037b0007eaaf0ae612a1481bd03b15ecb8cb8"

SRC_URI += " \
	file://100-add-rtsp-conntrack.patch \
	file://200-add-lua-packetscript.patch \
	file://201-fix-lua-packetscript.patch \
	file://202-add-lua-autoconf.patch \
	file://0001-Unset-LDFLAGS-for-kernel-modules.patch \
"
S = "${WORKDIR}/git"

MODULES_MODULE_SYMVERS_LOCATION = "../${BPN}-${PV}/extensions"

EXTRA_OECONF = "\
	--with-kbuild=${STAGING_KERNEL_DIR} \
	--with-xtlibdir=${libdir}/iptables \
"

EXTRA_OEMAKE = "M=${S}/extentions DESTDIR=${D} V=1"
MODULES_INSTALL_TARGET = "install"

# make_scripts requires kernel source directory to create
# kernel scripts
addtask make_scripts after do_patch before do_compile
do_make_scripts[lockfiles] = "${TMPDIR}/kernel-scripts.lock"
do_make_scripts[depends] += "virtual/kernel:do_shared_workdir bc-native:do_populate_sysroot"

FILES_${PN} += "${libexecdir}/xtables-addons ${sbindir}/iptaccount ${libdir}/libxt_ACCOUNT_cl.so.* ${libdir}/iptables"

RDEPENDS_${PN} += "perl"
