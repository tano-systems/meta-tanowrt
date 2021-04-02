#
# SPDX-License-Identifier: MIT
# Copyright (c) 2021 Tano Systems LLC. All rights reserved.
#

#
# SWUpdate
#
SWU_BOOTLOADER = "uboot"

SWU_IMAGE_EXTRA_ARTIFACTS += "at91bootstrap uboot startup kernel"

SWU_IMAGE_ARTIFACT_at91bootstrap[fname] = "at91bootstrap"
SWU_IMAGE_ARTIFACT_at91bootstrap[depends] = "at91bootstrap:do_deploy"
SWU_IMAGE_ARTIFACT_at91bootstrap[verextract] = "1"
SWU_IMAGE_ARTIFACT_at91bootstrap[verextract-cmd] = "strings"
SWU_IMAGE_ARTIFACT_at91bootstrap[verextract-filter-cmd] = "grep 'AT91Bootstrap' | awk '{ print $2 }'"

SWU_IMAGE_ARTIFACT_uboot[fname] = "u-boot-${MACHINE}"
SWU_IMAGE_ARTIFACT_uboot[depends] = "virtual/bootloader:do_deploy"
SWU_IMAGE_ARTIFACT_uboot[verextract] = "1"
SWU_IMAGE_ARTIFACT_uboot[verextract-cmd] = "strings"
SWU_IMAGE_ARTIFACT_uboot[verextract-filter-cmd] = "grep -m 2 'U-Boot 20' | tail -n1 | awk '{print $2}'"

SWU_IMAGE_ARTIFACT_startup[fname] = "startup"
SWU_IMAGE_ARTIFACT_startup[ftype] = "img"
SWU_IMAGE_ARTIFACT_startup[depends] = "u-boot-startup-script:do_deploy"
SWU_IMAGE_ARTIFACT_startup[verextract] = "1"
SWU_IMAGE_ARTIFACT_startup[verextract-input] = "${DEPLOY_DIR_IMAGE}/startup-${MACHINE}.img.version"

SWU_IMAGE_ARTIFACT_kernel[fname] = "fitImage"
SWU_IMAGE_ARTIFACT_kernel[depends] = "virtual/kernel:do_deploy"
SWU_IMAGE_ARTIFACT_kernel[verextract] = "1"
SWU_IMAGE_ARTIFACT_kernel[verextract-cmd] = "strings"
SWU_IMAGE_ARTIFACT_kernel[verextract-filter-cmd] = "grep -e 'U-Boot fitImage for TanoWrt/.*/${MACHINE}' | awk -F '/' '{ print $2 }'"

SWU_IMAGE_ARTIFACT_rootfs[sw-desc-type] = "raw"
