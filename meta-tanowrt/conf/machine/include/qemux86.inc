#
# SPDX-License-Identifier: MIT
# Copyright (c) 2018-2021 Tano Systems LLC. All rights reserved.
#

#
# PACKAGECONFIG's
#
PACKAGECONFIG_append_pn-fstools = " ovl-rootdisk-part "

#
# Kernel
#
PREFERRED_PROVIDER_virtual/kernel_qemux86 = "linux-tano-yocto"
SERIAL_CONSOLES ?= "115200;ttyS0"

KERNEL_CLASSES = "kernel-tn-mkimage"
KERNEL_MKIMAGE_TYPES = "ext4"

#
# Image
#
ROOTFS_IMAGE_FSTYPE = "squashfs-lzo"
IMAGE_FSTYPES = "wic wic.vmdk ${ROOTFS_IMAGE_FSTYPE}"

TANOWRT_ENABLE_OVERLAY_RESIZE = "1"

EFI_PROVIDER = "grub-efi"

WKS_FILE = "tanowrt-image-qemux86.wks.in"
WKS_FILE_DEPENDS = "syslinux syslinux-native e2fsprogs-native dosfstools-native mtools-native squashfs-tools-native"
WIC_CREATE_EXTRA_ARGS = "--no-fstab-update"
WIC_DO_IMAGE_DEPENDS = "\
	${IMAGE_BASENAME}:do_image_squashfs_lzo \
	${EFI_PROVIDER}:do_deploy \
	virtual/grub-bootconf:do_deploy \
"
do_image_wic[depends] += "${WIC_DO_IMAGE_DEPENDS}"

# These variables are used in tanowrt-image-qemux86.wks.in
WKS_IMAGE_ROOTFS_BINARY = "${IMAGE_BASENAME}-${MACHINE}.${ROOTFS_IMAGE_FSTYPE}"
WKS_IMAGE_KERNEL_BINARY = "${KERNEL_IMAGETYPE}-${MACHINE}.ext4"
WKS_IMAGE_GRUB_CONFIG = "grub.cfg"

# Remove /boot from root filesystem
ROOTFS_RM_BOOT_DIR_DISABLE = "0"

# Build ovmf firmware for uefi support in qemu.
EXTRA_IMAGEDEPENDS += "ovmf"

IMAGE_INSTALL_append = "\
	grub-editenv \
"

#
# Initramfs (used for factory installation image)
#
INITRAMFS_IMAGE = "tanowrt-image-initramfs-swu-factory"
INITRAMFS_IMAGE_NAME = "${INITRAMFS_IMAGE}-${MACHINE}"
INITRAMFS_IMAGE_BUNDLE = "0"

INITRD = "${DEPLOY_DIR_IMAGE}/${INITRAMFS_IMAGE_NAME}.cpio.gz"

#
# Machine/distro features
#
MACHINE_FEATURES = "\
	pci \
	apm \
	usbgadget \
	usbhost \
	vfat \
	ext2 \
	ethernet \
	wifi \
	cgroup \
	stp \
	lldp \
	crypto \
	pcbios \
	efi \
	swupdate \
	swupdate-factory \
"

#
# QEMU options (runqemu)
#
QB_MACHINE = "-machine q35"
QB_DEFAULT_FSTYPE = "wic"
QB_SYSTEM_NAME = "qemu-system-i386"
QB_KERNEL_CMDLINE_APPEND = "console=ttyS0,115200 console=tty1"
QB_OPT_APPEND = "-usb -device virtio-rng-pci -nographic -smp 2"
QB_SERIAL_OPT = "-serial stdio"
QB_AUDIO_DRV  = ""
QB_AUDIO_OPT  = ""

# Change bootorder to first boot from hard drive
QB_OPT_APPEND += "-boot order=c,menu=off"

# Add EFI ovmf firmware to qemu
QB_OPT_APPEND += "-drive if=pflash,format=qcow2,file=${DEPLOY_DIR_IMAGE}/ovmf.qcow2"

#
# SWUPDATE
#
SWU_BOOTLOADER = "grub-efi"
SWU_OVERLAY_CONTAINER = "blkdev"
SWU_OVERLAY_FS = "ext4"

SWU_IMAGE_MEDIA_TYPE = "Virtual Drive"

SWU_IMAGE_EXTRA_ARTIFACTS += "kernel bootloader"

SWU_IMAGE_ARTIFACT_kernel[fname] = "${KERNEL_IMAGETYPE}"
SWU_IMAGE_ARTIFACT_kernel[ftype] = "ext4"
SWU_IMAGE_ARTIFACT_kernel[depends] = "virtual/kernel:do_deploy"
SWU_IMAGE_ARTIFACT_kernel[verextract] = "1"
SWU_IMAGE_ARTIFACT_kernel[verextract-cmd] = "strings"
SWU_IMAGE_ARTIFACT_kernel[verextract-filter-cmd] = "grep -m 1 'oe-user@oe-host' | awk '{ print $1 }'"
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

# Factory image creation
SWU_FACTORY_WKS_FILE ?= "qemux86-swu-factory.wks.in"
SWU_FACTORY_WKS_FILE_DEPENDS += "cdrtools-native"
SWU_FACTORY_WKS_OUTPUT_EXT = "iso"

SWU_FACTORY_EXTRA_DEPENDS += "\
	${EFI_PROVIDER}:do_deploy \
	virtual/kernel:do_deploy \
	virtual/grub-bootconf:do_deploy \
"

SWU_FACTORY_EXTRA_FILES = "\
	grub-efi-startup.nsh;startup.nsh \
	grub-efi-grubenv \
	grub.cfg;grub-efi-grub.cfg \
"

#
# OpenWrt specific
#
OPENWRT_VERSION_PRODUCT = "qemux86"
OPENWRT_VERSION_HWREV = "v1"
