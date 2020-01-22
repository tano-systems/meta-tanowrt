# This file Copyright (C) 2019 Anton Kikin <a.kikin@tano-systems.com>
PR_append = ".tano0.${INC_PR}"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}_${PV}/files:${THISDIR}/${PN}_${PV}/patches:"

DEPENDS_remove = "${@bb.utils.contains('MACHINE_FEATURES', 'pci', '', 'pciutils',d)}"

EXTRA_OECONF += "${@bb.utils.contains('DISTRO_FEATURES', 'systemd', '--with-systemd', '', d)}"

#do_install_append() {
#    sed    -e "s@\([^ ]*-fdebug-prefix-map=[^ ]*\)\1*@@g" \
#           -e "s@\([^ ]*--sysroot=[^ ]*\)\1*@@g" \
#           -e "s@\([^ ]*--with-libtool-sysroot=[^ ]*\)\1*@@g" \
#          -e "s@\([^ ]*--with-install-prefix=[^ ]*\)\1*@@g" \
#       -i ${D}${bindir}/net-snmp-config

#    if [ "${HAS_PERL}" = "1" ]; then
#        install -m 0755 ${B}/local/snmp-bridge-mib ${D}${sysconfdir}/snmp/
#    fi
#}

FILES_${PN}-perl-modules += "${sysconfdir}/snmp/snmp-bridge-mib"

RDEPENDS_${PN}-libs_remove = "${@bb.utils.contains('MACHINE_FEATURES', 'pci', '', 'libpci', d)}"
RDEPENDS_${PN}-perl-modules += "perl"

require ${PN}-openwrt.inc
