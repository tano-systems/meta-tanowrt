#
# SPDX-License-Identifier: MIT
# Copyright (c) 2020-2021 Tano Systems LLC. All rights reserved.
#
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/files:"

PR_append_am335x-icev2 = ".ti2"
PR_append_am335x-bbb = ".ti2"
PR_append_am574x-idk = ".ti1"

RDEPENDS_${PN}_append_am335x-icev2 = "\
	ppp ${@oe.utils.conditional('TANOWRT_LUCI_ENABLE', '1', 'luci-proto-ppp', '', d)} \
"
