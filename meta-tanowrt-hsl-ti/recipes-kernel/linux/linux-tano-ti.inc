#
# Copyright (C) 2020 Tano Systems LLC, All rights reserved
# Authors: Anton Kikin <a.kikin@tano-systems.com>
#

PR_append = ".4"

COMPATIBLE_MACHINE = "(ti33x|omap-a15)"

SRC_URI_append = "\
	file://patches-cpsw/0001-cpsw-increase-default-desc-pool-size.patch \
	file://patches-cpsw/0002-cpsw-mark-rx-tx-irq-as-IRQF_NO_THREAD.patch \
	file://patches-musb/0001-musb_gadget-Do-not-use-CPPI-DMA-for-small-transfers.patch \
	file://patches-musb/0002-musb_core-Add-sysfs-entry-for-musb-port1_status-value.patch \
	file://patches-musb/0003-musb_cppi41-Flush-DMA-tx-queue-when-musb-is-not-active.patch \
	file://patches-tty/0001-drivers-tty-refactor-functions-for-flushing-queuing.patch \
	file://patches-tty/0002-drivers-tty-convert-tty_port-to-use-kthread_worker.patch \
	file://patches-tty/0003-drivers-tty-increase-priority-for-tty_buffer_worker.patch \
"

KERNEL_MACHINE_INCLUDE_am335x-icev2 ?= "recipes-kernel/linux/linux-tano-ti-am335x-icev2.inc"
KERNEL_MACHINE_INCLUDE_am335x-bbb ?= "recipes-kernel/linux/linux-tano-ti-am335x-bbb.inc"
KERNEL_MACHINE_INCLUDE_am574x-idk ?= "recipes-kernel/linux/linux-tano-ti-am574x-idk.inc"
KERNEL_MACHINE_INCLUDE ?= "recipes-kernel/linux/linux-tano-ti-${MACHINE}.inc"
include ${KERNEL_MACHINE_INCLUDE}

# Add a run-time dependency for the PM firmware to be installed
# on the target file system.
RDEPENDS_${KERNEL_PACKAGE_NAME}-base_append_ti33x = " amx3-cm3"

RDEPENDS_${KERNEL_PACKAGE_NAME}-base_append_dra7xx = " vpdma-fw"
RDEPENDS_${KERNEL_PACKAGE_NAME}-base_append_dra7xx = " goodix-fw"

KERNEL_EXTRA_ARGS += "LOADADDR=${UBOOT_ENTRYPOINT}"

KERNEL_IMAGETYPE ?= "uImage"

LINUX_VERSION_EXTENSION = "-tano-ti-${LINUX_KERNEL_TYPE}"
