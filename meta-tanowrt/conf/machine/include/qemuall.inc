#
# SPDX-License-Identifier: MIT
# Copyright (c) 2018-2021 Tano Systems LLC. All rights reserved.
#

#
# Kernel
#
KERNEL_IMAGETYPE = "bzImage"
PREFERRED_PROVIDER_virtual/kernel = "linux-tano-yocto"
PREFERRED_VERSION_linux-tano-yocto = "5.10%"
PREFERRED_VERSION_xtables-addons = "3.13%"

LINUXLIBCVERSION = "5.10%"
SDKLINUXLIBCVERSION = "5.10%"

# Set to "1" for kernels 5.6+
TANOWRT_WIREGUARD_IN_KERNEL = "1"

QEMU_X86_TARGETS = "i586 x86-64"

# Enable tty1
PROCD_ENABLED_TTYS ?= "1"

MACHINE_FEATURES_append = " hwrng "

#
# QEMU
#
QB_MEM ?= "-m 512"
