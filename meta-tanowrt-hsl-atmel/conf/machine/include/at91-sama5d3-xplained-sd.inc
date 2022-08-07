#
# SPDX-License-Identifier: MIT
# Copyright (c) 2022 Tano Systems LLC. All rights reserved.
#

require swu-atmel-sd.inc

SWU_IMAGE_DEV = "/dev/mmcblk1"

#
# Bootloader
#
UBOOT_CONFIG = "sdcard"

AT91BOOTSTRAP_BUILD_CONFIG = "sdcard"
AT91BOOTSTRAP_BUILD_CONFIG[sdcard] = "sd_uboot_defconfig"

#
# Kernel
#
KERNEL_CLASSES += "kernel-tn-mkimage"
KERNEL_MKIMAGE_TYPES = "ext4"

#
# Image
#
IMAGE_CLASSES += "wic-sdcard-img"
IMAGE_FSTYPES += "wic"
WKS_FILE = "sdimage-at91-swu-a-b.wks.in"
WIC_CREATE_EXTRA_ARGS = "--no-fstab-update"

IMAGE_BOOT_FILES = "\
	boot.bin-sdcard;boot.bin \
	u-boot.bin-sdcard;u-boot.bin \
"

PACKAGECONFIG_append_pn-fstools = " ovl-rootdisk-part "

TANOWRT_ENABLE_OVERLAY_RESIZE = "1"

WKS_IMAGE_KERNEL_BINARY = "fitImage-${MACHINE}.ext4"
WKS_IMAGE_ROOTFS_BINARY = "${IMAGE_BASENAME}-${MACHINE}.${ROOTFS_IMAGE_FSTYPE}"
WKS_IMAGE_STARTUP_BINARY = "startup-${MACHINE}.img"
WKS_IMAGE_UBOOT_INITIAL_ENV_BINARY = "u-boot-initial-env-${MACHINE}-sdcard.bin"

WKS_PART_SIZE_MIB_KERNEL = "64"
WKS_PART_SIZE_MIB_ROOTFS = "384"

WIC_DO_IMAGE_DEPENDS = "\
	${IMAGE_BASENAME}:do_image_squashfs_lzo \
	at91bootstrap:do_deploy \
	u-boot-startup-script:do_deploy \
	virtual/bootloader:do_deploy \
	virtual/kernel:do_deploy \
"
do_image_wic[depends] += "${WIC_DO_IMAGE_DEPENDS}"

#
# OpenWrt specific
#
OPENWRT_VERSION_PRODUCT_append = " (SD)"
