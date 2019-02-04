#
PR = "tano6"

SUMMARY = "Various tools relating to the Simple Network Management Protocol"
HOMEPAGE = "http://www.net-snmp.org/"
SECTION = "net"
LICENSE = "BSD"

LIC_FILES_CHKSUM = "file://README;beginline=3;endline=8;md5=7f7f00ba639ac8e8deb5a622ea24634e"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

inherit siteinfo autotools-brokensep siteinfo pkgconfig perlnative

DEPENDS = "\
	${@bb.utils.contains('MACHINE_FEATURES', 'pci', 'pciutils', '',d)} \
	openssl"

SRC_URI = "${SOURCEFORGE_MIRROR}/net-snmp/net-snmp-${PV}.zip \
	file://net-snmp-add-knob-whether-nlist.h-are-checked.patch \
	file://fix-libtool-finish.patch \
	file://net-snmp-testing-add-the-output-format-for-ptest.patch \
	file://dont-return-incompletely-parsed-varbinds.patch \
	file://0001-config_os_headers-Error-Fix.patch \
	file://0001-config_os_libs2-Error-Fix.patch \
	file://0001-snmplib-keytools.c-Don-t-check-for-return-from-EVP_M.patch \
	file://net-snmp-agentx-crash.patch \
	file://0001-get_pid_from_inode-Include-limit.h.patch \
	file://0001-BUG-a2584-Fix-snmptrap-to-use-clientaddr-from-snmp.c.patch \
	file://0001-snmplib-UDPIPv6-transport-Add-a-missing-return-state.patch \
	file://0001-configure-fix-check-for-enable-perl-cc-checks.patch \
	file://0002-configure-fix-a-cc-check-issue.patch \
	file://0003-CHANGES-BUG-2712-Fix-Perl-module-compilation.patch \
	file://0004-configure-fix-incorrect-variable.patch \
	file://net-snmp-5.7.2-fix-engineBoots-value-on-SIGHUP.patch \
	file://net-snmp-fix-for-disable-des.patch \
	file://0001-Remove-U64-typedef.patch \
	${@bb.utils.contains('MACHINE_FEATURES', 'pci', '', 'file://0002-disable-pci.patch',d)} \
"

SRC_URI[md5sum] = "9f682bd70c717efdd9f15b686d07baee"
SRC_URI[sha256sum] = "e8dfc79b6539b71a6ff335746ce63d2da2239062ad41872fff4354cafed07a3e"

PARALLEL_MAKE = ""
CCACHE = ""

TARGET_CC_ARCH += "${LDFLAGS}"

#bb.utils.filter
PACKAGECONFIG ?= "${@bb.utils.filter('ipv6', '${DISTRO_FEATURES}', d)}"
PACKAGECONFIG[elfutils] = "--with-elf, --without-elf, elfutils"
PACKAGECONFIG[libnl] = "--with-nl, --without-nl, libnl"

PACKAGECONFIG[perl] = "--enable-embedded-perl --with-perl-modules=yes, --disable-embedded-perl --with-perl-modules=no,\
                       perl, perl perl-lib"

PACKAGECONFIG[ipv6] = "--enable-ipv6,--disable-ipv6,,"

CACHED_CONFIGUREVARS = " \
    ac_cv_header_valgrind_valgrind_h=no \
    ac_cv_header_valgrind_memcheck_h=no \
    ac_cv_ETC_MNTTAB=/etc/mtab \
    lt_cv_shlibpath_overrides_runpath=yes \
"

EXTRA_OEMAKE = "INSTALL_PREFIX=${D} OTHERLDFLAGS='${LDFLAGS}' HOST_CPPFLAGS='${BUILD_CPPFLAGS}'"

EXTRA_OECONF = "--enable-shared \
                --disable-manuals \
                --with-defaults \
                --with-logfile=/var/log/snmpd.log \
                --with-install-prefix=${D} \
                --with-persistent-directory=${localstatedir}/lib/net-snmp \
                ${@oe.utils.conditional('SITEINFO_ENDIANNESS', 'le', '--with-endianness=little', '--with-endianness=big', d)} \
"

# net-snmp needs to have mib-modules=smux enabled to enable quagga to support snmp
EXTRA_OECONF += "--with-mib-modules=smux"

EXTRA_OECONF += "${@bb.utils.contains('DISTRO_FEATURES', 'systemd', '--with-systemd', '', d)}"


export PERLPROG="${bindir}/env perl"
PERLPROG_append = "${@bb.utils.contains('PACKAGECONFIG', 'perl', ' -I${WORKDIR}', '', d)}"
HAS_PERL = "${@bb.utils.contains('PACKAGECONFIG', 'perl', '1', '0', d)}"

