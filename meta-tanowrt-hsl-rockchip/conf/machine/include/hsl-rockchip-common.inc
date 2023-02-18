# Copyright (c) 2019, Fuzhou Rockchip Electronics Co., Ltd
# Copyright (c) 2022-2023, Tano Systems LLC
# Released under the MIT license (see COPYING.MIT for the terms)

require conf/machine/include/soc-family.inc

MACHINE_EXTRA_RRECOMMENDS:append = " \
	${RK_WIFIBT_FIRMWARES} \
	kernel-modules \
"

RK_ISP_VERSION := ""

SERIAL_CONSOLES ?= "115200;ttyFIQ0"
KERNEL_IMAGETYPE ?= "Image"
KBUILD_DEFCONFIG ?= ""
MACHINEOVERRIDES .= ":rockchip"

# Custom kernel might not support newest python
PACKAGECONFIG:remove:pn-perf = "scripting"

PREFERRED_RPROVIDER_lib32-initd-functions = "lib32-initscripts"

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
	hwrng \
"

MACHINE_EXTRA_RDEPENDS:append = " \
	udev-conf-rockchip \
"

#
# Bootloader
#
PREFERRED_PROVIDER_u-boot ?= "u-boot-tano-rockchip"
PREFERRED_PROVIDER_virtual/bootloader ?= "${PREFERRED_PROVIDER_u-boot}"

UBOOT_INITIAL_ENV   = "u-boot-initial-env"
UBOOT_ENV_SIZE      = "0x8000"
UBOOT_ENV_REDUNDANT = "1"

UBOOT_SUFFIX = "bin"
UBOOT_BINARY = "u-boot-${MACHINE}.${UBOOT_SUFFIX}"

KERNEL_ENTRYPOINT = "0x00280000"
KERNEL_LOADADDRESS = "0x00280000"
UBOOT_DTB_LOADADDRESS = "0x0a100000"
UBOOT_DTBO_LOADADDRESS = "0x0a200000"

UBOOT_BINARYNAME ?= "${@os.path.splitext(d.getVar("UBOOT_BINARY"))[0]}"
UBOOT_IMAGE ?= "u-boot-${MACHINE}-${PV}-${PR}.${UBOOT_SUFFIX}"
UBOOT_SYMLINK ?= "u-boot-${MACHINE}.${UBOOT_SUFFIX}"
UBOOT_MAKE_TARGET ?= "all"

#
# Kernel
#
PREFERRED_PROVIDER_virtual/kernel = "linux-tano-rockchip"

PREFERRED_VERSION_linux-tano-rockchip = "5.10%"
LINUXLIBCVERSION = "5.10-custom%"

KERNEL_IMAGETYPE = "fitImage"
KERNEL_IMAGETYPES = "fitImage"
KERNEL_CLASSES = "kernel-tn-fitimage kernel-tn-mkimage"
KERNEL_MKIMAGE_TYPES = "ext4"

#
# Image
#
ROOTFS_IMAGE_FSTYPE = "squashfs-lzo"

TANOWRT_PARTUUID_VARS = "\
	TANOWRT_PARTUUID_BOOT \
	TANOWRT_PARTUUID_OVERLAY \
	TANOWRT_PARTUUID_ROOTDEV_A \
	TANOWRT_PARTUUID_ROOTDEV_B \
	TANOWRT_PARTUUID_KERNELDEV_A \
	TANOWRT_PARTUUID_KERNELDEV_B \
"

IMAGE_FSTYPES += "${ROOTFS_IMAGE_FSTYPE} wic"
IMAGE_CLASSES += "wic-sdcard-img"
WKS_FILE ?= "sdimage-rockchip-gptdisk.wks.in"

WIC_CREATE_EXTRA_ARGS = "--no-fstab-update"
WIC_DO_IMAGE_DEPENDS = "\
	${IMAGE_BASENAME}:do_image_squashfs_lzo \
	u-boot-startup-script:do_deploy \
	virtual/bootloader:do_deploy \
	virtual/kernel:do_deploy \
"

do_image_wic[depends] += "${WIC_DO_IMAGE_DEPENDS}"

WKS_IMAGE_IDBLOCK_BINARY = "${RK_IMAGE_IDBLOCK}-${MACHINE}-sdcard"
WKS_IMAGE_UBOOT_INITIAL_ENV_BINARY = "u-boot-initial-env-${MACHINE}-sdcard.bin"
WKS_IMAGE_ROOTFS_BINARY = "${IMAGE_BASENAME}-${MACHINE}.${ROOTFS_IMAGE_FSTYPE}"
WKS_IMAGE_KERNEL_BINARY = "${KERNEL_IMAGETYPE}-${MACHINE}.ext4"
WKS_IMAGE_UBOOT_BINARY = "${UBOOT_BINARY}"
WKS_IMAGE_STARTUP_BINARY = "startup-${MACHINE}.img"

# Remove /boot from root filesystem
ROOTFS_RM_BOOT_DIR_DISABLE = "0"

RK_IMAGE_IDBLOCK ?= "idblock.img"
RK_IMAGE_LOADER ?= "loader.bin"

RK_IDBLOCK_OFFSET ?= "32768"
RK_STARTUP_OFFSET ?= "4063232"

PACKAGECONFIG:append:pn-fstools = " ovl-rootdisk-part "
PACKAGECONFIG:append:pn-collectd = " thermal "
PACKAGE_ARCH:pn-collectd = "${MACHINE_ARCH}"

PACKAGE_ARCH:pn-netifd = "${MACHINE_ARCH}"
PACKAGE_ARCH:pn-dnsmasq = "${MACHINE_ARCH}"

TANOWRT_NETIFD_CONFIGURE_INTERFACES = "lan usb0"
TANOWRT_NETIFD_CONFIGURE_INTERFACE_lan = "proto=dhcp device=br-lan peerdns=1"
TANOWRT_NETIFD_CONFIGURE_INTERFACE_usb0 = "proto=static device=usb0 force_link=0 ipaddr=192.168.128.1 netmask=255.255.255.0"

TANOWRT_DNSMASQ_CONFIGURE_INTERFACES = "lan usb0"
TANOWRT_DNSMASQ_CONFIGURE_INTERFACE_lan = "ignore=1"
TANOWRT_DNSMASQ_CONFIGURE_INTERFACE_usb0 = "start=100 limit=1 interface=usb0 leasetime=15m"
