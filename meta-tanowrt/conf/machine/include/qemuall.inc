#
# SPDX-License-Identifier: MIT
# Copyright (c) 2018-2022 Tano Systems LLC. All rights reserved.
#

#
# Kernel
#
KERNEL_IMAGETYPE = "bzImage"
PREFERRED_PROVIDER_virtual/kernel = "linux-tano-yocto"
PREFERRED_VERSION_linux-tano-yocto = "5.15%"
PREFERRED_VERSION_xtables-addons = "3.13%"

LINUXLIBCVERSION = "5.15%"
SDKLINUXLIBCVERSION = "5.15%"

# Set to "1" for kernels 5.6+
TANOWRT_WIREGUARD_IN_KERNEL = "1"

QEMU_X86_TARGETS = "i586 x86-64"

# Enable tty1
PROCD_ENABLED_TTYS ?= "1"

MACHINE_FEATURES:append = " hwrng psample bonding vxlan"

TANOWRT_PARTUUID_BOOT        = "bbefcb9e-ac94-43fc-8be2-0023a70c0722"
TANOWRT_PARTUUID_OVERLAY     = "4473a134-2531-4ab1-a5f9-dfa54376eb78"
TANOWRT_PARTUUID_ROOTDEV_A   = "d4997f99-3f39-4952-9433-602b66222be5"
TANOWRT_PARTUUID_ROOTDEV_B   = "fd4aab45-0ed8-48dc-8b07-6514336e2ac1"
TANOWRT_PARTUUID_KERNELDEV_A = "a1a55b33-b1c9-4226-b2f6-0d7d901f040f"
TANOWRT_PARTUUID_KERNELDEV_B = "d35fdb7b-4042-4f75-a4af-8983f9584b47"

TANOWRT_PARTUUID_VARS = "\
       TANOWRT_PARTUUID_BOOT \
       TANOWRT_PARTUUID_OVERLAY \
       TANOWRT_PARTUUID_ROOTDEV_A \
       TANOWRT_PARTUUID_ROOTDEV_B \
       TANOWRT_PARTUUID_KERNELDEV_A \
       TANOWRT_PARTUUID_KERNELDEV_B \
"

#
# QEMU
#
QB_MEM ?= "-m 512"