do_configure_prepend() {
    sed -i -e "s|I/usr/include|I${STAGING_INCDIR}|g" \
        "${S}"/configure \
        "${S}"/configure.d/config_os_libs2

    if [ "${HAS_PERL}" = "1" ]; then
        # this may need to be changed when package perl has any change.
        cp -f ${STAGING_DIR_TARGET}/usr/lib*/perl/*/Config.pm ${WORKDIR}/
        cp -f ${STAGING_DIR_TARGET}/usr/lib*/perl/*/Config_heavy.pl ${WORKDIR}/
        sed -e "s@libpth => '/usr/lib.*@libpth => '${STAGING_DIR_TARGET}/${libdir} ${STAGING_DIR_TARGET}/${base_libdir}',@g" \
            -e "s@privlibexp => '/usr@privlibexp => '${STAGING_DIR_TARGET}/usr@g" \
            -e "s@scriptdir => '/usr@scriptdir => '${STAGING_DIR_TARGET}/usr@g" \
            -e "s@sitearchexp => '/usr@sitearchexp => '${STAGING_DIR_TARGET}/usr@g" \
            -e "s@sitelibexp => '/usr@sitearchexp => '${STAGING_DIR_TARGET}/usr@g" \
            -e "s@vendorarchexp => '/usr@vendorarchexp => '${STAGING_DIR_TARGET}/usr@g" \
            -e "s@vendorlibexp => '/usr@vendorlibexp => '${STAGING_DIR_TARGET}/usr@g" \
            -i ${WORKDIR}/Config.pm
    fi
}

do_configure_append() {
    if [ "${HAS_PERL}" = "1" ]; then
        sed -e "s@^NSC_INCLUDEDIR=.*@NSC_INCLUDEDIR=${STAGING_DIR_TARGET}\$\{includedir\}@g" \
            -e "s@^NSC_LIBDIR=-L.*@NSC_LIBDIR=-L${STAGING_DIR_TARGET}\$\{libdir\}@g" \
            -i ${B}/net-snmp-config
    fi
}

do_install_append() {
    sed    -e "s@^NSC_SRCDIR=.*@NSC_SRCDIR=.@g" \
        -i ${D}${bindir}/net-snmp-create-v3-user
    sed    -e "s@^NSC_SRCDIR=.*@NSC_SRCDIR=.@g" \
           -e "s@\([^ ]*-fdebug-prefix-map=[^ ]*\)\1*@@g" \
           -e "s@\([^ ]*--sysroot=[^ ]*\)\1*@@g" \
           -e "s@\([^ ]*--with-libtool-sysroot=[^ ]*\)\1*@@g" \
           -e "s@\([^ ]*--with-install-prefix=[^ ]*\)\1*@@g" \
        -i ${D}${bindir}/net-snmp-config

    if [ "${HAS_PERL}" = "1" ]; then
        sed -e "s@^NSC_INCLUDEDIR=.*@NSC_INCLUDEDIR=\$\{includedir\}@g" \
            -e "s@^NSC_LIBDIR=-L.*@NSC_LIBDIR=-L\$\{libdir\}@g" \
            -i ${D}${bindir}/net-snmp-config

        install -m 0755 ${B}/local/snmp-bridge-mib ${D}${sysconfdir}/snmp/
    fi
}

do_install_ptest() {
    install -d ${D}${PTEST_PATH}
    for i in ${S}/dist ${S}/include ${B}/include ${S}/mibs ${S}/configure \
        ${B}/net-snmp-config ${S}/testing; do
        if [ -e "$i" ]; then
            cp -R --no-dereference --preserve=mode,links -v "$i" ${D}${PTEST_PATH}
        fi
    done
    echo `autoconf -V|awk '/autoconf/{print $NF}'` > ${D}${PTEST_PATH}/dist/autoconf-version

    rmdlist="${D}${PTEST_PATH}/dist/net-snmp-solaris-build"
    for i in $rmdlist; do
        if [ -d "$i" ]; then
            rm -rf "$i"
        fi
    done
}

SYSROOT_PREPROCESS_FUNCS += "net_snmp_sysroot_preprocess"
SNMP_DBGDIR = "/usr/src/debug/${PN}/${EXTENDPE}${PV}-${PR}"

net_snmp_sysroot_preprocess () {
    if [ -e ${D}${bindir}/net-snmp-config ]; then
        install -d ${SYSROOT_DESTDIR}${bindir_crossscripts}/
        install -m 755 ${D}${bindir}/net-snmp-config ${SYSROOT_DESTDIR}${bindir_crossscripts}/
        sed -e "s@-I/usr/include@-I${STAGING_INCDIR}@g" \
            -e "s@^prefix=.*@prefix=${STAGING_DIR_HOST}${prefix}@g" \
            -e "s@^exec_prefix=.*@exec_prefix=${STAGING_EXECPREFIXDIR}@g" \
            -e "s@^includedir=.*@includedir=${STAGING_INCDIR}@g" \
            -e "s@^libdir=.*@libdir=${STAGING_LIBDIR}@g" \
            -e "s@^NSC_SRCDIR=.*@NSC_SRCDIR=${S}@g" \
            -e "s@-fdebug-prefix-map=${SNMP_DBGDIR}@-fdebug-prefix-map=${WORKDIR}=${SNMP_DBGDIR}@g" \
            -e "s@-fdebug-prefix-map= -fdebug-prefix-map=@-fdebug-prefix-map=${STAGING_DIR_NATIVE}= \
                  -fdebug-prefix-map=${STAGING_DIR_HOST}=@g" \
            -e "s@--sysroot=@--sysroot=${STAGING_DIR_HOST}@g" \
            -e "s@--with-libtool-sysroot=@--with-libtool-sysroot=${STAGING_DIR_HOST}@g" \
            -e "s@--with-install-prefix=@--with-install-prefix=${D}@g" \
          -i  ${SYSROOT_DESTDIR}${bindir_crossscripts}/net-snmp-config
    fi
}

PACKAGES += "${PN}-libs ${PN}-mibs ${PN}-server ${PN}-client ${PN}-server-snmpd ${PN}-server-snmptrapd"

# perl module
PACKAGES += "${@bb.utils.contains('PACKAGECONFIG', 'perl', '${PN}-perl-modules', '', d)}"

ALLOW_EMPTY_${PN} = "1"
ALLOW_EMPTY_${PN}-server = "1"

FILES_${PN}-perl-modules  = "${libdir}/perl/*"
FILES_${PN}-perl-modules += "${sysconfdir}/snmp/snmp-bridge-mib"

FILES_${PN}-libs = "${libdir}/lib*${SOLIBS}"
FILES_${PN}-mibs = "${datadir}/snmp/mibs"

FILES_${PN}-server-snmpd = "${sbindir}/snmpd"
FILES_${PN}-server-snmptrapd = "${sbindir}/snmptrapd"

FILES_${PN} = ""
FILES_${PN}-client = "${bindir}/* ${datadir}/snmp/"
FILES_${PN}-dbg += "${libdir}/.debug/ ${sbindir}/.debug/ ${bindir}/.debug/"
FILES_${PN}-dev += "${bindir}/mib2c ${bindir}/mib2c-update"



RDEPENDS_${PN} += "${@bb.utils.contains('PACKAGECONFIG', 'perl', 'net-snmp-perl-modules', '', d)}"
RDEPENDS_${PN} += "net-snmp-client"
RDEPENDS_${PN}-server-snmpd += "net-snmp-mibs"
RDEPENDS_${PN}-server-snmptrapd += "net-snmp-server-snmpd"
RDEPENDS_${PN}-server += "net-snmp-server-snmpd net-snmp-server-snmptrapd"
RDEPENDS_${PN}-client += "net-snmp-mibs net-snmp-libs"
RDEPENDS_${PN}-libs += "${@bb.utils.contains('MACHINE_FEATURES', 'pci', 'libpci', '',d)}"
RDEPENDS_${PN}-ptest += "perl \
                         perl-module-test \
                         perl-module-file-basename \
                         perl-module-getopt-long \
                         perl-module-file-temp \
                         perl-module-data-dumper \
"

RDEPENDS_${PN}-perl-modules += "perl"

RDEPENDS_${PN}-dev = "net-snmp-client (= ${EXTENDPKGV}) net-snmp-server (= ${EXTENDPKGV})"
RRECOMMENDS_${PN}-dbg = "net-snmp-client (= ${EXTENDPKGV}) net-snmp-server (= ${EXTENDPKGV})"

RPROVIDES_${PN}-server-snmpd += "${PN}-server-snmpd-systemd"
RREPLACES_${PN}-server-snmpd += "${PN}-server-snmpd-systemd"
RCONFLICTS_${PN}-server-snmpd += "${PN}-server-snmpd-systemd"

RPROVIDES_${PN}-server-snmptrapd += "${PN}-server-snmptrapd-systemd"
RREPLACES_${PN}-server-snmptrapd += "${PN}-server-snmptrapd-systemd"
RCONFLICTS_${PN}-server-snmptrapd += "${PN}-server-snmptrapd-systemd"

LEAD_SONAME = "libnetsnmp.so"

require ${PN}-openwrt.inc
