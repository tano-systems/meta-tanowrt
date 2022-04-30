# Copyright (c) 2019, Fuzhou Rockchip Electronics Co., Ltd
# Copyright (c) 2022, Tano Systems LLC
# Released under the MIT license (see COPYING.MIT for the terms)

MACHINEOVERRIDES =. "rock-pi-s:"

require conf/machine/include/hsl-rk3308.inc

SERIAL_CONSOLES = "1500000;ttyS0"

KERNEL_DEVICETREE = "rockchip/rk3308-rock-pi-s.dtb"

RK_WIFIBT_FIRMWARES = ""

MACHINE_EXTRA_RDEPENDS_append = " \
	i2c-tools \
"

# Enable multilib support
require conf/multilib.conf
MULTILIBS = "multilib:lib32"
DEFAULTTUNE_virtclass-multilib-lib32 = "armv7vethf-neon"

RKBIN_DDR_UART_UPDATE = "1"
RKBIN_DDR_UART_ID = "0"
RKBIN_DDR_UART_IOMUX = "0"
RKBIN_DDR_UART_BAUDRATE = "1500000"

OPENWRT_VERSION_PRODUCT_EXTRA ?= ""
OPENWRT_VERSION_PRODUCT = "Radxa ROCK Pi S${OPENWRT_VERSION_PRODUCT_EXTRA}"
OPENWRT_VERSION_HWREV = "v1.3"

TANOWRT_ENABLE_OVERLAY_RESIZE = "1"
