#
# SPDX-License-Identifier: MIT
# Copyright (c) 2018-2020 Tano Systems LLC. All rights reserved.
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

#
# Image
#
IMAGE_FSTYPES = "wic wic.vmdk"
WKS_FILE = "tanowrt-image-qemux86.wks"
WKS_FILE_DEPENDS = "syslinux syslinux-native e2fsprogs-native dosfstools-native mtools-native squashfs-tools-native"
WIC_CREATE_EXTRA_ARGS = "--no-fstab-update"

EFI_PROVIDER = "grub-efi"

# Do not remove /boot from root filesystem
ROOTFS_RM_BOOT_DIR_DISABLE = "1"

# Build ovmf firmware for uefi support in qemu.
EXTRA_IMAGEDEPENDS += "ovmf"

IMAGE_INSTALL_append = "\
	grub \
	grub-efi \
	grub-editenv \
"

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
# OpenWrt specific
#
OPENWRT_VERSION_PRODUCT = "qemux86"
OPENWRT_VERSION_HWREV = "v1"
