#
# SPDX-License-Identifier: MIT
# Copyright (c) 2022 Tano Systems LLC. All rights reserved.
#

require sama5d2.inc
require swu-atmel.inc

MACHINE_FEATURES = "\
	apm \
	alsa \
	vfat \
	ext2 \
	ext4 \
	usbhost \
	usbgadget \
	ethernet \
	screen \
	cgroup \
	touchscreen \
	lldp \
	watchdog \
	crypto \
	swupdate \
"

#
# Kernel
#
KERNEL_IMAGETYPE = "fitImage"
KERNEL_CLASSES = "kernel-tn-fitimage"

KERNEL_ENTRYPOINT = "0x22000000"
KERNEL_LOADADDRESS = "0x22000000"

#
# Bootloader
#
UBOOT_MACHINE = ""

UBOOT_ENTRYPOINT = "0x20008000"
UBOOT_LOADADDRESS = "0x20008000"
UBOOT_ENV_SIZE = "0x20000"
UBOOT_ENV_REDUNDANT = "1"

UBOOT_DTB_LOADADDRESS  = "0x23e00000"
UBOOT_DTBO_LOADADDRESS = "0x23f00000"
UBOOT_DTBO_OFFSET      = "0x00010000"
UBOOT_RD_LOADADDRESS   = "0x25000000"
UBOOT_RD_ENTRYPOINT    = "0x25000000"

UBOOT_INITIAL_ENV = "u-boot-initial-env"

AT91BOOTSTRAP_MACHINE = ""

#
# Image
#
ROOTFS_IMAGE_FSTYPE = "squashfs-lzo"
IMAGE_FSTYPES = "${ROOTFS_IMAGE_FSTYPE}"

# used by sysvinit_2
SERIAL_CONSOLES = "115200;ttyS0"

#
# Kernel
#
KERNEL_DEVICETREE = "\
	at91-sama5d2_xplained.dtb \
"

#
# OpenWrt specific
#
OPENWRT_VERSION_PRODUCT = "Atmel SAMA5D2 Xplained"
OPENWRT_VERSION_HWREV = "1.0"
