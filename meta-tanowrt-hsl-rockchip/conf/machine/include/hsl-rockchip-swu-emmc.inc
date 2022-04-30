#
# SPDX-License-Identifier: MIT
# Copyright (c) 2022 Tano Systems LLC. All rights reserved.
#

SWU_UBOOT_CONFIG = "emmc"
SWU_UBOOT_CONFIG_FACTORY = "sdcard"

require hsl-rockchip-swu-estorage.inc

SWU_IMAGE_MEDIA_TYPE = "eMMC"
OPENWRT_VERSION_PRODUCT_EXTRA = " (eMMC)"
