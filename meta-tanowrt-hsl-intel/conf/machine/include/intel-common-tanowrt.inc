#
# SPDX-License-Identifier: MIT
# Copyright (c) 2022 Tano Systems LLC. All rights reserved.
#

#
# Kernel
#
KERNEL_IMAGETYPE = "bzImage"

PREFERRED_PROVIDER_virtual/kernel = "linux-tano-intel"

PREFERRED_VERSION_linux-tano-intel = "5.15%"
PREFERRED_VERSION_linux-tano-intel-rt = "5.15%"

PREFERRED_VERSION_xtables-addons = "3.13%"

LINUXLIBCVERSION = "5.10%"
SDKLINUXLIBCVERSION = "5.10%"

# Set to "1" for kernels 5.6+
TANOWRT_WIREGUARD_IN_KERNEL = "1"

# Enable tty0
PROCD_ENABLED_TTYS ?= "0"

MACHINE_FEATURES:append = " hwrng "

# Do not install all firmwares (> 700 MiB), install only specified
MACHINE_EXTRA_RRECOMMENDS:remove = "linux-firmware"

###########################################

#
# PACKAGECONFIG's
#
PACKAGECONFIG:append_pn-fstools = " ovl-rootdisk-part "

#
# Kernel
#
SERIAL_CONSOLES ?= "115200;ttyS0"

KERNEL_CLASSES = "kernel-tn-mkimage"
KERNEL_MKIMAGE_TYPES = ""

#
# Image
#
TANOWRT_PARTUUID_BOOT      = "617d05fc-e3ca-4427-842b-18355497a0db"
TANOWRT_PARTUUID_OVERLAY   = "5f198da7-09e9-426b-ab5a-8791bc3fdc77"
TANOWRT_PARTUUID_ROOTDEV_A = "cd5da453-1fc1-4eb1-ba16-d32672642f77"
TANOWRT_PARTUUID_ROOTDEV_B = "a4ab17b2-49fb-48c9-a0f6-f14be8c11515"

TANOWRT_PARTUUID_VARS = "\
	TANOWRT_PARTUUID_BOOT \
	TANOWRT_PARTUUID_OVERLAY \
	TANOWRT_PARTUUID_ROOTDEV_A \
	TANOWRT_PARTUUID_ROOTDEV_B \
"

KERNEL_MODULE_AUTOLOAD:append = " i2c-dev"

TANOWRT_NETIFD_CONFIGURE_INTERFACES = "lan"
TANOWRT_NETIFD_CONFIGURE_INTERFACE_lan = "proto=dhcp device=br-lan"

TANOWRT_DHCPS_CONFIGURE ?= "1"
TANOWRT_DHCPS_LAN_ENABLE = "0"

ROOTFS_IMAGE_FSTYPE = "squashfs-lzo"
IMAGE_FSTYPES = "wic wic.vmdk ${ROOTFS_IMAGE_FSTYPE}"
IMAGE_CLASSES += "rootfs-mv-kernel-to-root"

TANOWRT_ENABLE_OVERLAY_RESIZE = "1"

EFI_PROVIDER = "grub-efi"
EFI_STARTUP_DEVICE = "fs1"

WKS_FILE = "intel-common-tanowrt-image.wks.in"
WKS_FILE_DEPENDS = "syslinux syslinux-native e2fsprogs-native dosfstools-native mtools-native squashfs-tools-native"
WIC_CREATE_EXTRA_ARGS = "--no-fstab-update"
WIC_DO_IMAGE_DEPENDS = "\
	${IMAGE_BASENAME}:do_image_squashfs_lzo \
	${EFI_PROVIDER}:do_deploy \
	virtual/grub-bootconf:do_deploy \
"
do_image_wic[depends] += "${WIC_DO_IMAGE_DEPENDS}"

WKS_IMAGE_ROOTFS_BINARY = "${IMAGE_BASENAME}-${MACHINE}.${ROOTFS_IMAGE_FSTYPE}"
WKS_IMAGE_KERNEL_BINARY = "${KERNEL_IMAGETYPE}-${MACHINE}.ext4"
WKS_IMAGE_GRUB_CONFIG = "grub.cfg"

# Remove /boot from root filesystem
ROOTFS_RM_BOOT_DIR_DISABLE = "0"

IMAGE_INSTALL:append = "\
	grub-editenv \
"

#
# Image
#
IMAGE_INSTALL:append = "\
	i2c-tools \
	pmtools \
	nvme-cli \
	dmidecode \
	pciutils-ids \
	usbutils-ids \
	efivar \
	iperf2 \
	iperf3 \
	lmsensors \
	tmux \
	spitools \
	hdparm \
	parted \
	util-linux-lsblk \
	gptfdisk \
	tcpdump \
"

#
# Machine/distro features
#
MACHINE_FEATURES = "\
	pci \
	apm \
	usbhost \
	vfat \
	ext2 \
	ethernet \
	cgroup \
	stp \
	lldp \
	crypto \
	pcbios \
	efi \
	psample \
"

#
# OpenWrt specific
#
OPENWRT_VERSION_PRODUCT = "Intel x86_64"
OPENWRT_VERSION_HWREV = "1.0"
