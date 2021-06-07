#
# SPDX-License-Identifier: MIT
# Copyright (c) 2021 Tano Systems LLC. All rights reserved.
#

require conf/machine/include/baikal-m.inc

MACHINEOVERRIDES =. "baikal-be-m1000:"

#
# Image generation
#

#
# Kernel
#
PREFERRED_PROVIDER_virtual/kernel = "linux-tano-baikal"
PREFERRED_VERSION_linux-tano-baikal = "5.4%"
LINUXLIBCVERSION = "5.4%"
SDKLINUXLIBCVERSION = "5.4%"

KERNEL_IMAGETYPE = "Image"

KERNEL_CLASSES = "kernel-tn-mkimage"
KERNEL_MKIMAGE_TYPES = "ext4"
KERNEL_MKIMAGE_INCLUDE_DEVICETREE = "1"

KERNEL_DEFCONFIG ?= "defconfig"

#
# Other
#

# Enable irqbalance
IRQBALANCE_CONFIG_ENABLED = "1"
MACHINE_EXTRA_RDEPENDS_append = " irqbalance "

# RTC hwclock parameters
OPENWRT_HWCLOCK_DEV       = "/dev/rtc0"
OPENWRT_HWCLOCK_LOCALTIME = "1"
