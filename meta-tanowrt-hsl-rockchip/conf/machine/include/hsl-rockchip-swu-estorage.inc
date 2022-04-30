#
# SPDX-License-Identifier: MIT
# Copyright (c) 2022 Tano Systems LLC. All rights reserved.
#

#
# SWUpdate
#
SWU_UBOOT_CONFIG ?= "emmc"
SWU_UBOOT_CONFIG_FACTORY ?= "sdcard"
SWU_IMAGE_DEV ?= "/dev/mmcblk0"
SWU_IMAGE_MEDIA_TYPE ?= "eMMC"
SWU_OVERLAY_CONTAINER ?= "blkdev"
SWU_OVERLAY_FS ?= "ext4"

OPENWRT_VERSION_PRODUCT_EXTRA = " (eMMC)"

MACHINE_FEATURES_append = " swupdate-factory"

WKS_IMAGE_IDBLOCK_BINARY = "${RK_IMAGE_IDBLOCK}-${MACHINE}-${SWU_UBOOT_CONFIG}"
WKS_IMAGE_UBOOT_INITIAL_ENV_BINARY = "u-boot-initial-env-${MACHINE}-${SWU_UBOOT_CONFIG}.bin"

# Do not need auto overlay resizing for eMMC
TANOWRT_ENABLE_OVERLAY_RESIZE = "0"

#
# Initramfs (used for factory installation image)
#
INITRAMFS_IMAGE = "tanowrt-image-initramfs-swu-factory"
INITRAMFS_IMAGE_NAME = "${INITRAMFS_IMAGE}-${MACHINE}"
INITRAMFS_IMAGE_BUNDLE = "0"

SWU_INSTALL_EXTRA_RDEPENDS += "gptfdisk parted mmc-utils"

SWU_IMAGE_ARTIFACT_idblock[fname] = "idblock.img-${MACHINE}-${SWU_UBOOT_CONFIG}"
SWU_IMAGE_ARTIFACT_idblock[ftype] = ""
SWU_IMAGE_ARTIFACT_idblock[sw-desc-type] = "raw"
SWU_IMAGE_ARTIFACT_idblock[sw-desc-device] = "${SWU_IMAGE_DEV}"
SWU_IMAGE_ARTIFACT_idblock[sw-desc-offset] = "${RK_IDBLOCK_OFFSET}"

SWU_IMAGE_ARTIFACT_uboot[ftype] = "bin-${SWU_UBOOT_CONFIG}"
SWU_IMAGE_ARTIFACT_uboot[sw-desc-type] = "raw"
SWU_IMAGE_ARTIFACT_uboot[sw-desc-device] = "${SWU_IMAGE_DEV}p1"

SWU_IMAGE_ARTIFACT_startup[sw-desc-type] = "raw"
SWU_IMAGE_ARTIFACT_startup[sw-desc-device] = "${SWU_IMAGE_DEV}"
SWU_IMAGE_ARTIFACT_startup[sw-desc-offset] = "${RK_STARTUP_OFFSET}"

SWU_IMAGE_ARTIFACT_kernel[ftype] = "ext4"
SWU_IMAGE_ARTIFACT_kernel[sw-desc-device-a] = "${SWU_IMAGE_DEV}p2"
SWU_IMAGE_ARTIFACT_kernel[sw-desc-device-b] = "${SWU_IMAGE_DEV}p4"
SWU_IMAGE_ARTIFACT_kernel[sw-desc-type] = "raw"

SWU_IMAGE_ARTIFACT_rootfs[sw-desc-device-a] = "${SWU_IMAGE_DEV}p3"
SWU_IMAGE_ARTIFACT_rootfs[sw-desc-device-b] = "${SWU_IMAGE_DEV}p5"


# Factory image creation
SWU_FACTORY_WKS_FILE ?= "sdimage-rockchip-swu-factory.wks.in"
SWU_FACTORY_EXTRA_DEPENDS += "\
	u-boot-startup-script:do_deploy \
	virtual/bootloader:do_deploy \
	virtual/kernel:do_deploy \
"
SWU_FACTORY_EXTRA_FILES = "\
	u-boot-initial-env-${SWU_UBOOT_CONFIG}.bin;u-boot-initial-env.bin \
"

# These variables are used in sdimage-rockchip-swu-factory.wks.in
WKS_FACTORY_IMAGE_IDBLOCK_BINARY = "idblock.img-${MACHINE}-${SWU_UBOOT_CONFIG_FACTORY}"
WKS_FACTORY_IMAGE_STARTUP_BINARY = "startup-factory-${MACHINE}.img"
WKS_FACTORY_IMAGE_KERNEL_BINARY = "fitImage-${INITRAMFS_IMAGE}-${MACHINE}.ext4"
WKS_FACTORY_IMAGE_UBOOT_BINARY = "${UBOOT_BINARY}-${SWU_UBOOT_CONFIG_FACTORY}"
WKS_FACTORY_IMAGE_UBOOT_INITIAL_ENV_BINARY = "u-boot-initial-env-${MACHINE}-${SWU_UBOOT_CONFIG_FACTORY}.bin"
