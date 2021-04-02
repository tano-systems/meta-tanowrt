#
# SPDX-License-Identifier: MIT
# Copyright (c) 2021 Tano Systems LLC. All rights reserved.
#

require swu-atmel-nand.inc

MACHINE_FEATURES += "swupdate-factory"

#
# Bootloader
#
UBOOT_CONFIG = "sdcard nand"

AT91BOOTSTRAP_BUILD_CONFIG = "sdcard nand"
AT91BOOTSTRAP_BUILD_CONFIG[sdcard] = "sd_uboot_defconfig"
AT91BOOTSTRAP_BUILD_CONFIG[nand]   = "nf_uboot_defconfig"

AT91BOOTSTRAP_PMECC_HEADER = "1"
AT91BOOTSTRAP_PMECC_HEADER_BOARD = "sama5d3"

#
# OpenWrt specific
#
OPENWRT_VERSION_PRODUCT_append = " (NAND)"

#
# Initramfs (used for factory installation image)
#
INITRAMFS_IMAGE = "tanowrt-image-initramfs-swu-factory"
INITRAMFS_IMAGE_NAME = "${INITRAMFS_IMAGE}-${MACHINE}"
INITRAMFS_IMAGE_BUNDLE = "0"

# Factory image creation
SWU_FACTORY_WKS_FILE ?= "sdimage-at91-swu-factory.wks.in"
SWU_FACTORY_EXTRA_DEPENDS += "\
	at91bootstrap:do_deploy \
	u-boot-startup-script:do_deploy \
	virtual/bootloader:do_deploy \
	virtual/kernel:do_deploy \
"

SWU_FACTORY_BOOT_FILES = "\
	at91bootstrap.bin-sdcard;boot.bin \
	u-boot.bin-sdcard;u-boot.bin \
	fitImage-${INITRAMFS_IMAGE_NAME}-${MACHINE};fitImage \
"

SWU_FACTORY_EXTRA_FILES = "\
	${SWU_FACTORY_BOOT_FILES} \
	u-boot-initial-env-nand.bin;u-boot-initial-env.bin \
"

# These variables are used in sdimage-at91-swu-factory.wks
WKS_FACTORY_IMAGE_STARTUP_BINARY = "startup-factory.img"
WKS_FACTORY_IMAGE_UBOOT_INITIAL_ENV_BINARY = "u-boot-initial-env-${MACHINE}-sdcard.bin"
