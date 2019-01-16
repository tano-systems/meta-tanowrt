SUMMARY = "non-mainline-kernel netfilter extensions"
DESCRIPTION = "Xtables-addons contains a set of possibly useful but not included in the mainline kernel nefilter extensions"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=751419260aa954499f7abaabaa882bbe"
DEPENDS = "virtual/kernel iptables bc-native"

inherit autotools kernel-module-split module-base pkgconfig

S = "${WORKDIR}/xtables-addons-${PV}"

do_compile[depends] += "virtual/kernel:do_deploy"

MODULES_MODULE_SYMVERS_LOCATION = "../${BPN}-${PV}/extensions"

EXTRA_OECONF = "--with-kbuild=${STAGING_KERNEL_DIR}"

EXTRA_OEMAKE = "M=${S}/extentions DESTDIR=${D} V=1"
MODULES_INSTALL_TARGET = "install"

# make_scripts requires kernel source directory to create
# kernel scripts
addtask make_scripts after do_patch before do_compile
do_make_scripts[lockfiles] = "${TMPDIR}/kernel-scripts.lock"
do_make_scripts[depends] += "virtual/kernel:do_shared_workdir bc-native:do_populate_sysroot"

FILES_${PN} += "${libexecdir}/xtables-addons ${sbindir}/iptaccount ${libdir}/libxt_ACCOUNT_cl.so.* ${libdir}/iptables"

RDEPENDS_${PN} += "perl"
