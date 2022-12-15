#
# SPDX-License-Identifier: MIT
# Copyright (c) 2020 Tano Systems LLC. All rights reserved.
#

require conf/machine/include/am574x-evm.inc

#
# Machine/distro features
#
MACHINEOVERRIDES =. "am574x-idk:"

MACHINE_FEATURES = "\
	apm usbgadget usbhost vfat ext2 ext4 screen \
	touchscreen graphics gpu mmip dsp gc320 pci \
	sgx ethernet cgroup lldp stp watchdog \
	mbusgw crypto swupdate \
"

MACHINE_EXTRA_RDEPENDS += "\
	${@bb.utils.contains('MACHINE_FEATURES', 'mmip', 'ipumm-fw', '', d)} \
	ffmpeg \
"

# Enable tty1
PROCD_ENABLED_TTYS ?= "1"

#
# Graphics
#
MACHINE_GUI_CLASS = "smallscreen"

#
# Kernel
#
ENABLE_TI_UIO_DEVICES ?= "0"

KERNEL_IMAGETYPE = "fitImage"
KERNEL_CLASSES = "kernel-tn-fitimage kernel-tn-mkimage"
KERNEL_MKIMAGE_TYPES = "ext4"
KERNEL_DEVICETREE = " \
	am57xx-evm.dtb \
	am57xx-beagle-x15.dtb \
	am57xx-beagle-x15-revb1.dtb \
	am57xx-beagle-x15-revc.dtb \
	am571x-idk.dtb \
	am572x-idk.dtb \
	am574x-idk.dtb \
"

KERNEL_DEVICETREE += "${@oe.utils.conditional('ENABLE_TI_UIO_DEVICES', '1', 'am574x-idk-pru-excl-uio.dtb am572x-idk-pru-excl-uio.dtb am571x-idk-pru-excl-uio.dtb', '', d)}"

SERIAL_CONSOLES = "115200;ttyS2"

# Use SDK kernel
TANOWRT_HSL_TI_SDK_KERNEL ?= "1"

#
# Bootloader
#
UBOOT_MACHINE = "am57xx_evm_defconfig"

UBOOT_DTB_LOADADDRESS  = "0x87e00000"
UBOOT_DTBO_LOADADDRESS = "0x87f00000"
UBOOT_DTBO_OFFSET      = "0x00010000"
UBOOT_RD_LOADADDRESS   = "0x84000000"
UBOOT_RD_ENTRYPOINT    = "0x84000000"

UBOOT_ENV_SIZE      = "0x20000"
UBOOT_ENV_REDUNDANT = "1"

UBOOT_SPL_MULTIPLE_COUNT_SD = "3"
UBOOT_SPL_MULTIPLE_COUNT_EMMC = "4"

UBOOT_SPL_MULTIPLE = "1"
UBOOT_SPL_MULTIPLE_COUNT = "${UBOOT_SPL_MULTIPLE_COUNT_SD} ${UBOOT_SPL_MULTIPLE_COUNT_EMMC}"
UBOOT_SPL_MULTIPLE_BLOCK_SIZE = "131072"

UBOOT_INITIAL_ENV = "u-boot-initial-env"

#
# Image
#
ROOTFS_IMAGE_FSTYPE = "squashfs-lzo"

IMAGE_FSTYPES = "${ROOTFS_IMAGE_FSTYPE}"

PACKAGECONFIG:append:pn-fstools = " ovl-f2fs ovl-rootdisk-part "

IMAGE_FEATURES += "splash"
SPLASH = "psplash"

IMAGE_FEATURES:remove = "modem"

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
OPENWRT_VERSION_PRODUCT = "AM574x IDK EVM"
OPENWRT_VERSION_HWREV = "${SWU_REVISION}"

#
# Other
#

# Refine the list of device targets for ti-pdk class recipes
# Set the list of device targets for ti-pdk class recipes
TI_PDK_LIMIT_SOCS = "am571x am572x am574x"
TI_PDK_LIMIT_BOARDS = "evmAM571x evmAM572x idkAM571x idkAM572x idkAM574x"
