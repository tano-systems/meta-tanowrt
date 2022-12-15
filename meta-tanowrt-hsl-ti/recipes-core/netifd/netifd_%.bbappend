#
# SPDX-License-Identifier: MIT
# Copyright (c) 2020-2021 Tano Systems LLC. All rights reserved.
#
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/files:"

PR:append:am335x-icev2 = ".ti2"
PR:append:am335x-bbb = ".ti2"
PR:append:am574x-idk = ".ti1"

RDEPENDS:${PN}:append:am335x-icev2 = "\
	ppp ${@oe.utils.conditional('TANOWRT_LUCI_ENABLE', '1', 'luci-proto-ppp', '', d)} \
"
