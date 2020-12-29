#
# SPDX-License-Identifier: MIT
# Copyright (C) 2018-2020 Anton Kikin <a.kikin@tano-systems.com>
#

PR = "tano1"
SUMMARY = "Modem (3G/4G) support packages"
DESCRIPTION = "The set of packages required for a modem support"
LICENSE = "MIT"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

do_package[vardeps] += "TANOWRT_LUCI_ENABLE"

RDEPENDS_${PN} = "\
	comgt \
	ppp \
	comgt-ncm \
	uqmi \
	umbim \
	${@oe.utils.conditional('TANOWRT_LUCI_ENABLE', '1', 'luci-proto-3g', '',d)} \
	${@oe.utils.conditional('TANOWRT_LUCI_ENABLE', '1', 'luci-proto-ppp', '',d)} \
	${@oe.utils.conditional('TANOWRT_LUCI_ENABLE', '1', 'luci-proto-ncm', '',d)} \
	${@oe.utils.conditional('TANOWRT_LUCI_ENABLE', '1', 'luci-proto-qmi', '',d)} \
"
