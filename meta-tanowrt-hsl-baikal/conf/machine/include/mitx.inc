#
# SPDX-License-Identifier: MIT
# Copyright (c) 2021 Tano Systems LLC. All rights reserved.
#

require conf/machine/include/baikal-be-m1000.inc

MACHINEOVERRIDES =. "mitx:"

TANOWRT_PARTUUID_BOOT        = "6468acf8-db09-4a44-8a28-1277390934ba "
TANOWRT_PARTUUID_OVERLAY     = "25a56de0-1873-4c27-b228-b5ff9a1f82bb"
TANOWRT_PARTUUID_ROOTDEV_A   = "66430133-7e75-404d-8714-241922f9eca7"
TANOWRT_PARTUUID_ROOTDEV_B   = "a55febe1-5b24-465c-a7e2-3070cf3a22a8"
TANOWRT_PARTUUID_KERNELDEV_A = "88bfbf5d-75ea-42e2-b605-b6faef0db41e"
TANOWRT_PARTUUID_KERNELDEV_B = "2c3d2555-fb65-41a6-9d8c-cf835ff030ee"

TANOWRT_PARTUUID_VARS = "\
	TANOWRT_PARTUUID_BOOT \
	TANOWRT_PARTUUID_OVERLAY \
	TANOWRT_PARTUUID_ROOTDEV_A \
	TANOWRT_PARTUUID_ROOTDEV_B \
	TANOWRT_PARTUUID_KERNELDEV_A \
	TANOWRT_PARTUUID_KERNELDEV_B \
"

#
# Machine
#
MACHINE_FEATURES = "\
	pci \
	apm \
	usbhost \
	vfat \
	ext2 \
	ethernet \
	wifi \
	cgroup \
	stp \
	lldp \
	crypto \
	efi \
	screen \
	swupdate \
"

MACHINE_EXTRA_RRECOMMENDS_append = "\
	nvme-cli \
"

#
# Image generation
#
ROOTFS_IMAGE_FSTYPE = "squashfs-lzo"
IMAGE_FSTYPES = "wic ${ROOTFS_IMAGE_FSTYPE}"
MACHINE_EXTRA_RDEPENDS_append = " usbutils-ids "

SPLASH = "psplash"
IMAGE_FEATURES += "${@bb.utils.contains('MACHINE_FEATURES', 'screen', 'splash', '', d)}"

EFI_PROVIDER = "grub-efi"

WKS_FILE = "image-baikal-m-swu-a-b.wks.in"
WKS_FILE_DEPENDS = "syslinux-native e2fsprogs-native dosfstools-native mtools-native squashfs-tools-native"
WIC_CREATE_EXTRA_ARGS = "--no-fstab-update"
WIC_DO_IMAGE_DEPENDS = "\
	${IMAGE_BASENAME}:do_image_squashfs_lzo \
	${EFI_PROVIDER}:do_deploy \
	virtual/grub-bootconf:do_deploy \
"
do_image_wic[depends] += "${WIC_DO_IMAGE_DEPENDS}"

# These variables are used in image-baikal-m-swu-a-b.wks.in
WKS_IMAGE_ROOTFS_BINARY = "${IMAGE_BASENAME}-${MACHINE}.${ROOTFS_IMAGE_FSTYPE}"
WKS_IMAGE_KERNEL_BINARY = "${KERNEL_IMAGETYPE}-${MACHINE}.ext4"
WKS_IMAGE_GRUB_CONFIG = "grub.cfg"

TANOWRT_ENABLE_OVERLAY_RESIZE = "1"

# Remove /boot from root filesystem
ROOTFS_RM_BOOT_DIR_DISABLE = "0"

IMAGE_INSTALL_append = "\
	grub-editenv \
	efibootmgr \
"

PACKAGECONFIG_append_pn-fstools = " ovl-rootdisk-part "

#
# Initramfs (used for factory installation image)
#
## TODO:
##INITRAMFS_IMAGE = "tanowrt-image-initramfs-swu-factory"
##INITRAMFS_IMAGE_NAME = "${INITRAMFS_IMAGE}-${MACHINE}"
##INITRAMFS_IMAGE_BUNDLE = "0"

##INITRD = "${DEPLOY_DIR_IMAGE}/${INITRAMFS_IMAGE_NAME}.cpio.gz"

#
# SWUpdate
#
SWU_BOOTLOADER = "grub-efi"
SWU_OVERLAY_CONTAINER = "blkdev"
SWU_OVERLAY_FS = "ext4"

SWU_IMAGE_MEDIA_TYPE = "USB/SATA/NVMe Drive"

SWU_IMAGE_EXTRA_ARTIFACTS += "kernel bootloader"

SWU_IMAGE_ARTIFACT_kernel[fname] = "${KERNEL_IMAGETYPE}"
SWU_IMAGE_ARTIFACT_kernel[ftype] = "ext4"
SWU_IMAGE_ARTIFACT_kernel[depends] = "virtual/kernel:do_deploy"
SWU_IMAGE_ARTIFACT_kernel[verextract] = "1"
SWU_IMAGE_ARTIFACT_kernel[verextract-cmd] = "strings"
SWU_IMAGE_ARTIFACT_kernel[verextract-filter-cmd] = "grep -m 2 'oe-user@oe-host' | tail -n1 | awk '{ print $3 }'"
SWU_IMAGE_ARTIFACT_kernel[sw-desc-type] = "raw"
SWU_IMAGE_ARTIFACT_kernel[sw-desc-device-a] = "/dev/swu-kernel-a"
SWU_IMAGE_ARTIFACT_kernel[sw-desc-device-b] = "/dev/swu-kernel-b"

SWU_IMAGE_ARTIFACT_rootfs[sw-desc-type] = "raw"
SWU_IMAGE_ARTIFACT_rootfs[sw-desc-device-a] = "/dev/swu-rootfs-a"
SWU_IMAGE_ARTIFACT_rootfs[sw-desc-device-b] = "/dev/swu-rootfs-b"

SWU_IMAGE_ARTIFACT_bootloader[name] = "grub"
SWU_IMAGE_ARTIFACT_bootloader[fname] = "grub-efi-${@os.path.splitext(d.getVar('EFI_BOOT_IMAGE', True))[0]}"
SWU_IMAGE_ARTIFACT_bootloader[ftype] = "efi"
SWU_IMAGE_ARTIFACT_bootloader[depends] = "grub-efi:do_deploy"
SWU_IMAGE_ARTIFACT_bootloader[verextract] = "1"
SWU_IMAGE_ARTIFACT_bootloader[verextract-cmd] = "strings"
SWU_IMAGE_ARTIFACT_bootloader[verextract-filter-cmd] = "sed -n '/GNU GRUB  version %s/{n;p;}'"
SWU_IMAGE_ARTIFACT_bootloader[sw-desc-type] = "rawfile"
SWU_IMAGE_ARTIFACT_bootloader[sw-desc-path] = "${EFI_FILES_PATH}/${EFI_BOOT_IMAGE}"

# Extra dependencies for factory installation script
SWU_INSTALL_EXTRA_RDEPENDS += "parted"

#
# Other
#

# Enable tty1
PROCD_ENABLED_TTYS ?= "1"

TANOWRT_DHCPS_CONFIGURE = "1"
TANOWRT_DHCPS_LAN_ENABLE = "1"

PACKAGECONFIG_append_pn-collectd = " cpufreq sensors "
PACKAGE_ARCH_pn-collectd = "${MACHINE_ARCH}"

OPENWRT_VERSION_HWREV = "${SWU_REVISION}"
