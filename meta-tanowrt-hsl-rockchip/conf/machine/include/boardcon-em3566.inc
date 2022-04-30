# Copyright (c) 2019, Fuzhou Rockchip Electronics Co., Ltd
# Copyright (c) 2022, Tano Systems LLC
# Released under the MIT license (see COPYING.MIT for the terms)

MACHINEOVERRIDES =. "boardcon-em356x:"
MACHINEOVERRIDES =. "boardcon-em3566:"

require conf/machine/include/hsl-rk356x.inc
require conf/machine/include/boardcon-em356x.inc

RK_WIFIBT_FIRMWARES = ""

KERNEL_DEVICETREE = "rockchip/boardcon-em3566.dtb"

RKBIN_DDR_UART_UPDATE = "1"
RKBIN_DDR_UART_ID = "4"
RKBIN_DDR_UART_IOMUX = "0"
RKBIN_DDR_UART_BAUDRATE = "1500000"

OPENWRT_VERSION_PRODUCT_EXTRA ?= ""
OPENWRT_VERSION_PRODUCT = "Boardcon EM3566 SBC${OPENWRT_VERSION_PRODUCT_EXTRA}"
OPENWRT_VERSION_HWREV = "v2"

TANOWRT_ENABLE_OVERLAY_RESIZE = "1"
