#
# SPDX-License-Identifier: MIT
# Copyright (C) 2018-2020 Anton Kikin <a.kikin@tano-systems.com>
#

PR = "tano1"
SUMMARY = "OpenVPN support packages"
DESCRIPTION = "The set of packages required for a OpenVPN support"
LICENSE = "MIT"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

do_package[vardeps] += "TANOWRT_LUCI_ENABLE"

RDEPENDS_${PN} = "\
	openvpn \
	openvpn-easy-rsa \
	${@oe.utils.conditional('TANOWRT_LUCI_ENABLE', '1', 'luci-app-openvpn', '',d)} \
"
