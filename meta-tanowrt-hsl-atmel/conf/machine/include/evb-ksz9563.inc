#
# SPDX-License-Identifier: MIT
# Copyright (c) 2021 Tano Systems LLC. All rights reserved.
#

require evb-ksz-sama5d3.inc

#
# Kernel
#
KERNEL_DEVICETREE = "\
	at91-evb-ksz9563.dtb \
"

#
# OpenWrt specific
#
OPENWRT_VERSION_PRODUCT = "Microchip SAMA5D3 EDS with EVB-KSZ9563"
OPENWRT_VERSION_HWREV = "1.0"
