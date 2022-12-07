#
# SPDX-License-Identifier: MIT
# This file Copyright (C) 2019-2021 Anton Kikin <a.kikin@tano-systems.com>
#
PR:append = ".tano0.${INC_PR}"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}_${PV}/files:${THISDIR}/${PN}_${PV}/patches:"

DEPENDS:remove = "${@bb.utils.contains('MACHINE_FEATURES', 'pci', '', 'pciutils',d)}"

EXTRA_OECONF += "${@bb.utils.contains('DISTRO_FEATURES', 'systemd', '--with-systemd', '', d)}"

FILES:${PN}-perl-modules += "${sysconfdir}/snmp/snmp-bridge-mib"

RDEPENDS:${PN}-libs:remove = "${@bb.utils.contains('MACHINE_FEATURES', 'pci', '', 'libpci', d)}"
RDEPENDS:${PN}-perl-modules += "perl"

require ${PN}-openwrt.inc
