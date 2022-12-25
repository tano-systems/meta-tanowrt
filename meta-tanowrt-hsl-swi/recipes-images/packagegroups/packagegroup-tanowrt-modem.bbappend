#
# SPDX-License-Identifier: MIT
# Copyright (c) 2020 Tano Systems LLC. All rights reserved.
#
PR:append = ".swi0"

RDEPENDS:${PN}:remove = "\
	comgt-ncm \
	uqmi \
	umbim \
	${@oe.utils.conditional('TANOWRT_LUCI_ENABLE', '1', 'luci-proto-ncm', '',d)} \
	${@oe.utils.conditional('TANOWRT_LUCI_ENABLE', '1', 'luci-proto-qmi', '',d)} \
"
