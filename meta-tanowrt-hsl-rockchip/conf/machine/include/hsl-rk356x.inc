# Copyright (c) 2020, Rockchip Electronics Co., Ltd
# Copyright (c) 2022-2023, Tano Systems LLC
# Released under the MIT license (see COPYING.MIT for the terms)

SOC_FAMILY ?= "rk3568"

MACHINE_FEATURES:append = "\
	pci \
"

require conf/machine/include/arm/armv8-2a/tune-cortexa55.inc
require conf/machine/include/hsl-rockchip-arm64-common.inc

PREFERRED_PROVIDER_virtual/egl ?= "rockchip-libmali"
PREFERRED_PROVIDER_virtual/libgles1 ?= "rockchip-libmali"
PREFERRED_PROVIDER_virtual/libgles2 ?= "rockchip-libmali"
PREFERRED_PROVIDER_virtual/libgles3 ?= "rockchip-libmali"
PREFERRED_PROVIDER_virtual/libopencl ?= "rockchip-libmali"
PREFERRED_PROVIDER_virtual/libgbm ?= "rockchip-libmali"
PACKAGECONFIG:pn-wayland ?= "${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'no-egl', '', d)}"

DEPENDS:append:pn-mesa-gl = " rockchip-libmali"

PREFERRED_PROVIDER_virtual/libgl ?= "mesa-gl"
PREFERRED_PROVIDER_virtual/mesa ?= "mesa-gl"

MALI_GPU := "bifrost-g52"
MALI_VERSION := "g2p0"

RK_ISP_VERSION := "2.1"

# 4.19
#PREFERRED_PROVIDER_virtual/kernel = "linux-tano-rockchip"
#PREFERRED_VERSION_linux-tano-rockchip = "4.19%"
#LINUXLIBCVERSION = "4.19-custom%"
#TANOWRT_WIREGUARD_IN_KERNEL = "1"

# 4.19-rt
#PREFERRED_PROVIDER_virtual/kernel = "linux-tano-rockchip-rt"
#PREFERRED_VERSION_linux-tano-rockchip-rt = "4.19%"
#LINUXLIBCVERSION = "4.19-custom%"
#TANOWRT_WIREGUARD_IN_KERNEL = "1"

# 5.10
PREFERRED_PROVIDER_virtual/kernel = "linux-tano-rockchip"
PREFERRED_VERSION_linux-tano-rockchip = "5.10%"
LINUXLIBCVERSION = "5.10-custom%"
TANOWRT_WIREGUARD_IN_KERNEL = "1"
