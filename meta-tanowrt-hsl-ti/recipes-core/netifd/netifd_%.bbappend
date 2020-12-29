#
# SPDX-License-Identifier: MIT
# Copyright (c) 2020 Tano Systems LLC. All rights reserved.
#
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/files:"

PR_append_am335x-icev2 = ".ti1"
PR_append_am335x-bbb = ".ti0"
PR_append_am574x-idk = ".ti0"

RDEPENDS_${PN}_append_am335x-icev2 = "\
	ppp ${@oe.utils.conditional('TANOWRT_LUCI_ENABLE', '1', 'luci-proto-ppp', '', d)} \
"
