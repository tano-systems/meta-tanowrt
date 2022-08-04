#
# SPDX-License-Identifier: MIT
# Copyright (c) 2022 Tano Systems LLC. All rights reserved.
#

#
# SWUpdate
#
SWU_OVERLAY_CONTAINER = "blkdev"
SWU_OVERLAY_FS = "ext4"

SWU_IMAGE_DEV = "/dev/mmcblk1"
SWU_IMAGE_BOOT_DEV = "/dev/mtdblock0"
SWU_IMAGE_MEDIA_TYPE = "eMMC"

SWU_IMAGE_ARTIFACT_at91bootstrap[ftype] = "bin-emmc"
SWU_IMAGE_ARTIFACT_at91bootstrap[sw-desc-type] = "raw"
SWU_IMAGE_ARTIFACT_at91bootstrap[sw-desc-path] = ""
SWU_IMAGE_ARTIFACT_at91bootstrap[sw-desc-device] = "${SWU_IMAGE_BOOT_DEV}"
SWU_IMAGE_ARTIFACT_at91bootstrap[sw-desc-offset] = "0"

SWU_IMAGE_ARTIFACT_uboot[ftype] = "bin-emmc"
SWU_IMAGE_ARTIFACT_uboot[sw-desc-type] = "rawfile"
SWU_IMAGE_ARTIFACT_uboot[sw-desc-path] = "/boot/u-boot.bin"
SWU_IMAGE_ARTIFACT_uboot[sw-desc-device] = "/dev/null"
SWU_IMAGE_ARTIFACT_uboot[sw-desc-offset] = "0"

SWU_IMAGE_ARTIFACT_startup[sw-desc-type] = "raw"
SWU_IMAGE_ARTIFACT_startup[sw-desc-device] = "${SWU_IMAGE_DEV}"
SWU_IMAGE_ARTIFACT_startup[sw-desc-offset] = "262144"

SWU_IMAGE_ARTIFACT_kernel[ftype] = "ext4"
SWU_IMAGE_ARTIFACT_kernel[sw-desc-device-a] = "${SWU_IMAGE_DEV}p2"
SWU_IMAGE_ARTIFACT_kernel[sw-desc-device-b] = "${SWU_IMAGE_DEV}p5"
SWU_IMAGE_ARTIFACT_kernel[sw-desc-type] = "raw"

SWU_IMAGE_ARTIFACT_rootfs[sw-desc-device-a] = "${SWU_IMAGE_DEV}p3"
SWU_IMAGE_ARTIFACT_rootfs[sw-desc-device-b] = "${SWU_IMAGE_DEV}p6"

SWU_INSTALL_EXTRA_RDEPENDS += "gptfdisk parted mmc-utils"
