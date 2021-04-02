#
# SPDX-License-Identifier: MIT
# Copyright (c) 2021 Tano Systems LLC. All rights reserved.
#

require evb-ksz-sama5d3.inc

#
# Kernel
#
KERNEL_DEVICETREE = "\
	at91-evb-ksz9477.dtb \
	at91-evb-ksz9477-sfp.dtb \
"

#
# OpenWrt specific
#
OPENWRT_VERSION_PRODUCT = "Microchip EVB-KSZ9477"
OPENWRT_VERSION_HWREV = "1.0"
