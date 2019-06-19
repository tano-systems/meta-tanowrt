# This file Copyright (C) 2018-2019 Anton Kikin <a.kikin@tano-systems.com>
PR_append = ".tano8.${INC_PR}"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}_${PV}/files:${THISDIR}/${PN}_${PV}/patches:"

SRC_URI += " \
	file://0001-Remove-U64-typedef.patch \
	file://1001-CHANGES-BUG-2743-snmpd-crashes-when-receiving-a-GetNext.patch \
	${@bb.utils.contains('MACHINE_FEATURES', 'pci', '', 'file://0002-disable-pci.patch',d)} \
"

DEPENDS_remove = "${@bb.utils.contains('MACHINE_FEATURES', 'pci', '', 'pciutils',d)}"

PACKAGECONFIG ??= "${@bb.utils.filter('DISTRO_FEATURES', 'ipv6', d)}"
EXTRA_OECONF += "${@bb.utils.contains('DISTRO_FEATURES', 'systemd', '--with-systemd', '', d)}"

do_install_append() {
    sed    -e "s@\([^ ]*-fdebug-prefix-map=[^ ]*\)\1*@@g" \
           -e "s@\([^ ]*--sysroot=[^ ]*\)\1*@@g" \
           -e "s@\([^ ]*--with-libtool-sysroot=[^ ]*\)\1*@@g" \
           -e "s@\([^ ]*--with-install-prefix=[^ ]*\)\1*@@g" \
        -i ${D}${bindir}/net-snmp-config

    if [ "${HAS_PERL}" = "1" ]; then
        install -m 0755 ${B}/local/snmp-bridge-mib ${D}${sysconfdir}/snmp/
    fi
}

FILES_${PN}-perl-modules += "${sysconfdir}/snmp/snmp-bridge-mib"

RDEPENDS_${PN}-libs_remove = "${@bb.utils.contains('MACHINE_FEATURES', 'pci', '', 'libpci', d)}"
RDEPENDS_${PN}-perl-modules += "perl"

require ${PN}-openwrt.inc
