#
# SPDX-License-Identifier: MIT
# Copyright (c) 2020-2021 Tano Systems LLC. All rights reserved.
#

require conf/machine/include/am335x-evm.inc

#
# Machine/distro features
#
MACHINEOVERRIDES =. "am335x-bbb:"

MACHINE_FEATURES = "\
	apm usbgadget usbhost vfat ext2 ext4 screen graphics \
	sgx ethernet wifi cgroup lldp watchdog crypto \
	swupdate \
"

#
# Graphics
#
MACHINE_GUI_CLASS = "bigscreen"

#
# Kernel
#
KERNEL_IMAGETYPE = "fitImage"
KERNEL_DEVICETREE = "am335x-boneblack.dtb"
KERNEL_CLASSES = "kernel-tn-fitimage kernel-tn-mkimage"
KERNEL_MKIMAGE_TYPES = "ext4"

SERIAL_CONSOLE = "115200 ttyS0"

# Use latest staging kernel
TANOWRT_HSL_TI_SDK_KERNEL ?= "0"

#
# Bootloader
#
UBOOT_DTB_LOADADDRESS  = "0x87e00000"
UBOOT_DTBO_LOADADDRESS = "0x87f00000"
UBOOT_DTBO_OFFSET      = "0x00010000"
UBOOT_RD_LOADADDRESS   = "0x84000000"
UBOOT_RD_ENTRYPOINT    = "0x84000000"

UBOOT_ENV_SIZE      = "0x20000"
UBOOT_ENV_REDUNDANT = "1"

UBOOT_SPL_MULTIPLE = "1"
UBOOT_SPL_MULTIPLE_COUNT = "3"
UBOOT_SPL_MULTIPLE_BLOCK_SIZE = "131072"

UBOOT_INITIAL_ENV = "u-boot-initial-env"

#
# Image
#
ROOTFS_IMAGE_FSTYPE = "squashfs-lzo"

IMAGE_FSTYPES = "${ROOTFS_IMAGE_FSTYPE}"

IMAGE_FEATURES += "splash"
SPLASH = "psplash"

PACKAGECONFIG_append_pn-fstools = " ovl-rootdisk-part "

# These variables are used in sdimage-ti-swu-a-b.wks.in
WKS_IMAGE_MLO_BINARY = "MLO"
WKS_IMAGE_UBOOT_BINARY = "u-boot-${MACHINE}.img"
WKS_IMAGE_UBOOT_INITIAL_ENV_BINARY = "u-boot-initial-env-${MACHINE}.bin"
WKS_IMAGE_STARTUP_BINARY = "startup-${MACHINE}.img"
WKS_IMAGE_KERNEL_BINARY = "fitImage-${MACHINE}.ext4"
WKS_IMAGE_ROOTFS_BINARY = "${IMAGE_BASENAME}-${MACHINE}.${ROOTFS_IMAGE_FSTYPE}"

#
# SWUPDATE
#
SWU_BOOTLOADER = "uboot"
SWU_OVERLAY_CONTAINER = "blkdev"
SWU_OVERLAY_FS = "ext4"

require ${TANOWRT_HSL_BASE}/conf/machine/include/swu-ti-common.inc

#
# OpenWrt specific
#
OPENWRT_VERSION_PRODUCT = "BeagleBone Black"
OPENWRT_VERSION_HWREV = "${SWU_REVISION}"

#
# Other
#

# Refine the list of device targets for ti-pdk class recipes
TI_PDK_LIMIT_BOARDS = "bbbAM335x"
