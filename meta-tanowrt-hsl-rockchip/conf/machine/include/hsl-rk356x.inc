# Copyright (c) 2020, Rockchip Electronics Co., Ltd
# Copyright (c) 2022, Tano Systems LLC
# Released under the MIT license (see COPYING.MIT for the terms)

SOC_FAMILY ?= "rk3568"

MACHINE_FEATURES_append = "\
	pci \
"

# OpenEmbedded core layer on dunfell branch are not support Cortex-A55.
# Use Cortex-A53 instead because it is a predecessor.
require conf/machine/include/tune-cortexa53.inc
require conf/machine/include/hsl-rockchip-arm64-common.inc

PREFERRED_PROVIDER_virtual/egl ?= "rockchip-libmali"
PREFERRED_PROVIDER_virtual/libgles1 ?= "rockchip-libmali"
PREFERRED_PROVIDER_virtual/libgles2 ?= "rockchip-libmali"
PREFERRED_PROVIDER_virtual/libgles3 ?= "rockchip-libmali"
PREFERRED_PROVIDER_virtual/libopencl ?= "rockchip-libmali"
PREFERRED_PROVIDER_virtual/libgbm ?= "rockchip-libmali"
PACKAGECONFIG_pn-wayland ?= "${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'no-egl', '', d)}"

DEPENDS_append_pn-mesa-gl += " rockchip-libmali"

PREFERRED_PROVIDER_virtual/libgl ?= "mesa-gl"
PREFERRED_PROVIDER_virtual/mesa ?= "mesa-gl"

MALI_GPU := "bifrost-g52"
MALI_VERSION := "g2p0"

RK_ISP_VERSION := "2.1"

#PREFERRED_VERSION_linux-rockchip := "4.19%"
#LINUXLIBCVERSION := "4.19-custom%"

# 4.19
PREFERRED_PROVIDER_virtual/kernel = "linux-tano-rockchip"
PREFERRED_VERSION_linux-tano-rockchip = "4.19%"
LINUXLIBCVERSION = "4.19-custom%"
TANOWRT_WIREGUARD_IN_KERNEL = "1"

# 4.19-rt
#PREFERRED_PROVIDER_virtual/kernel = "linux-tano-rockchip-rt"
#PREFERRED_VERSION_linux-tano-rockchip-rt = "4.19%"
#LINUXLIBCVERSION = "4.19-custom%"
#TANOWRT_WIREGUARD_IN_KERNEL = "1"
