SUMMARY = "non-mainline-kernel netfilter extensions"
DESCRIPTION = "Xtables-addons contains a set of possibly useful but not included in the mainline kernel nefilter extensions"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=751419260aa954499f7abaabaa882bbe"
DEPENDS = "virtual/kernel iptables bc-native"

inherit autotools kernel-module-split module-base pkgconfig

INC_PR = "1"

do_compile[depends] += "virtual/kernel:do_deploy"

MODULES_MODULE_SYMVERS_LOCATION = "../${BPN}-${PV}/extensions"

EXTRA_OECONF = "\
	--with-kbuild=${STAGING_KERNEL_BUILDDIR} \
	--with-xtlibdir=${libdir}/iptables \
"

EXTRA_OEMAKE = "M=${S}/extensions DESTDIR=${D} V=1 DEPMOD=/bin/true"
MAKE_TARGETS = "all"
MODULES_INSTALL_TARGET = "install"

FILES_${PN} += "${libexecdir}/xtables-addons ${sbindir}/iptaccount ${libdir}/libxt_ACCOUNT_cl.so.* ${libdir}/iptables"

RDEPENDS_${PN} += "perl"
