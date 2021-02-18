#
# SPDX-License-Identifier: MIT
# Copyright (C) 2021 Anton Kikin <a.kikin@tano-systems.com>
#

PR = "tano0"
SUMMARY = "Swupdate support packages"
DESCRIPTION = "The set of packages required for a swupdate support"
LICENSE = "MIT"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

do_package[vardeps] += "TANOWRT_LUCI_ENABLE"

RDEPENDS_${PN} = "\
	swupdate \
	swupdate-client \
	${@oe.utils.conditional('TANOWRT_LUCI_ENABLE', '1', 'luci-app-tn-swupdate', '', d)} \
"
