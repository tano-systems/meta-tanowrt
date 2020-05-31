# Copyright (C) 2018-2020 Anton Kikin <a.kikin@tano-systems.com>

PR = "tano1"
SUMMARY = "SNMP support packages"
DESCRIPTION = "The set of packages required for a SNMP support"
LICENSE = "MIT"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

do_package[vardeps] += "TANOWRT_LUCI_ENABLE"

RDEPENDS_${PN} = "\
	net-snmp-client \
	net-snmp-mibs \
	net-snmp-server-snmpd \
	net-snmp-server-snmptrapd \
	${@oe.utils.conditional('TANOWRT_LUCI_ENABLE', '1', 'luci-app-tn-snmpd', '',d)} \
"
