#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2020 Anton Kikin <a.kikin@tano-systems.com>
#
require recipes-image/images/tanowrt-image-full.bb

PR:append = ".0"
SUMMARY = "TanoWrt Image intended for network routers"
PACKAGE_ARCH = "${MACHINE_ARCH}"

CORE_IMAGE_EXTRA_INSTALL += "\
	${@oe.utils.conditional('TANOWRT_LUCI_ENABLE', '1', 'luci-app-nlbwmon', '', d)} \
	${@oe.utils.conditional('TANOWRT_LUCI_ENABLE', '1', 'luci-app-vnstat2', '', d)} \
"
