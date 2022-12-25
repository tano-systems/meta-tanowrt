# Copyright (c) 2019, Fuzhou Rockchip Electronics Co., Ltd
# Copyright (c) 2022, Tano Systems LLC
# Released under the MIT license (see COPYING.MIT for the terms)

SOC_FAMILY ?= "rk3308"

DEFAULTTUNE ?= "cortexa35-crypto"
require conf/machine/include/arm/armv8a/tune-cortexa35.inc
require conf/machine/include/hsl-rockchip-arm64-common.inc

# 4.19
PREFERRED_PROVIDER_virtual/kernel = "linux-tano-rockchip"
PREFERRED_VERSION_linux-tano-rockchip = "4.19%"
LINUXLIBCVERSION = "4.19-custom%"
TANOWRT_WIREGUARD_IN_KERNEL = "1"
