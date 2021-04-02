#
# SPDX-License-Identifier: MIT
# Copyright (c) 2021 Tano Systems LLC. All rights reserved.
#

#
# SWUpdate
#
SWU_OVERLAY_CONTAINER = "ubivol"
SWU_OVERLAY_FS = "ubifs"

SWU_UBI_MTD_BLACKLIST = "0 1 2 3 6"

# Extra dependencies for factory installation script
SWU_INSTALL_EXTRA_RDEPENDS = "\
	mtd-utils \
	mtd-utils-ubifs \
"

SWU_IMAGE_MEDIA_TYPE = "NAND"

SWU_IMAGE_ARTIFACT_at91bootstrap[ftype] = "bin-nand-pmecc"
SWU_IMAGE_ARTIFACT_at91bootstrap[sw-desc-type] = "raw"
SWU_IMAGE_ARTIFACT_at91bootstrap[sw-desc-path] = "/"
SWU_IMAGE_ARTIFACT_at91bootstrap[sw-desc-device] = "/dev/mtdblock0"
SWU_IMAGE_ARTIFACT_at91bootstrap[sw-desc-offset] = "0"

SWU_IMAGE_ARTIFACT_uboot[ftype] = "bin-nand"
SWU_IMAGE_ARTIFACT_uboot[sw-desc-type] = "raw"
SWU_IMAGE_ARTIFACT_uboot[sw-desc-path] = "/"
SWU_IMAGE_ARTIFACT_uboot[sw-desc-device] = "/dev/mtdblock1"
SWU_IMAGE_ARTIFACT_uboot[sw-desc-offset] = "0"

SWU_IMAGE_ARTIFACT_startup[sw-desc-type] = "raw"
SWU_IMAGE_ARTIFACT_startup[sw-desc-device] = "/dev/mtdblock2"
SWU_IMAGE_ARTIFACT_startup[sw-desc-offset] = "0"

SWU_IMAGE_ARTIFACT_kernel[ftype] = "bin"
SWU_IMAGE_ARTIFACT_kernel[sw-desc-type] = "ubivol"
SWU_IMAGE_ARTIFACT_kernel[sw-desc-device-a] = "mtd4"
SWU_IMAGE_ARTIFACT_kernel[sw-desc-device-b] = "mtd5"
SWU_IMAGE_ARTIFACT_kernel[sw-desc-extra] = "\
	volume = "kernel"; \
	data = "dynamic"; \
	properties: { \
		volume-id = "0"; \
		always-remove = "true"; \
		auto-resize = "true"; \
	} \
"

SWU_IMAGE_ARTIFACT_rootfs[sw-desc-type] = "ubivol"
SWU_IMAGE_ARTIFACT_rootfs[sw-desc-device-a] = "mtd4"
SWU_IMAGE_ARTIFACT_rootfs[sw-desc-device-b] = "mtd5"
SWU_IMAGE_ARTIFACT_rootfs[sw-desc-extra] = "\
	volume = "rootfs"; \
	data = "dynamic"; \
	properties: { \
		volume-id = "1"; \
		always-remove = "true"; \
		auto-resize = "true"; \
	} \
"
