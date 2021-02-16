#
# SPDX-License-Identifier: MIT
# Copyright (c) 2021 Tano Systems LLC. All rights reserved.
#

SWU_IMAGE_MMCDEV ?= "/dev/mmcblk0"
SWU_IMAGE_MEDIA_TYPE ?= "SD Card"

SWU_IMAGE_EXTRA_ARTIFACTS += "kernel uboot spl startup"

SWU_IMAGE_ARTIFACT_rootfs[sw-desc-type] = "raw"
SWU_IMAGE_ARTIFACT_rootfs[sw-desc-device-a] = "${SWU_IMAGE_MMCDEV}p2"
SWU_IMAGE_ARTIFACT_rootfs[sw-desc-device-b] = "${SWU_IMAGE_MMCDEV}p4"

SWU_IMAGE_ARTIFACT_kernel[fname] = "fitImage"
SWU_IMAGE_ARTIFACT_kernel[ftype] = "ext4"
SWU_IMAGE_ARTIFACT_kernel[depends] = "virtual/kernel:do_deploy"
SWU_IMAGE_ARTIFACT_kernel[verextract] = "1"
SWU_IMAGE_ARTIFACT_kernel[verextract-cmd] = "strings"
SWU_IMAGE_ARTIFACT_kernel[verextract-filter-cmd] = "grep -e 'U-Boot fitImage for TanoWrt/.*/${MACHINE}' | awk -F '/' '{ print $2 }'"
SWU_IMAGE_ARTIFACT_kernel[sw-desc-type] = "raw"
SWU_IMAGE_ARTIFACT_kernel[sw-desc-device-a] = "${SWU_IMAGE_MMCDEV}p1"
SWU_IMAGE_ARTIFACT_kernel[sw-desc-device-b] = "${SWU_IMAGE_MMCDEV}p3"

SWU_IMAGE_ARTIFACT_spl[fname] = "MLO-x${UBOOT_SPL_MULTIPLE_COUNT}"
SWU_IMAGE_ARTIFACT_spl[ftype] = ""
SWU_IMAGE_ARTIFACT_spl[depends] = "virtual/bootloader:do_deploy"
SWU_IMAGE_ARTIFACT_spl[sw-desc-type] = "raw"
SWU_IMAGE_ARTIFACT_spl[sw-desc-device] = "${SWU_IMAGE_MMCDEV}"
SWU_IMAGE_ARTIFACT_spl[sw-desc-offset] = "131072"

SWU_IMAGE_ARTIFACT_uboot[fname] = "u-boot-${MACHINE}"
SWU_IMAGE_ARTIFACT_uboot[ftype] = "img"
SWU_IMAGE_ARTIFACT_uboot[depends] = "virtual/bootloader:do_deploy"
SWU_IMAGE_ARTIFACT_uboot[verextract] = "1"
SWU_IMAGE_ARTIFACT_uboot[verextract-cmd] = "strings"
SWU_IMAGE_ARTIFACT_uboot[verextract-filter-cmd] = "grep -m 2 'U-Boot 20' | tail -n1 | awk '{print $2}'"
SWU_IMAGE_ARTIFACT_uboot[sw-desc-type] = "raw"
SWU_IMAGE_ARTIFACT_uboot[sw-desc-device] = "${SWU_IMAGE_MMCDEV}"
SWU_IMAGE_ARTIFACT_uboot[sw-desc-offset] = "524288"

SWU_IMAGE_ARTIFACT_startup[fname] = "startup"
SWU_IMAGE_ARTIFACT_startup[ftype] = "img"
SWU_IMAGE_ARTIFACT_startup[depends] = "u-boot-startup-script:do_deploy"
SWU_IMAGE_ARTIFACT_startup[verextract] = "1"
SWU_IMAGE_ARTIFACT_startup[verextract-input] = "${DEPLOY_DIR_IMAGE}/startup-${MACHINE}.img.version"
SWU_IMAGE_ARTIFACT_startup[sw-desc-type] = "raw"
SWU_IMAGE_ARTIFACT_startup[sw-desc-device] = "${SWU_IMAGE_MMCDEV}"
SWU_IMAGE_ARTIFACT_startup[sw-desc-offset] = "2621440"
