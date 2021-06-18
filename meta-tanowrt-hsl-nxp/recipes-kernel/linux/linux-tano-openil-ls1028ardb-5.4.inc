#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2020-2021 Tano Systems LLC. All rights reserved.
# Authors: Anton Kikin <a.kikin@tano-systems.com>
#
PR_append = ".7"

COMPATIBLE_MACHINE =. "ls1028ardb|"

# Config
SRC_URI_append_ls1028ardb = " file://defconfig "

SRC_URI_append_ls1028ardb = "\
	file://551-loop-Better-discard-support-for-block-devices.patch \
	file://0001-arm64-fpsimd-Backport-changes.patch \
	file://0002-freescale-Build-all-.dtbs-from-overlays-subdir.patch \
"

# Devicetree
SRC_URI_append_ls1028ardb = "\
	file://fsl-ls1028a-rdb.dts \
	file://fsl-ls1028a.dtsi \
	file://tano-mkb004lw.dtso \
"

do_configure_append_ls1028ardb() {
	install -d ${S}/arch/arm64/boot/dts/freescale
	cp -f ${S}/arch/arm64/boot/dts/freescale/fsl-ls1028a-rdb.dts ${S}/arch/arm64/boot/dts/freescale/fsl-ls1028a-rdb.dts.orig
	cp -f ${S}/arch/arm64/boot/dts/freescale/fsl-ls1028a.dtsi ${S}/arch/arm64/boot/dts/freescale/fsl-ls1028a.dtsi.orig
	install -m 0644 ${WORKDIR}/fsl-ls1028a-rdb.dts ${S}/arch/arm64/boot/dts/freescale/
	install -m 0644 ${WORKDIR}/fsl-ls1028a.dtsi ${S}/arch/arm64/boot/dts/freescale/

	install -d ${S}/arch/arm64/boot/dts/freescale/overlays
	install -m 0644 ${WORKDIR}/tano-mkb004lw.dtso ${S}/arch/arm64/boot/dts/freescale/overlays/
}
