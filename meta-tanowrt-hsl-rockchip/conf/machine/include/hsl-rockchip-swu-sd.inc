#
# SPDX-License-Identifier: MIT
# Copyright (c) 2022 Tano Systems LLC. All rights reserved.
#

#
# SWUpdate
#

OPENWRT_VERSION_PRODUCT_EXTRA = " (SD)"

WKS_IMAGE_IDBLOCK_BINARY = "${RK_IMAGE_IDBLOCK}-${MACHINE}-sdcard"
WKS_IMAGE_UBOOT_INITIAL_ENV_BINARY = "u-boot-initial-env-${MACHINE}-sdcard.bin"

SWU_OVERLAY_CONTAINER = "blkdev"
SWU_OVERLAY_FS = "ext4"

SWU_IMAGE_DEV ?= "/dev/mmcblk1"
SWU_IMAGE_MEDIA_TYPE ?= "SD Card"

SWU_IMAGE_ARTIFACT_idblock[fname] = "idblock.img-${MACHINE}-sdcard"
SWU_IMAGE_ARTIFACT_idblock[ftype] = ""
SWU_IMAGE_ARTIFACT_idblock[sw-desc-type] = "raw"
SWU_IMAGE_ARTIFACT_idblock[sw-desc-device] = "${SWU_IMAGE_DEV}"
SWU_IMAGE_ARTIFACT_idblock[sw-desc-offset] = "${RK_IDBLOCK_OFFSET}"

SWU_IMAGE_ARTIFACT_uboot[ftype] = "bin-sdcard"
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
