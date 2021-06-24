#
# SPDX-License-Identifier: MIT
# Copyright (c) 2020 Tano Systems LLC. All rights reserved.
#

# meta-swi/meta-swi-mdm9x28/
require conf/machine/swi-mdm9x28-wp.conf

MACHINEOVERRIDES =. "swi-mdm9x28-wp:"

#
# Image
#
IMAGE_FSTYPES = "squashfs 4k.ubifs"

# Bootloader
EXTRA_IMAGEDEPENDS += "lk"

# For userapp (UAPP) MTD partition UBI image.
UBI_USERAPP_PATH ?= "${IMGDEPLOYDIR}/${IMAGE_NAME}.userapp"
UBI_USERAPP_SIZE ?= "124MiB"

PACKAGECONFIG_append_pn-collectd = " cpufreq "
PACKAGE_ARCH_pn-collectd = "${MACHINE_ARCH}"

#
# Kernel
#
SERIAL_CONSOLE = "115200 ttyS0"

#
# QEMU options (runqemu)
#
# No qemu support, yet
MACHINE_FEATURES_remove = "qemu-usermode"
QEMU_TARGETS = "arm"

ENABLE_IMA = "false"
ENABLE_FX30 = "false"
IMA_KERNEL_CMDLINE_OPTIONS = ""

# GPLv2 readline
PREFERRED_VERSION_readline = "8.%"

LEGATO_BUILD ?= "false"

# Note: Supports only single target per machine
LEGATO_ROOTFS_TARGETS = "wp76xx"

KERNEL_BOOT_OPTIONS = "earlyprintk=serial,ttyHSL1,115200 loglevel=7 \
  rootwait rootflags=bulk_read root=/dev/ubiblock0_0 ro rootfstype=squashfs \
  ubi.mtd=13,4096 ubi.block=0,rootfs \
  ubi.mtd=16,4096 \
  rootfs_partition=system rootfs_volume=rootfs \
  overlay_partition=userapp overlay_volume=userapp_vol0 \
  console=ttyHSL1,115200 ima_ready=${ENABLE_IMA} \
"

MACHINE_FEATURES_append = " hwrng "

PACKAGECONFIG_remove_pn-procd = "ujail"
