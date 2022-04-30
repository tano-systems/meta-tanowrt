#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2022 Tano Systems LLC. All rights reserved.
# Authors: Anton Kikin <a.kikin@tano-systems.com>
#

PR_append = ".1"

COMPATIBLE_MACHINE = "(rk3568|rk3308)"

KERNEL_VERSION_SANITY_SKIP = "1"

FILESEXTRAPATHS_prepend = "${TANOWRT_HSL_BASE}/recipes-kernel/linux/features:"

KERNEL_DEVICETREE_COPY = "${@d.getVar('KERNEL_DEVICETREE').replace('rockchip/', '').replace('.dtb', '.dts')}"

KERNEL_DEVICETREE_COPY_DST = "${S}/arch/arm64/boot/dts/rockchip"
KERNEL_DEVICETREE_COPY_DST_aarch64 = "${S}/arch/arm64/boot/dts/rockchip"
KERNEL_DEVICETREE_COPY_DST_arm = "${S}/arch/arm64/boot/dts/rockchip"

KERNEL_MACHINE_INCLUDE_boardcon-em3566 ?= "recipes-kernel/linux/linux-tano-rockchip-boardcon-em3566.inc"
KERNEL_MACHINE_INCLUDE_boardcon-em3568 ?= "recipes-kernel/linux/linux-tano-rockchip-boardcon-em3568.inc"
KERNEL_MACHINE_INCLUDE_rock-pi-s ?= "recipes-kernel/linux/linux-tano-rockchip-rock-pi-s.inc"
KERNEL_MACHINE_INCLUDE ?= ""
require ${KERNEL_MACHINE_INCLUDE}

LINUX_VERSION_EXTENSION = "-tano-rk-${LINUX_KERNEL_TYPE}"

DEPENDS += "python3-native"

inherit python3-dir

do_kernel_configme_prepend() {
	# Make sure we use /usr/bin/env ${PYTHON_PN} for scripts
	for s in `grep -rIl python ${S}/scripts`; do
		sed -i -e '1s|^#!.*python[23]*|#!/usr/bin/env ${PYTHON_PN}|' $s
	done
}
