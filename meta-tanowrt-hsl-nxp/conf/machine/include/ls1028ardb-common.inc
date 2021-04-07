#
# SPDX-License-Identifier: MIT
# Copyright (c) 2020-2021 Tano Systems LLC. All rights reserved.
#

#
# Architecture/machine
#
require conf/machine/include/qoriq-arm64.inc
require conf/machine/include/arm/arch-arm64.inc

SERIAL_CONSOLES = "115200;ttyS0 115200;ttyS1 115200;ttyAMA0"
SERIAL_CONSOLES_CHECK = ""

SOC_DEFAULT_IMAGE_FSTYPES = "tar.gz ext4.gz"
MACHINE_FEATURES_remove = "ext2"

MACHINEOVERRIDES =. "ls1028ardb:fsl-lsch3:ls1028a:"

MACHINE_FEATURES += "\
	optee pci usbhost vfat wifi cgroup stp lldp \
	screen crypto hwrng watchdog touchscreen swupdate \
"

#
# Bootloader
#
PREFERRED_PROVIDER_u-boot = "u-boot-openil"
PREFERRED_PROVIDER_virtual/bootloader = "u-boot-openil"

UBOOT_DTB_LOADADDRESS = "0x90000000"
UBOOT_ENTRYPOINT = "0x80080000"

UBOOT_DTBO_LOADADDRESS = "0x97f00000"
UBOOT_DTBO_OFFSET      = "0x00010000"
UBOOT_RD_LOADADDRESS   = "0x94000000"
UBOOT_RD_ENTRYPOINT    = "0x94000000"

UBOOT_ENV_SIZE      = "0x2000"
UBOOT_ENV_REDUNDANT = "1"
UBOOT_INITIAL_ENV = "u-boot-initial-env"

UBOOT_SUFFIX = "bin"
UBOOT_BINARY = "u-boot-${MACHINE}.${UBOOT_SUFFIX}"

#
# Kernel
#
KERNEL_IMAGETYPE ?= "fitImage"

PREFERRED_PROVIDER_virtual/kernel = "linux-tano-openil"

PREFERRED_VERSION_linux-tano-openil = "5.4%"
LINUXLIBCVERSION = "openil-5.4%"
SDKLINUXLIBCVERSION = "openil-5.4%"

KERNEL_IMAGETYPES = "fitImage"
KERNEL_CLASSES = "kernel-tn-fitimage kernel-tn-mkimage"
KERNEL_MKIMAGE_TYPES = "ext4"

KERNEL_DEVICETREE ?= "freescale/fsl-ls1028a-rdb.dtb"
KERNEL_DEFCONFIG ?= "defconfig"

#
# RCW
#
RCW_BINARY_EMMC_BOOT = "rcw/ls1028ardb/R_SQPH_0x85be/rcw_1300_emmcboot.bin"
RCW_BINARY_SD_BOOT = "rcw/ls1028ardb/R_SQPH_0x85be/rcw_1300_sdboot.bin"

RCWSD ?= "R_SQPP_0x85bb/rcw_1500_gpu600"
RCWXSPI ?= "R_SQPP_0x85bb/rcw_1500_gpu600"
RCWEMMC ?= "R_SQPP_0x85bb/rcw_1500_gpu600"

#
# Image
#
EXTRA_IMAGEDEPENDS += "rcw dp-firmware-cadence ppa"
USE_VT = "0"
MACHINE_EXTRA_RRECOMMENDS_remove = "udev-extraconf"

ROOTFS_IMAGE_FSTYPE = "squashfs-lzo"
IMAGE_FSTYPES += "${ROOTFS_IMAGE_FSTYPE}"
MACHINE_EXTRA_RDEPENDS_append = " usbutils-ids "

# Enable irqbalance
IRQBALANCE_CONFIG_ENABLED = "1"
MACHINE_EXTRA_RDEPENDS_append = " irqbalance "

# Add support for Intel Wi-Fi modules
MACHINE_EXTRA_RRECOMMENDS_append = " linux-firmware-iwlwifi "
MACHINE_EXTRA_RDEPENDS_append = " linux-backports "
LINUX_BACKPORTS_CONFIGS = "iwlwifi"

SPLASH = "psplash"
IMAGE_FEATURES += "splash"

# These variables are used in sdimage-ls1028ardb-swu-a-b.wks.in
WKS_IMAGE_UBOOT_BINARY = "${UBOOT_BINARY}"
WKS_IMAGE_DPFW_BINARY = "ls1028a-dp-fw.bin"
WKS_IMAGE_PPAFW_BINARY = "ppa.itb"
WKS_IMAGE_STARTUP_BINARY = "startup-${MACHINE}.img"
WKS_IMAGE_UBOOT_INITIAL_ENV_BINARY = "u-boot-initial-env-${MACHINE}-sdcard-defconfig.bin"
WKS_IMAGE_RCW_BINARY = "rcw.bin"
WKS_IMAGE_KERNEL_BINARY = "fitImage-${MACHINE}.ext4"
WKS_IMAGE_ROOTFS_BINARY = "${IMAGE_BASENAME}-${MACHINE}.${ROOTFS_IMAGE_FSTYPE}"

#
# SWUPDATE
#
SWU_BOOTLOADER = "uboot"
SWU_OVERLAY_CONTAINER = "blkdev"
SWU_OVERLAY_FS = "ext4"

require ${TANOWRT_HSL_BASE}/conf/machine/include/ls1028ardb-swu.inc

#
# Other
#
OPENWRT_VERSION_PRODUCT = "NXP LS1028A RDB"
OPENWRT_VERSION_HWREV = "${SWU_REVISION}"

PACKAGECONFIG_append_pn-fstools = " ovl-rootdisk-part "

PACKAGECONFIG_append_pn-collectd = " cpufreq sensors "
PACKAGE_ARCH_pn-collectd = "${MACHINE_ARCH}"

OPENWRT_HWCLOCK_DEV       = "/dev/rtc0"
OPENWRT_HWCLOCK_LOCALTIME = "0"

TANOWRT_ENABLE_OVERLAY_RESIZE = "1"

#
# Disable DHCP server on LAN interface
#
TANOWRT_DHCPS_CONFIGURE ?= "1"
TANOWRT_DHCPS_LAN_ENABLE ?= "0"
