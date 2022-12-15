#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2020-2021 Tano Systems LLC. All rights reserved.
# Authors: Anton Kikin <a.kikin@tano-systems.com>
#

PR:append = ".8"

COMPATIBLE_MACHINE = "(ti33x|omap-a15)"

FILESEXTRAPATHS:prepend = "${TANOWRT_HSL_BASE}/recipes-kernel/linux/features:"

SRC_URI:append = "\
	file://patches-cpsw/0001-cpsw-increase-default-desc-pool-size.patch \
	file://patches-cpsw/0002-cpsw-mark-rx-tx-irq-as-IRQF_NO_THREAD.patch \
	file://patches-musb/0001-musb_gadget-Do-not-use-CPPI-DMA-for-small-transfers.patch \
	file://patches-musb/0002-musb_core-Add-sysfs-entry-for-musb-port1_status-value.patch \
	file://patches-musb/0003-musb_cppi41-Flush-DMA-tx-queue-when-musb-is-not-active.patch \
	file://patches-tty/0001-drivers-tty-refactor-functions-for-flushing-queuing.patch \
	file://patches-tty/0002-drivers-tty-convert-tty_port-to-use-kthread_worker.patch \
	file://patches-tty/0003-drivers-tty-increase-priority-for-tty_buffer_worker.patch \
	${@oe.utils.conditional('ENABLE_TI_UIO_DEVICES', '1', 'file://uio.scc file://uio-pruss.scc', '', d)} \
"

SRC_URI:append = "\
	${@oe.utils.conditional('LINUX_VERSION_SHORT', '4.19', 'file://patches-rt/0001-hrtimer-fix-logic-for-when-grabbing-softirq_expiry_l.patch', '', d)} \
"

KERNEL_MACHINE_INCLUDE:am335x-icev2 ?= "recipes-kernel/linux/linux-tano-ti-am335x-icev2.inc"
KERNEL_MACHINE_INCLUDE:am335x-bbb ?= "recipes-kernel/linux/linux-tano-ti-am335x-bbb.inc"
KERNEL_MACHINE_INCLUDE:am574x-idk ?= "recipes-kernel/linux/linux-tano-ti-am574x-idk.inc"
KERNEL_MACHINE_INCLUDE ?= "recipes-kernel/linux/linux-tano-ti-${MACHINE}.inc"
include ${KERNEL_MACHINE_INCLUDE}

TI_UIO_REQUIRE ?= "${@oe.utils.conditional('ENABLE_TI_UIO_DEVICES', '1', 'recipes-kernel/linux/ti-uio.inc', '', d)}"
require ${TI_UIO_REQUIRE}

KERNEL_FEATURES:append = "${@oe.utils.conditional('ENABLE_TI_UIO_DEVICES', '1', ' uio.scc uio-pruss.scc ', '', d)}"
RDEPENDS:${KERNEL_PACKAGE_NAME}-base:append = "${@oe.utils.conditional('ENABLE_TI_UIO_DEVICES', '1', ' uio-module-drv uio-module-drv-test ', '', d)}"

# Add a run-time dependency for the PM firmware to be installed
# on the target file system.
RDEPENDS:${KERNEL_PACKAGE_NAME}-base:append:ti33x = " amx3-cm3"

RDEPENDS:${KERNEL_PACKAGE_NAME}-base:append:dra7xx = " vpdma-fw"
RDEPENDS:${KERNEL_PACKAGE_NAME}-base:append:dra7xx = " goodix-fw"

KERNEL_EXTRA_ARGS += "LOADADDR=${UBOOT_ENTRYPOINT}"

KERNEL_IMAGETYPE ?= "uImage"

LINUX_VERSION_EXTENSION = "-tano-ti-${LINUX_KERNEL_TYPE}"

# Special configuration for remoteproc/rpmsg IPC modules
module_conf_rpmsg_client_sample = "blacklist rpmsg_client_sample"
module_conf_ti_k3_r5_remoteproc = "softdep ti_k3_r5_remoteproc pre: virtio_rpmsg_bus"
module_conf_ti_k3_dsp_remoteproc = "softdep ti_k3_dsp_remoteproc pre: virtio_rpmsg_bus"
KERNEL_MODULE_PROBECONF += "rpmsg_client_sample ti_k3_r5_remoteproc ti_k3_dsp_remoteproc"
KERNEL_MODULE_AUTOLOAD:append:j7 = " rpmsg_kdrv_switch"
