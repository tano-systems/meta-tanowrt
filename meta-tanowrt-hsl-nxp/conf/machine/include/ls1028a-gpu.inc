#
# SPDX-License-Identifier: MIT
# Copyright (c) 2020 Tano Systems LLC. All rights reserved.
#
MACHINEOVERRIDES =. "imxgpu:imxgpu2d:imxgpu3d:imxdrm:"

PREFERRED_PROVIDER_virtual/xserver = "xserver-xorg"
XSERVER_DRIVER_imxgpu2d = "xf86-video-imx-vivante"

# Graphics libraries
PREFERRED_PROVIDER_virtual/egl           ?= "imx-gpu-viv"
PREFERRED_PROVIDER_virtual/libgl         ?= "imx-gpu-viv"
PREFERRED_PROVIDER_virtual/libgles1      ?= "imx-gpu-viv"
PREFERRED_PROVIDER_virtual/libgles2      ?= "imx-gpu-viv"
PREFERRED_PROVIDER_virtual/libgbm        ?= "imx-gpu-viv"
PREFERRED_PROVIDER_virtual/libg2d        ?= "imx-gpu-g2d"
PREFERRED_PROVIDER_virtual/libg2d_imxdpu ?= "imx-dpu-g2d"

PREFERRED_VERSION_libdrm = "2.4.99.imx"
PREFERRED_VERSION_wayland-protocols = "1.18.imx"

MACHINE_HAS_VIVANTE_KERNEL_DRIVER_SUPPORT = "1"

#PREFERRED_PROVIDER_virtual/egl_imxgpu        ?= "imx-gpu-viv"
#PREFERRED_PROVIDER_virtual/libgl_imxgpu3d    ?= "imx-gpu-viv"
#PREFERRED_PROVIDER_virtual/libgles1_imxgpu3d ?= "imx-gpu-viv"
#PREFERRED_PROVIDER_virtual/libgles2_imxgpu3d ?= "imx-gpu-viv"

# Graphics
#XSERVER += "xf86-input-tslib"
MACHINE_GUI_CLASS = "largescreen"
QT_PROVIDER = "qt5"

#
# Graphics (Qt5) configuration
#
#PREFERRED_PROVIDER_virtual/egl      = "mesa-gl"
#PREFERRED_PROVIDER_virtual/libgl    = "mesa-gl"
#PREFERRED_PROVIDER_virtual/libgles1 = "mesa-gl"
#PREFERRED_PROVIDER_virtual/libgles2 = "mesa-gl"

#PREFERRED_PROVIDER_virtual/egl = "ti-sgx-ddk-um"
#PREFERRED_PROVIDER_virtual/libgles1 = "ti-sgx-ddk-um"
#PREFERRED_PROVIDER_virtual/libgles2 = "ti-sgx-ddk-um"
#PREFERRED_PROVIDER_virtual/libgl = "mesa-gl"
#PREFERRED_PROVIDER_libgbm = "libgbm"
#PREFERRED_PROVIDER_libgbm-dev = "libgbm-dev"

# Configure Qt5 flags (GLES is still required for qtdeclarative and qtwebkit)
#PACKAGECONFIG_GL:pn-qtbase = "gles2 linuxfb"
#PACKAGECONFIG_DISTRO:pn-qtbase = "icu accessibility"
#PACKAGECONFIG_FONTS:pn-qtbase = "fontconfig"

# Add gstreamer in package configuration to enable video
#PACKAGECONFIG:append:pn-qtmultimedia  = " gstreamer"
