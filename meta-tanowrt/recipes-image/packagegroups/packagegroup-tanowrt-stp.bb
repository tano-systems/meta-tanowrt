# Copyright (C) 2018-2020 Anton Kikin <a.kikin@tano-systems.com>

PR = "tano1"
SUMMARY = "STP/RSTP support packages"
DESCRIPTION = "The set of packages required for a STP/RSTP support"
LICENSE = "MIT"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

do_package[vardeps] += "TANOWRT_LUCI_ENABLE"

RDEPENDS_${PN} = "\
	mstpd \
	${@oe.utils.conditional('TANOWRT_LUCI_ENABLE', '1', 'luci-app-tn-mstpd', '',d)} \
"
