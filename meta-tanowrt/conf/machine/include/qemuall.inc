#
# SPDX-License-Identifier: MIT
# Copyright (c) 2018-2020 Tano Systems LLC. All rights reserved.
#

#
# Kernel
#
KERNEL_IMAGETYPE = "bzImage"
PREFERRED_PROVIDER_virtual/kernel = "linux-tano-yocto"
PREFERRED_VERSION_linux-tano-yocto = "5.4%"
PREFERRED_VERSION_xtables-addons = "3.9%"

LINUXLIBCVERSION = "5.4%"
SDKLINUXLIBCVERSION = "5.4%"

QEMU_X86_TARGETS = "i586 x86-64"

# Enable tty1
PROCD_ENABLED_TTYS ?= "1"

MACHINE_FEATURES_append = " hwrng "
