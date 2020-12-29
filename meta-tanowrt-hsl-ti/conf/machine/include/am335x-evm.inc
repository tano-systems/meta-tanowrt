#
# SPDX-License-Identifier: MIT
# Copyright (c) 2020 Tano Systems LLC. All rights reserved.
#

#
# CPU
#
require conf/machine/include/tano-am335x.inc

#
# Graphics
#
PREFERRED_PROVIDER_virtual/xserver = "xserver-xorg"

# For built-in LCD, add xf86-input-tslib
XSERVER = "xserver-xorg \
           xf86-input-evdev \
           xf86-input-mouse \
           xf86-video-fbdev \
           xf86-input-keyboard"

# Default to external video, change to smallscreen for built-in LCD
MACHINE_GUI_CLASS = "bigscreen"

#
# Image
#

# Remove /boot directory from image
ROOTFS_RM_BOOT_DIR_DISABLE = "0"
ROOTFS_RM_BOOT_DIR_KEEP_DTB = "0"

# Install device tree files
IMAGE_INSTALL_append = " kernel-devicetree "

#
# Kernel
#
PREFERRED_PROVIDER_virtual/kernel = "${@oe.utils.conditional('TANOWRT_HSL_TI_SDK_KERNEL', '1', 'linux-tano-ti-sdk-rt', 'linux-tano-ti-staging-rt', d)}"
PREFERRED_VERSION_linux-tano-ti-staging-rt = "5.4%"
PREFERRED_VERSION_linux-tano-ti-sdk-rt = "4.19%"
LINUXLIBCVERSION = "${@oe.utils.conditional('TANOWRT_HSL_TI_SDK_KERNEL', '1', 'ti-4.19%', 'ti-5.4%', d)}"
SDKLINUXLIBCVERSION = "${@oe.utils.conditional('TANOWRT_HSL_TI_SDK_KERNEL', '1', 'ti-4.19%', 'ti-5.4%', d)}"
PREFERRED_VERSION_xtables-addons = "${@oe.utils.conditional('TANOWRT_HSL_TI_SDK_KERNEL', '1', '3.3', '3.9', d)}"

#
# Bootloader
#
PREFERRED_PROVIDER_virtual/bootloader = "u-boot-ti-staging"
PREFERRED_PROVIDER_virtual/bootloader_beaglebone-black = "u-boot-ti-staging"
PREFERRED_PROVIDER_u-boot = "u-boot-ti-staging"
PREFERRED_PROVIDER_u-boot_beaglebone-black = "u-boot-ti-staging"
PREFERRED_VERSION_u-boot-ti-staging = "2020.01%"

UBOOT_ARCH = "arm"
UBOOT_ENTRYPOINT = "0x80008000"
UBOOT_LOADADDRESS = "0x80008000"
UBOOT_MACHINE = "am335x_evm_defconfig"

EXTRA_IMAGEDEPENDS += "u-boot"

#
# QEMU options (runqemu)
#

# No qemu support, yet
MACHINE_FEATURES_remove = "qemu-usermode"
QEMU_TARGETS = "arm"
